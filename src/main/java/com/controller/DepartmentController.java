package com.controller;

import com.entity.FdDoc;
import com.entity.FdQuession;
import com.entity.FdUser;
import com.service.FdDepartmentService;
import com.service.FdDocService;
import com.service.FdQuessionService;
import com.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/department")
@Controller
public class DepartmentController {
    @Autowired
    private FdDepartmentService departmentService;
    @Autowired
    private FdQuessionService quessionService;
    @Autowired
    private FdDocService docService;
    @RequestMapping("/getAllDpt")
    public String getAllDpt(ModelMap map,HttpServletRequest request) {
        try {
            map.put("dpt_1",departmentService.getByType(1));
            map.put("dpt_2",departmentService.getByType(2));
            map.put("dpt_3",departmentService.getByType(3));
            map.put("dpt_4",departmentService.getByType(4));
            return "department";
        }catch (Exception e){
            e.printStackTrace();
            request.getSession().setAttribute("errorMsg","获取科室失败");
        }
        return "error";
    }

    @RequestMapping("/dptDetail")
    public String dptDetail(String dptId,ModelMap map) {
        try {
            map.put("department",departmentService.getById(dptId));
            map.put("quessionOfDpt",quessionService.getByDptId(dptId));
            map.put("docOfDpt",docService.getByDptId(dptId));
            return "departmentDetail";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }

    @RequestMapping("/docShow")
    @ResponseBody
    public ModelMap docShow(String userId,HttpServletRequest request) {
        ModelMap map = new ModelMap();
        try {
            FdDoc doc = docService.getById(userId);
            doc.setDptId(departmentService.getById(doc.getDptId()).getDpName());
            map.put("docOfDpt",doc);
            map.put("type","success");
            map.put("message","获取医生资料成功");
            FdUser user = (FdUser) request.getSession().getAttribute(Constants.CURRENT_USER);
            if(userId.equals(user.getId())){
                map.put("own","true");
            }
            return map;
        }catch (Exception e){
            e.printStackTrace();
            map.put("type","error");
            map.put("message","获取医生资料失败");
        }
        return map;
    }
}
