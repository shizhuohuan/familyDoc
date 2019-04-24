package com.controller;

import com.entity.FdDoc;
import com.entity.FdPatient;
import com.entity.FdUser;
import com.es.EsTemplate;
import com.service.FdDepartmentService;
import com.service.FdDocService;
import com.service.FdPatientService;
import com.service.FdUserService;
import com.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.common.UUIDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/home")
@Controller
public class HomeController {
    @Autowired
    private FdUserService userService;
    @Autowired
    private FdDocService docService;
    @Autowired
    private FdPatientService patientService;
    @Autowired
    private FdDepartmentService departmentService;
    private EsTemplate esTemplate = EsTemplate.esTemplate();
    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            FdUser user = userService.getByName(username);
            if(user!=null && user.getPassword().equals(password)){
                request.getSession().setAttribute(Constants.CURRENT_USER,user);
                return "index";
            }else {
                request.getSession().setAttribute("errorMsg","用户名密码错误");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }

    @RequestMapping("/register")
    @ResponseBody
    public ModelMap regist(HttpServletRequest request){
        ModelMap modelMap= new ModelMap();
        try {
            String username = request.getParameter("usernamesignup");
            String pwd = request.getParameter("passwordsignup");
            String pwd_confirm = request.getParameter("passwordsignup_confirm");
            if(!pwd.equals(pwd_confirm)){
                modelMap.put("type","error");
                modelMap.put("msg","密码不一致");
                return modelMap;
            }
            FdUser user = new FdUser();
            user.setUserName(username);
            user.setPassword(pwd);
            user.setIdentity("3");
            userService.add(user);
            modelMap.put("type","success");
            modelMap.put("msg","注册成功");
        }catch (Exception e){
            e.printStackTrace();
            modelMap.put("type","error");
            modelMap.put("msg","出现错误");
        }
        return modelMap;
    }

    @RequestMapping("/validateUserName")
    @ResponseBody
    public ModelMap validateUserName(String username,HttpServletRequest request){
        ModelMap modelMap= new ModelMap();
        try {
            if(userService.getByName(username)!=null){
                modelMap.put("error","用户名已存在");
                return modelMap;
            }
        }catch (Exception e){
            e.printStackTrace();
            modelMap.put("error","发生错误");
        }
        return modelMap;
    }

    @RequestMapping("/myData")
    public String  myData(ModelMap map,HttpServletRequest request){
        try {
            FdUser user = (FdUser) request.getSession().getAttribute(Constants.CURRENT_USER);
            String identity = request.getParameter("identity");
            if(StringUtils.isNotEmpty(identity) && identity.equals("1")){
                user.setIdentity(identity);
                userService.update(user);
                request.getSession().setAttribute(Constants.CURRENT_USER,user);
            }
            if(user.getIdentity().equals("1")){
               map.put("userData", docService.getById(user.getId()));
               map.put("dpt", departmentService.getAll());
            }else {
                map.put("userData",patientService.getById(user.getId()));
            }
            return "myData";
        }catch (Exception e){
            e.printStackTrace();
            request.getSession().setAttribute("errorMsg","获取个人信息失败");
        }
        return "error";
    }
    @RequestMapping("/updateData")
    @ResponseBody
    public ModelMap  updateData(HttpServletRequest request){
        ModelMap modelMap= new ModelMap();
        try {
            FdUser user = (FdUser) request.getSession().getAttribute(Constants.CURRENT_USER);
            String relName = request.getParameter("relName");
            String idcard = request.getParameter("idcard");
            Integer age = null;
            if(StringUtils.isNotEmpty(request.getParameter("age"))){
                age = Integer.valueOf(request.getParameter("age"));
            }
            String sex = request.getParameter("sex");
            if(user.getIdentity().equals("1")){
                if(docService.getById(user.getId()) == null){
                    modelMap.put("identity","doc");
                }
                FdDoc doc = new FdDoc(relName,idcard,age,sex);
                doc.setUserId(user.getId());
                doc.setHospital(request.getParameter("hospital"));
                doc.setAddress(request.getParameter("address"));
                doc.setDptId(request.getParameter("dptId"));
                doc.setSex(request.getParameter("sex"));
                doc.setSex(request.getParameter("sex"));
                doc.setLevel(request.getParameter("level"));
                doc.setProduce(request.getParameter("produce"));
                docService.update(doc);
                esTemplate.upsertDocument(doc,Constants.TABLE_DOC);
            }else {

                FdPatient patient = new FdPatient(relName,idcard,age,sex);
                patient.setUserId(user.getId());
                patient.setAddress(request.getParameter("address"));
                patient.setMarray(request.getParameter("marray"));
                patient.setAllergic(request.getParameter("allergic"));
                patient.setProfession(request.getParameter("profession"));
                patientService.update(patient);
                if(user.getIdentity().equals("3")){
                    user.setIdentity("2");
                    userService.update(user);
                    modelMap.put("identity","patient");
                }
            }
            modelMap.put("type","success");
            modelMap.put("message","修改资料成功");
        }catch (Exception e){
            e.printStackTrace();
            modelMap.put("type","error");
            modelMap.put("message","修改资料失败");
        }
        return modelMap;
    }

    @RequestMapping("/updatePwd")
    @ResponseBody
    public ModelMap  updatePwd(HttpServletRequest request){
        ModelMap modelMap= new ModelMap();
        try {
            String pwd = request.getParameter("pwd");
            String confirm = request.getParameter("confirm_pwd");
            FdUser user = (FdUser) request.getSession().getAttribute(Constants.CURRENT_USER);

            if(!pwd.equals(confirm)){
                modelMap.put("type","error");
                modelMap.put("message","两次密码输入不一致");
                return modelMap;
            }else if (user.getPassword().equals(pwd)){
                modelMap.put("type","error");
                modelMap.put("message","密码与原密码一致");
                return modelMap;
            }
            user.setPassword(pwd);
            userService.update(user);
            modelMap.put("type","success");
            modelMap.put("message","修改密码成功");
            request.getSession().setAttribute(Constants.CURRENT_USER,user);
        }catch (Exception e){
            e.printStackTrace();
            modelMap.put("type","error");
            modelMap.put("message","修改密码失败");
        }
        return modelMap;
    }
}
