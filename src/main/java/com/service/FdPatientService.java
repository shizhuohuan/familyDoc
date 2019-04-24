package com.service;

import com.entity.FdPatient;

import java.util.List;

public interface FdPatientService {
    public FdPatient getById(String id);
    public List<String> getMyRecord(String id);
    public List<FdPatient> getAll();
    public void add(FdPatient fdPatient) ;
    public void update(FdPatient fdPatient);
    public void deleteById(String id);
}
