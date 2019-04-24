package com.mongo.dao.impl;

import com.mongo.dao.FdDocDao;
import com.entity.FdDoc;
import com.mongo.MyMongoTemple;
import com.mongodb.WriteResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class FdDocDaoImpl extends MyMongoTemple implements FdDocDao{
    private static String collectionName = "FdDoc";
    @Override
    public FdDoc getById(String id) throws SQLException {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(id));
        List<FdDoc> fdDocs = mongoTemplate.find(query,FdDoc.class,collectionName);
        if(CollectionUtils.isNotEmpty(fdDocs)){
            return fdDocs.get(0);
        }else {
            return null;
        }

    }

    @Override
    public List<FdDoc> getAll() throws SQLException {
        List<FdDoc> all = mongoTemplate.findAll(FdDoc.class,collectionName);
        return all;
    }

    @Override
    public List<String> getMyRecord(String id) throws SQLException {
        //return mongoTemplate.findById(id,FdDoc.class,collectionName).getCharge();
        return null;
    }

    @Override
    public List<FdDoc> getByDptId(String dptId) throws SQLException {
        Query query = new Query();
        query.addCriteria(Criteria.where("dptId").is(dptId));
        List<FdDoc> fdDocs = mongoTemplate.find(query,FdDoc.class,collectionName);
        return fdDocs;
    }

    @Override
    public void add(FdDoc fdDoc) {
        mongoTemplate.insert(fdDoc,"FdDoc");
    }

    @Override
    public void update(FdDoc fdDoc) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(fdDoc.getUserId()));  //_id区分引号 "1"和1
        Update update = new Update();
        if(StringUtils.isNotEmpty(fdDoc.getRelName())){
            update.set("relName",fdDoc.getRelName());
        }
        if(StringUtils.isNotEmpty(fdDoc.getIdcard())){
            update.set("idcard",fdDoc.getIdcard());
        }
        if(fdDoc.getAge()!=null && fdDoc.getAge()>0){
            update.set("age",fdDoc.getAge());
        }
        if(StringUtils.isNotEmpty(fdDoc.getSex())){
            update.set("sex",fdDoc.getSex());
        }
        if(StringUtils.isNotEmpty(fdDoc.getHospital())){
            update.set("hospital",fdDoc.getHospital());
        }
        if(StringUtils.isNotEmpty(fdDoc.getDptId())){
            update.set("dptId",fdDoc.getDptId());
        }
        if(StringUtils.isNotEmpty(fdDoc.getProduce())) {
            update.set("produce", fdDoc.getProduce());
        }
        if(StringUtils.isNotEmpty(fdDoc.getLevel())) {
            update.set("level", fdDoc.getLevel());
        }
        if(StringUtils.isNotEmpty(fdDoc.getAddress())) {
            update.set("address", fdDoc.getAddress());
        }
        if(CollectionUtils.isNotEmpty(fdDoc.getCharge())) {
            update.set("charge", fdDoc.getCharge());
        }
        mongoTemplate.updateFirst(query, update, collectionName);
//	      WriteResult upsert = mongoTemplate.updateMulti(query, update, "userList"); //查询到的全部更新
//	      WriteResult upsert = mongoTemplate.updateFirst(query, update, "userList"); //查询更新第一条
//        WriteResult upsert = mongoTemplate.upsert(query, update, "userList");      //有则更新，没有则新增
    }

    @Override
    public void deleteById(String id) {


    }
}
