package com.service.impl;

import com.dao.FdHistoryDao;
import com.entity.FdHistory;
import com.service.FdHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
public class FdHistoryServiceImpl implements FdHistoryService {
    @Autowired
    private FdHistoryDao fdHistoryDao;

    @Override
    public List<FdHistory> getBySendId(String sendId,String type,String confirm) throws SQLException {
        return fdHistoryDao.getBySendId(sendId,type,confirm);
    }

    @Override
    public List<FdHistory> getByReciveId(String reciveId,String type,String confirm) throws SQLException {
        return fdHistoryDao.getByReciveId(reciveId,type,confirm);
    }

    @Override
    public void add(FdHistory fh) throws SQLException {
        fdHistoryDao.add(fh);
    }

    @Override
    public void confirm(String id) throws SQLException {
        fdHistoryDao.confirm(id);
    }

    @Override
    public List<FdHistory> getBySendIdWithoutType(String sendId, String confirm) throws SQLException {
        return fdHistoryDao.getBySendIdWithoutType(sendId,confirm);
    }

    @Override
    public List<FdHistory> getChatHistory(String sendId, String recId, String confirm) throws SQLException {
        return fdHistoryDao.getChatHistory(sendId,recId,confirm);
    }
}
