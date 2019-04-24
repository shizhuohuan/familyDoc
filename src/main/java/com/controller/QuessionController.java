package com.controller;

import com.entity.*;
import com.es.EsTemplate;
import com.google.gson.Gson;
import com.service.*;
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
import java.util.*;
import java.util.stream.Collectors;

@RequestMapping("/quession")
@Controller
public class QuessionController {
    @Autowired
    private FdQuessionService quessionService;
    @Autowired
    private FdDocService docService;
    @Autowired
    private FdHistoryService historyService;
    @Autowired
    private FdDepartmentService departmentService;
    @Autowired
    private FdAnswerService answerService;
    @Autowired
    private FdUserService userService;
    private EsTemplate esTemplate = EsTemplate.esTemplate();
    @RequestMapping("/addQuession")
    @ResponseBody
    public ModelMap addQuession(String title, String content, String dpt,HttpServletRequest request) {
        ModelMap map = new ModelMap();
        try {
            if (StringUtils.isEmpty(title) || StringUtils.isEmpty(content)) {
                map.put("type", "error");
                map.put("message", "标题或内容为空");
                return map;
            }
            FdUser user = (FdUser) request.getSession().getAttribute(Constants.CURRENT_USER);
            FdQuession quession = new FdQuession();
            quession.setUserId(user.getId());
            quession.setTitle(title);
            quession.setContent(content);
            quession.setCreateTime(DateUtil.NowDate2String());
            if(StringUtils.isNotEmpty(dpt)){
                quession.setDptId(dpt);
            }
            quessionService.add(quession);
            esTemplate.upsertDocument(quession,Constants.TABLE_QUESSION);
            map.put("type", "success");
            map.put("message", "提问成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("type", "error");
            map.put("message", "添加问题失败");
        }
        return map;
    }

    @RequestMapping("/allQuession")
    public String allQuession(ModelMap map, HttpServletRequest request) {
        try {
            map.put("allQuession", quessionService.getAll());
            return "allQuession";
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMsg", "获取问题失败");
        }
        return "error";
    }
    @RequestMapping("/askQuession")
    public String askQuession(ModelMap map, HttpServletRequest request) {
        try {
            map.put("dpt", departmentService.getAll());
            return "askQuession";
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMsg", "提问--获取部门失败");
        }
        return "error";
    }

    @RequestMapping("/myQuession")
    @ResponseBody
    public String myQuession(HttpServletRequest request) {
        try {
            FdUser user = (FdUser) request.getSession().getAttribute(Constants.CURRENT_USER);
            List<FdQuession> quessions = quessionService.getByUserId(user.getId());
            List<FdAnswer> answers = answerService.getByUserId(user.getId());
            answers = answers.stream().collect(//list是需要去重的list，返回值是去重后的list
                    Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getQuessionId()))),//o代表object对象，o.list对象的属性值，根据此属性值去重
                            ArrayList::new));
            if(CollectionUtils.isNotEmpty(answers)){
                for(FdAnswer answer : answers){
                    FdQuession quession = quessionService.getById(answer.getQuessionId());
                    quession.setUserId(Constants.ANSWER);
                    quessions.add(quession);
                }
            }
            Gson gson = new Gson();
            String json = gson.toJson(quessions);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMsg", "获取我的问题失败");
        }
        return "error";
    }

    @RequestMapping("/toMyQuession")
    public String toMyQuession(HttpServletRequest request) {
        return "myQuession";
    }

    @RequestMapping("/dQuession")
    @ResponseBody
    public ModelMap dQuession(HttpServletRequest request) {
        ModelMap map = new ModelMap();
        try {
            String pid = request.getParameter("qid");
            if(StringUtils.isEmpty(pid)){
                map.put("type", "error");
                map.put("message", "问题Id为空");
            }
            quessionService.delete(pid);
            esTemplate.removeOneById(pid,Constants.TABLE_QUESSION);
            map.put("type", "success");
            map.put("message", "删除成功");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("type", "error");
            map.put("message", "删除问题失败");
        }
        return map;
    }

    @RequestMapping("/quessionDetail")
    public String quessionDetail(String qid, ModelMap map,HttpServletRequest request) {
        try {
            if(StringUtils.isEmpty(qid)){
                request.getSession().setAttribute("errorMsg", "问题ID为空");
                return "error";
            }
            FdUser user = (FdUser) request.getSession().getAttribute(Constants.CURRENT_USER);
            FdQuession quession = quessionService.getById(qid);
            quession.setUserId(user.getUserName());
            map.put("quession",quession);
            if(CollectionUtils.isNotEmpty(quession.getAnswer())){
                List<FdAnswerDto> answers = new ArrayList<>();
                for(String aid: quession.getAnswer()){
                    FdAnswer fdAnswer =  answerService.getById(aid);
                    FdAnswerDto answerDto = new FdAnswerDto();
                    answerDto.setId(fdAnswer.getId());
                    answerDto.setUserId(fdAnswer.getUserId());
                    answerDto.setQuessionId(fdAnswer.getQuessionId());
                    answerDto.setCreateTime(fdAnswer.getCreateTime());
                    answerDto.setContent(fdAnswer.getContent());
                    FdUser answerUser = userService.getById(fdAnswer.getUserId());
                    FdDoc doc = docService.getById(answerUser.getId());
                    answerDto.setDocData(doc.getRelName() + " (" + doc.getHospital() + ")");
                    answers.add(answerDto);
                }
                answers.sort(Comparator.reverseOrder());
                map.put("answer",answers);
            }
            return "quessionDetail";
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMsg", "获取问题详情错误");
        }
        return "error";
    }

    @RequestMapping("/answerQs")
    @ResponseBody
    public ModelMap answerQs(String qid,String content,HttpServletRequest request) {
        ModelMap map = new ModelMap();
        try {
            FdQuession quession = quessionService.getById(qid);
            FdAnswer answer = new FdAnswer();
            FdUser user = (FdUser) request.getSession().getAttribute(Constants.CURRENT_USER);
            answer.setId(UUIDs.randomBase64UUID());
            answer.setUserId(user.getId());
            answer.setContent(content);
            answer.setCreateTime(DateUtil.NowDate2String());
            answer.setQuessionId(qid);
            answerService.add(answer);
            List<String> qsAnswer = new ArrayList<>();
            qsAnswer.add(answer.getId());
            if(CollectionUtils.isEmpty(quession.getAnswer())){
                quession.setAnswer(qsAnswer);
            }else {
                qsAnswer = quession.getAnswer();
                qsAnswer.add(answer.getId());
                quession.setAnswer(qsAnswer);
            }
            quessionService.update(quession);
            esTemplate.upsertDocument(quession,Constants.TABLE_QUESSION);
            FdDoc doc = docService.getById(user.getId());
            map.put("type", "success");
            map.put("message", "回答问题成功");
            map.put("docName",doc.getRelName() + " (" + doc.getHospital() + ")");
            map.put("createTime",answer.getCreateTime());
            map.put("answerId",answer.getId());
            map.put("docId",doc.getId());
            map.put("userId",user.getId());
            addToHistory(quession.getTitle(),user.getId(),quession.getUserId(),quession.getId());
        }catch (Exception e){
            e.printStackTrace();
            map.put("type", "error");
            map.put("message", "回答问题失败");
        }
        return map;
    }
    @RequestMapping("/deleteAnswer")
    @ResponseBody
    public ModelMap deleteAnswer(String aid,String qid,HttpServletRequest request) {
        ModelMap map = new ModelMap();
        try {
            if(StringUtils.isEmpty(aid) || StringUtils.isEmpty(qid)){
                map.put("type", "error");
                map.put("message", "id为空");
                return map;
            }
            answerService.delete(aid);
            FdQuession quession = quessionService.getById(qid);
            List<String> answer = quession.getAnswer();
            if(answer.contains(aid)){
                answer.remove(aid);
            }
            quession.setAnswer(answer);
            quessionService.update(quession);
            esTemplate.upsertDocument(quession,Constants.TABLE_QUESSION);
            map.put("type", "success");
            if(answer.size() == 0){
                map.put("noData","true");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("type", "error");
            map.put("message", "删除回答失败");
        }
        return  map;
    }

    public void addToHistory(String content,String sendId,String recId,String quessionId) throws SQLException {
        FdHistory history = new FdHistory();
        history.setId(UUIDs.randomBase64UUID());
        history.setCreateDate(DateUtil.NowDate2String());
        history.setContent(content);
        history.setSendId(sendId);
        history.setReciveId(recId);
        history.setConfirm(Constants.MESSAGE_UNCONFIRM);
        history.setType(Constants.MESSAGE_QUESSION);
        history.setMessageId(quessionId);
        historyService.add(history);
    }
}
