package com.mongo.dao.impl;

import com.entity.FdDoc;
import com.entity.FdPatient;
import com.mongo.MyMongoTemple;
import com.mongo.dao.FdPatientDao;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FdPatientDaoImpl extends MyMongoTemple implements FdPatientDao {
    private static String collectionName = "FdPatient";

    @Override
    public FdPatient getById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(id));
        List<FdPatient> fdPatient= mongoTemplate.find(query, FdPatient.class, collectionName);
        if(CollectionUtils.isNotEmpty(fdPatient)){
            return fdPatient.get(0);
        }else {
            return null;
        }
    }

    @Override
    public List<String> getMyRecord(String id) {
        return null;
    }

    @Override
    public List<FdPatient> getAll() {
        return mongoTemplate.findAll(FdPatient.class, collectionName);
    }

    @Override
    public void add(FdPatient fdPatient) {
        mongoTemplate.insert(fdPatient, collectionName);
    }

    @Override
    public void update(FdPatient fdPatient) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(fdPatient.getUserId()));
        Update update = new Update();
        if (StringUtils.isNotEmpty(fdPatient.getRelName())) {
            update.set("relName", fdPatient.getRelName());
        }
        if (StringUtils.isNotEmpty(fdPatient.getIdcard())) {
            update.set("idcard", fdPatient.getIdcard());
        }
        if (fdPatient.getAge() != null && fdPatient.getAge() > 0) {
            update.set("age", fdPatient.getAge());
        }
        if (StringUtils.isNotEmpty(fdPatient.getSex())) {
            update.set("sex", fdPatient.getSex());
        }
        if (StringUtils.isNotEmpty(fdPatient.getAddress())) {
            update.set("address", fdPatient.getAddress());
        }
        if (StringUtils.isNotEmpty(fdPatient.getMarray())) {
            update.set("marray", fdPatient.getMarray());
        }
        update.set("allergic", fdPatient.getAllergic());
        if (StringUtils.isNotEmpty(fdPatient.getProfession())) {
            update.set("profession", fdPatient.getProfession());
        }
        if (CollectionUtils.isNotEmpty(fdPatient.getRecord())) {
            update.set("record", fdPatient.getRecord());
        }
        mongoTemplate.updateFirst(query, update, collectionName);
    }

    @Override
    public void deleteById(String id) {

    }
}
