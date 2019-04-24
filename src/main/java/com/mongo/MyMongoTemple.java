package com.mongo;


import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;


public class MyMongoTemple {

    protected static MongoTemplate mongoTemplate;

    static {
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        mongoTemplate = (MongoTemplate) wac.getBean("mongoTemplate");
        System.out.println("初始化mongo------"+mongoTemplate);
    }

}
