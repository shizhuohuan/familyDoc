package com.controller;

import com.es.EsTemplate;
import com.service.FdDocService;
import com.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequestMapping("/search")
@Controller
public class SearchController {
    private EsTemplate esTemplate = EsTemplate.esTemplate();
    @Autowired
    private FdDocService docService;
    @RequestMapping("/searchDoc")
    @ResponseBody
    public ModelMap searchDoc(String keyword,HttpServletRequest request){
        ModelMap map = new ModelMap();
        try {
            List<Map<String, Object>> doc = esTemplate.search(Constants.TABLE_DOC,keyword);
            map.put("type", "success");
            map.put("doc",doc);
            return map;
        }catch (Exception e){
            e.printStackTrace();
            map.put("type", "error");
            map.put("message", "搜索医生信息失败");
        }
        return map;
    }

    @RequestMapping("/searchQuession")
    @ResponseBody
    public ModelMap searchQuession(String keyword,HttpServletRequest request){
        ModelMap map = new ModelMap();
        try {
            List<Map<String, Object>> quession = esTemplate.search(Constants.TABLE_QUESSION,keyword);
            map.put("type", "success");
            map.put("quession",quession);
            return map;
        }catch (Exception e){
            e.printStackTrace();
            map.put("type", "error");
            map.put("message", "搜索问题信息失败");
        }
        return map;
    }

    @RequestMapping("/searchAll")
    public String searchAll(ModelMap map,HttpServletRequest request){
        String type = request.getParameter("type");
        String keyword = request.getParameter("keyword");
        String tableName = Constants.TABLE_DOC;
        if(type.equals("quession")){
            tableName = Constants.TABLE_QUESSION;
        }
        try {
            List<Map<String, Object>> hit = esTemplate.search(tableName,keyword);
            if(type.equals("quession")){
                map.put("allQuession",hit);
                return "allQuession";
            }else {
                map.put("allDoc",hit);
                return "allDoc";
            }
        }catch (Exception e){
            e.printStackTrace();
            request.getSession().setAttribute("errorMsg", "ES查询失败");
        }
        return "error";
    }

    @RequestMapping("/searchAllDoc")
    @ResponseBody
    public ModelMap searchAllDoc(String keyword,HttpServletRequest request){
        ModelMap map = new ModelMap();
        try {
            map.put("type", "success");
            map.put("doc",docService.getAll());
            return map;
        }catch (Exception e){
            e.printStackTrace();
            map.put("type", "error");
            map.put("message", "搜索问题信息失败");
        }
        return map;
    }


}
