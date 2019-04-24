package com.dao;

import com.entity.FdDepartment;

import java.sql.SQLException;
import java.util.List;

public interface FdDepartmentDao {
    public FdDepartment getById(String id) throws SQLException;
    public List<FdDepartment> getAll() throws SQLException;
    public List<FdDepartment> getByType(int type) throws SQLException;
}
