package com.service.impl;

import com.entity.FdPatient;
import com.mongo.dao.FdPatientDao;
import com.service.FdPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class FdPatientServiceImpl implements FdPatientService {
    @Autowired
    private FdPatientDao fdPatientDao;

    @Override
    public FdPatient getById(String id) {
        return fdPatientDao.getById(id);
    }

    @Override
    public List<String> getMyRecord(String id) {
        return fdPatientDao.getById(id).getRecord();
    }

    @Override
    public List<FdPatient> getAll() {
        return fdPatientDao.getAll();
    }

    @Override
    public void add(FdPatient fdPatient) {
        fdPatientDao.add(fdPatient);
    }

    @Override
    public void update(FdPatient fdPatient) {
        if (getById(fdPatient.getUserId()) == null) {
            add(fdPatient);
            return;
        }
        fdPatientDao.update(fdPatient);
    }

    @Override
    public void deleteById(String id) {
        fdPatientDao.deleteById(id);
    }
}
