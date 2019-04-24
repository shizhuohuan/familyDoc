package com.service;

import com.entity.FdQuession;

import java.util.List;

public interface FdQuessionService  {
    public void add(FdQuession quession);
    public void update(FdQuession quession);
    public List<FdQuession> getByUserId(String userId);
    public FdQuession getById(String id);
    public List<FdQuession> getByDptId(String dptId);
    public List<FdQuession> getAll();
    public void delete(String id);
}
