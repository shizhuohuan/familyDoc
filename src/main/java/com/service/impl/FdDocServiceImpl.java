package com.service.impl;

import com.entity.FdDoc;
import com.mongo.dao.FdDocDao;
import com.service.FdDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
public class FdDocServiceImpl implements FdDocService {
    @Autowired
    private FdDocDao fdDocDao;
    @Override
    public FdDoc getById(String id) throws SQLException {
        return fdDocDao.getById(id);
    }

    @Override
    public List<FdDoc> getAll() throws SQLException {
        return fdDocDao.getAll();
    }

    @Override
    public List<String> getMyRecord(String id) throws SQLException {
        return fdDocDao.getById(id).getCharge();
    }

    @Override
    public List<FdDoc> getByDptId(String dptId) throws SQLException {
        return fdDocDao.getByDptId(dptId);
    }

    @Override
    public void add(FdDoc fdDoc) {
        fdDocDao.add(fdDoc);
    }

    @Override
    public void update(FdDoc fdDoc) {
        try {
            if(getById(fdDoc.getUserId()) == null){
                add(fdDoc);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        fdDocDao.update(fdDoc);
    }

    @Override
    public void deleteById(String id) {
        fdDocDao.deleteById(id);
    }
}
