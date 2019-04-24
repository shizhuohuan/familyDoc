package com.service.impl;

import com.entity.FdQuession;
import com.mongo.dao.FdDocDao;
import com.mongo.dao.FdQuessionDao;
import com.service.FdQuessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FdQuessionServiceImpl implements FdQuessionService {
    @Autowired
    private FdQuessionDao quessionDao;

    @Override
    public void add(FdQuession quession) {
        quessionDao.add(quession);
    }

    @Override
    public void update(FdQuession quession) {
        quessionDao.update(quession);
    }

    @Override
    public List<FdQuession> getByUserId(String userId) {
        return quessionDao.getByUserId(userId);
    }

    @Override
    public FdQuession getById(String id) {
        return quessionDao.getById(id);
    }

    @Override
    public List<FdQuession> getByDptId(String dptId) {
        return quessionDao.getByDptId(dptId);
    }

    @Override
    public List<FdQuession> getAll() {
        return quessionDao.getAll();
    }

    @Override
    public void delete(String id) {
        quessionDao.delete(id);
    }
}
