package com.controller;

import com.entity.FdDoc;
import com.entity.FdQuession;
import com.entity.FdUser;
import com.es.EsTemplate;
import com.mongo.MyMongoTemple;
import com.mongodb.QueryBuilder;
import com.mongodb.WriteResult;
import com.mysql.cj.core.util.StringUtils;
import com.service.FdDocService;
import com.service.FdQuessionService;
import com.util.Constants;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.UUIDs;
import org.jboss.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.*;

@RequestMapping("/test")
@Controller
public class TestController {

    @Autowired
    private FdDocService fdDocService;
    @Autowired
    private MongoTemplate mongoTemplate;
    private EsTemplate esTemplate = EsTemplate.esTemplate();
    @Autowired
    private FdDocService docService;
    @Autowired
    private FdQuessionService quessionService;
    @RequestMapping("/test")
    public String test(HttpServletRequest request){
//        try {
//            //System.out.println(testService.findTestById("1"));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return "test";
    }

    @RequestMapping("/testMongo")
    @ResponseBody
    public String testMongo(HttpServletRequest request){
//        Test t = new Test();
//        t.setTid("3");
//        t.setName("李三");
//        Content content = new Content();
//        content.setid("1");
//        content.setContent("1111");
//        content.setCreateDate("2019-4-10");
//        mongoTemplate.insert(content,"Content");
        //插入
       // mongoTemplate.insert(t,"test");

        //模糊匹配
//        Query q = new Query(Criteria.where("name").regex("三"));
//        List<Test> t2 = mongoTemplate.find(q,Test.class);
        //t2.forEach(test -> System.out.println(test.getName()));
        Query query = new Query();
        //多条件查询
//        query.addCriteria(Criteria.where("tid").is("1"));
//        query.addCriteria(Criteria.where("name").is("张三"));
//        List<Test> t3 = mongoTemplate.find(query, Test.class);
//        t3.forEach(test -> System.out.println(test.getName()));
        //or、and操作
//        Criteria  criteria = new Criteria();
//        criteria.orOperator(Criteria.where("name").regex("张"), Criteria.where("tid").regex("1"));
//        criteria.andOperator(Criteria.where("name").regex("张"), Criteria.where("tid").regex("1"));
        //updatea操作
//        query.addCriteria(Criteria.where("_id").is(1));  //_id区分引号 "1"和1
//        Update update = Update.update("name", "zzzzz");
//	      WriteResult upsert = mongoTemplate.updateMulti(query, update, "userList"); //查询到的全部更新
//	      WriteResult upsert = mongoTemplate.updateFirst(query, update, "userList"); //查询更新第一条
//        WriteResult upsert = mongoTemplate.upsert(query, update, "userList");      //有则更新，没有则新增

        return "";
    }
    @RequestMapping("/testEs")
    @ResponseBody
    public String testEs(HttpServletRequest request) throws SQLException {
        List<Map<String,Object>> hitsList= esTemplate.search("fduser","器");

        for(Map<String,Object> m:hitsList){
            Set<String> s = m.keySet();
            for(String ss:s){
                System.out.println(ss + "-------" + m.get(ss));
            }
        }
//        FdUser user = new FdUser();
//
//        user.setId("FYa0cGUlSRaNNK04FofIqg");
//        user.setUserName("分词器设置");
//        user.setPassword("分词器");
//        user.setIdentity("3");
//        esTemplate.upsertDocument(user,"fduser");
//        List<FdQuession> docs = quessionService.getAll();
//        for(FdQuession doc:docs){
//            esTemplate.upsertDocument(doc,Constants.TABLE_QUESSION);
//        }
        return "";
    }
    @RequestMapping("/chatroom")
    @ResponseBody
    public String chatroom(HttpServletRequest request){
        return "chatroom";
    }
    @RequestMapping("/getMongoTemplate")
    @ResponseBody
    public String getMongoTemplate(HttpServletRequest request) throws SQLException {

        try {
            FdDoc fdDoc = fdDocService.getById("1");
            List<String> re = new ArrayList<>();
            re.add("111");
            re.add("222");
            fdDoc.setCharge(re);
            fdDocService.update(fdDoc);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
