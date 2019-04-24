package com.controller;

import com.entity.FdHistory;
import com.entity.FdQuession;
import com.entity.FdUser;
import com.google.gson.Gson;
import com.service.FdDocService;
import com.service.FdHistoryService;
import com.service.FdPatientService;
import com.service.FdUserService;
import com.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/message")
@Controller
public class MessageController {
    @Autowired
    private FdUserService userService;
    @Autowired
    private FdHistoryService historyService;
    @Autowired
    private FdDocService docService;
    @Autowired
    private FdPatientService patientService;
    @RequestMapping("/myMessage")
    @ResponseBody
    public String myMessage(HttpServletRequest request) {
        try {
            FdUser user = (FdUser) request.getSession().getAttribute(Constants.CURRENT_USER);
            List<FdHistory> histories = historyService.getBySendIdWithoutType(user.getId(),Constants.MESSAGE_UNCONFIRM);
            for(FdHistory history:histories){
                FdUser messageUser = userService.getById(history.getSendId());
                if(messageUser.getIdentity().equals("1")){
                    history.setSendId(docService.getById(messageUser.getId()).getRelName());
                }else if(messageUser.getIdentity().equals("2")){
                    history.setSendId(patientService.getById(messageUser.getId()).getRelName());
                }else {
                    history.setSendId(messageUser.getUserName());
                }
            }
            Gson gson = new Gson();
            String json = gson.toJson(histories);
            return json;
        }catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMsg", "获取我的消息失败");
        }
        return "error";
    }
    @RequestMapping("/confirmMsg")
    @ResponseBody
    public ModelMap confirmMsg(HttpServletRequest request) {
        ModelMap map = new ModelMap();
        try {
            String mid = request.getParameter("mid");
            historyService.confirm(mid);
            map.put("type", "success");
        }catch (Exception e){
            e.printStackTrace();
            map.put("type", "error");
            map.put("message", "确认接受消息失败");
        }
        return  map;
    }
}
