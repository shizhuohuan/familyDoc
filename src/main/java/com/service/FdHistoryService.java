package com.service;

import com.entity.FdHistory;

import java.sql.SQLException;
import java.util.List;

public interface FdHistoryService {
    public List<FdHistory> getBySendId(String sendId,String type,String confirm) throws SQLException;
    public List<FdHistory> getByReciveId(String reciveId,String type,String confirm) throws SQLException;
    public void add(FdHistory fh) throws SQLException;
    public void confirm(String id) throws SQLException;
    public List<FdHistory> getBySendIdWithoutType(String sendId,String confirm) throws  SQLException;
    public List<FdHistory> getChatHistory(String sendId,String recId,String confirm) throws SQLException;
}
