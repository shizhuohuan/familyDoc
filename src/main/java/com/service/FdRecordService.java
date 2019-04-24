package com.service;

import com.entity.FdRecord;

import java.sql.SQLException;
import java.util.List;

public interface FdRecordService {
    public FdRecord getById(String id) throws SQLException;
    public List<FdRecord> getAll() throws SQLException;
    public void add(FdRecord fdRecord) throws SQLException;
    public void update(FdRecord fdRecord) throws SQLException;
}
