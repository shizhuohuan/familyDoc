package com.service.impl;

import com.dao.FdDepartmentDao;
import com.entity.FdDepartment;
import com.service.FdDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
public class FdDepartmentServiceImpl implements FdDepartmentService {
    @Autowired
    private FdDepartmentDao fdDepartmentDao;

    @Override
    public FdDepartment getById(String id) throws SQLException {
        return fdDepartmentDao.getById(id);
    }

    @Override
    public List<FdDepartment> getAll() throws SQLException {
        return fdDepartmentDao.getAll();
    }

    @Override
    public List<FdDepartment> getByType(int type) throws SQLException {
        return fdDepartmentDao.getByType(type);
    }
}
