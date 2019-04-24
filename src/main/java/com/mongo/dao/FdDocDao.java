package com.mongo.dao;

import com.entity.FdDoc;
import com.entity.FdRecord;

import java.sql.SQLException;
import java.util.List;

public interface FdDocDao {
    public FdDoc getById(String id) throws SQLException;
    public List<FdDoc> getAll() throws SQLException;
    public List<String> getMyRecord(String id) throws SQLException;
    public List<FdDoc> getByDptId(String dptId) throws SQLException;
    public void add(FdDoc fdDoc) ;
    public void update(FdDoc fdDoc);
    public void deleteById(String id);
}
