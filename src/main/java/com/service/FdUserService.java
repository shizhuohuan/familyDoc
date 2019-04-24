package com.service;

import com.entity.FdUser;

import java.sql.SQLException;
import java.util.List;

public interface FdUserService {
    public FdUser getByName(String username)throws SQLException;
    public List<FdUser> getAll() throws SQLException;
    public List<FdUser> getByIdentity(String identity) throws SQLException;
    public void add(FdUser fdUser) throws SQLException;
    public void update(FdUser fdUser) throws SQLException;
    public FdUser getById(String id) throws SQLException;
}
