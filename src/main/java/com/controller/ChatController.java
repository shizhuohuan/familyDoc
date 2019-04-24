package com.controller;

import com.entity.FdPatient;
import com.entity.FdUser;
import com.service.FdDocService;
import com.service.FdHistoryService;
import com.service.FdPatientService;
import com.service.FdUserService;
import com.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/chat")
@Controller
public class ChatController {
    @Autowired
    private FdHistoryService historyService;
    @Autowired
    private FdUserService userService;
    @Autowired
    private FdDocService docService;
    @Autowired
    private FdPatientService patientService;
    @RequestMapping("/toCharRoom")
    public String toCharRoom(String recId, ModelMap map,HttpServletRequest request) {
        try {
            map.put("recId",recId);
            FdUser user = userService.getById(recId);
            if(user.getIdentity().equals("1")){
                map.put("recName",docService.getById(recId).getRelName());
            }else if (user.getIdentity().equals("2")){
                map.put("recName",patientService.getById(recId).getRelName());
            }else {
                map.put("recName",user.getUserName());
            }
            return "chatroom";
        }catch (Exception e){
            e.printStackTrace();
            request.getSession().setAttribute("errorMsg", "进入聊天室失败");
        }
        return "error";
    }
    @RequestMapping("/getUnconfirmMsg")
    @ResponseBody
    public ModelMap getUnconfirmMsg(String sendId,String recId,HttpServletRequest request){
        ModelMap map = new ModelMap();
        try {
            map.put("unconfirmMsg",historyService.getChatHistory(sendId,recId,Constants.MESSAGE_CONFIRM));
            map.put("type","success");
        }catch (Exception e){
            e.printStackTrace();
            map.put("type","error");
            map.put("message","获取聊天记录失败");
        }
        return map;
    }


}
