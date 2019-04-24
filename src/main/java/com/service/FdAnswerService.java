package com.service;

import com.entity.FdAnswer;

import java.sql.SQLException;
import java.util.List;

public interface FdAnswerService {
    public FdAnswer getById(String id) throws SQLException;
    public List<FdAnswer> getByQsId(String qsId) throws SQLException;
    public void add(FdAnswer answer) throws SQLException;
    public List<FdAnswer> getByUserId(String userId) throws SQLException;
    public void delete(String id) throws SQLException;
}
