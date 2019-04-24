package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/")
@Controller
public class UrlRedirectController {

    @RequestMapping("/chatroom")
    public String chatroom(HttpServletRequest request){
        return "chatroom";
    }

}
