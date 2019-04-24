package com.service;

import com.entity.FdDepartment;

import java.sql.SQLException;
import java.util.List;

public interface FdDepartmentService {
    public FdDepartment getById(String id) throws SQLException;
    public List<FdDepartment> getAll() throws SQLException;
    public List<FdDepartment> getByType(int type) throws SQLException;
}
