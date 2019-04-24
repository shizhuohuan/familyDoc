package com.service.impl;

import com.dao.FdRecordDao;
import com.entity.FdRecord;
import com.service.FdRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
public class FdRecordServiceImpl implements FdRecordService {
    @Autowired
    private FdRecordDao fdRecordDao;
    @Override
    public FdRecord getById(String id) throws SQLException {
        return fdRecordDao.getById(id);
    }

    @Override
    public List<FdRecord> getAll() throws SQLException {
        return fdRecordDao.getAll();
    }

    @Override
    public void add(FdRecord fdRecord) throws SQLException {
//        if(StringUtils.isEmpty(fdRecord.getDiagnose())){
//            fdRecord.setDiagnose("");
//        }
//        if(StringUtils.isEmpty(fdRecord.getDocAdvice())){
//            fdRecord.setDocAdvice("");
//        }
//        if(StringUtils.isEmpty(fdRecord.getRemark())){
//            fdRecord.setRemark("");
//        }
        fdRecordDao.add(fdRecord);
    }

    @Override
    public void update(FdRecord fdRecord) throws SQLException {
        fdRecordDao.update(fdRecord);
    }
}
