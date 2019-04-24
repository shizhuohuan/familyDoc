package com.service.impl;

import com.dao.FdAnswerDao;
import com.dao.FdDepartmentDao;
import com.entity.FdAnswer;
import com.service.FdAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
public class FdAnswerServiceImpl implements FdAnswerService {
    @Autowired
    private FdAnswerDao fdAnswerDao;

    @Override
    public FdAnswer getById(String id) throws SQLException {
        return fdAnswerDao.getById(id);
    }

    @Override
    public List<FdAnswer> getByQsId(String qsId) throws SQLException {
        return fdAnswerDao.getByQsId(qsId);
    }

    @Override
    public void add(FdAnswer answer) throws SQLException {
        fdAnswerDao.add(answer);
    }

    @Override
    public List<FdAnswer> getByUserId(String userId) throws SQLException {
        return fdAnswerDao.getByUserId(userId);
    }

    @Override
    public void delete(String id) throws SQLException {
        fdAnswerDao.delete(id);
    }
}
