package com.controller;

import com.entity.*;
import com.google.gson.Gson;
import com.service.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.util.Constants;
import com.util.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.common.UUIDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/record")
@Controller
public class RecordController {
    @Autowired
    private FdDocService docService;
    @Autowired
    private FdPatientService patientService;
    @Autowired
    private FdDepartmentService departmentService;
    @Autowired
    private FdRecordService recordService;
    @Autowired
    private FdHistoryService historyService;
    @RequestMapping("/commitRecord")
    @ResponseBody
    public ModelMap writeRecord(HttpServletRequest request) {
        ModelMap map = new ModelMap();
        try {
            FdUser user = (FdUser) request.getSession().getAttribute(Constants.CURRENT_USER);
            String talk = request.getParameter("talk");
            String currenIll = request.getParameter("currentIll");
            String dpt = request.getParameter("dpt");
            String docId = request.getParameter("doc");
            FdRecord record = new FdRecord(dpt, currenIll, talk);
            record.setCreateTime(DateUtil.NowDate2String());
            record.setId(UUIDs.randomBase64UUID());
            record.setDocId(docId);
            record.setUserId(user.getId());
            recordService.add(record);
            FdDoc doc = docService.getById(docId);
            List<String> docRecord = doc.getCharge();
            if (CollectionUtils.isEmpty(docRecord)) {
                docRecord = new ArrayList<>();
            }
            docRecord.add(record.getId());
            doc.setCharge(docRecord);
            docService.update(doc);
            FdPatient patient = patientService.getById(user.getId());
            List<String> pRecord = patient.getRecord();
            if (CollectionUtils.isEmpty(pRecord)) {
                pRecord = new ArrayList<>();
            }
            pRecord.add(record.getId());
            patient.setRecord(pRecord);
            patientService.update(patient);
            map.put("type", "success");
            addToHistory(record.getTalk(),user.getId(),docId,record.getId());
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("type", "error");
            map.put("message", "提交病历失败");
        }
        return map;
    }

    @RequestMapping("/getDpt")
    @ResponseBody
    public ModelMap getDpt(HttpServletRequest request) {
        ModelMap map = new ModelMap();
        try {
            map.put("allDpt", departmentService.getAll());
            map.put("type", "success");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("type", "error");
            map.put("message", "病历--获取部门失败");
        }
        return map;
    }

    @RequestMapping("/getDptDoc")
    @ResponseBody
    public ModelMap getDptDoc(String dptId, HttpServletRequest request) {
        ModelMap map = new ModelMap();
        try {
            map.put("doc", docService.getByDptId(dptId));
            map.put("type", "success");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("type", "error");
            map.put("message", "病历--获取部门医生失败");
        }
        return map;
    }

    @RequestMapping("/toMyRecord")
    public String toMyRecord(HttpServletRequest request) {
        return "myRecord";
    }

    @RequestMapping("/myRecord")
    @ResponseBody
    public String myRecord(HttpServletRequest request) {
        try {
            FdUser user = (FdUser) request.getSession().getAttribute(Constants.CURRENT_USER);
            List<String> recordId = new ArrayList<>();
            if (user.getIdentity().equals("1")) {
                FdDoc doc = docService.getById(user.getId());
                if (CollectionUtils.isNotEmpty(doc.getCharge())) {
                    recordId = doc.getCharge();
                }
            } else if (user.getIdentity().equals("2")) {
                FdPatient patient = patientService.getById(user.getId());
                if (CollectionUtils.isNotEmpty(patient.getRecord())) {
                    recordId = patient.getRecord();
                }
            } else {
                request.getSession().setAttribute("errorMsg", "普通用户没有病历");
            }
            Gson gson = new Gson();
            List<FdRecord> record = new ArrayList<>();

            if (CollectionUtils.size(recordId) != 0) {
                for (String rid : recordId) {
                    FdRecord myRecord = recordService.getById(rid);
                    myRecord.setDocId(docService.getById(myRecord.getDocId()).getRelName());
                    myRecord.setUserId(patientService.getById(myRecord.getUserId()).getRelName());
                    myRecord.setDeptId(departmentService.getById(myRecord.getDeptId()).getDpName());
                    record.add(myRecord);
                }
            }
            String json1= gson.toJson(record);
            return json1;
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMsg", "获取我的病历失败");
        }
        return "error";
    }

    @RequestMapping("/recordDetail")
    public String recordDetail(ModelMap modelMap, HttpServletRequest request) {
        try {
            String cid = request.getParameter("cid");
            FdRecord record = recordService.getById(cid);
            modelMap.put("userId",record.getUserId());
            modelMap.put("userData",patientService.getById(record.getUserId()));
            record.setUserId(patientService.getById(record.getUserId()).getRelName());
            modelMap.put("docId",record.getDocId());
            record.setDocId(docService.getById(record.getDocId()).getRelName());
            record.setDeptId(departmentService.getById(record.getDeptId()).getDpName());
            modelMap.put("record",record);
            return "recordDetail";
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMsg", "查看病历详情失败");
        }
        return "error";
    }

    @RequestMapping("/updateRecord")
    @ResponseBody
    public ModelMap updateRecord(HttpServletRequest request) {
        ModelMap map = new ModelMap();
        try {
            String diagnose = request.getParameter("diagnose");
            String docAdvice = request.getParameter("docAdvice");
            String remark = request.getParameter("remark");
            FdRecord record = recordService.getById(request.getParameter("rid"));
            if(record!=null){
                if(StringUtils.isNotEmpty(diagnose)){
                    record.setDiagnose(diagnose);
                }
                if(StringUtils.isNotEmpty(docAdvice)){
                    record.setDocAdvice(docAdvice);
                }
                if(StringUtils.isNotEmpty(remark)){
                    record.setRemark(remark);
                }
                recordService.update(record);
                map.put("type", "success");
                addToHistory(record.getTalk(),record.getDocId(),record.getUserId(),record.getId());
            }else {
                map.put("type", "error");
                map.put("message", "病历为空");

            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("type", "error");
            map.put("message", "病历--获取部门医生失败");
        }
        return map;
    }

    public void addToHistory(String content,String sendId,String recId,String recordId) throws SQLException {
        FdHistory history = new FdHistory();
        history.setId(UUIDs.randomBase64UUID());
        history.setCreateDate(DateUtil.NowDate2String());
        history.setContent(content);
        history.setSendId(sendId);
        history.setReciveId(recId);
        history.setConfirm(Constants.MESSAGE_UNCONFIRM);
        history.setType(Constants.MESSAGE_RECORD);
        history.setMessageId(recordId);
        historyService.add(history);
    }
}
