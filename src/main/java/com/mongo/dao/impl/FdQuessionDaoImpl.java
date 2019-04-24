package com.mongo.dao.impl;

import com.entity.FdPatient;
import com.entity.FdQuession;
import com.mongo.MyMongoTemple;
import com.mongo.dao.FdQuessionDao;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FdQuessionDaoImpl extends MyMongoTemple implements FdQuessionDao {
    private static String collectionName = "FdQuession";
    @Override
    public void add(FdQuession quession) {
        mongoTemplate.insert(quession, collectionName);
    }

    @Override
    public void update(FdQuession quession) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(quession.getId()));
        Update update = new Update();
        if (StringUtils.isNotEmpty(quession.getUserId())) {
            update.set("userId", quession.getUserId());
        }
        if (StringUtils.isNotEmpty(quession.getTitle())) {
            update.set("title", quession.getTitle());
        }
        if (StringUtils.isNotEmpty(quession.getContent())) {
            update.set("content", quession.getContent());
        }
        if (StringUtils.isNotEmpty(quession.getCreateTime())) {
            update.set("createTime", quession.getCreateTime());
        }
        if (quession.getAnswer()!=null) {
            update.set("answer", quession.getAnswer());
        }
        if (StringUtils.isNotEmpty(quession.getDptId())) {
            update.set("dptId", quession.getDptId());
        }
        mongoTemplate.updateFirst(query, update, collectionName);
    }

    @Override
    public List<FdQuession> getByUserId(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.find(query, FdQuession.class, collectionName);
    }

    @Override
    public FdQuession getById(String id) {
        return mongoTemplate.findById(id,FdQuession.class,collectionName);
    }

    @Override
    public List<FdQuession> getByDptId(String dptId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("dptId").is(dptId));
        return mongoTemplate.find(query, FdQuession.class, collectionName);
    }

    @Override
    public List<FdQuession> getAll() {
        return mongoTemplate.findAll(FdQuession.class,collectionName);
    }

    @Override
    public void delete(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        mongoTemplate.remove(query,FdQuession.class,collectionName);
    }
}
