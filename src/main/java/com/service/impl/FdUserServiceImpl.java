package com.service.impl;

import com.dao.FdUserDao;
import com.entity.FdUser;
import com.service.FdUserService;
import org.elasticsearch.common.UUIDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
public class FdUserServiceImpl implements FdUserService {
    @Autowired
    private FdUserDao fdUserDao;
    @Override
    public FdUser getByName(String username) throws SQLException {
        return fdUserDao.getByUserName(username);
    }

    @Override
    public List<FdUser> getAll() throws SQLException {
        return fdUserDao.getAll();
    }

    @Override
    public List<FdUser> getByIdentity(String identity) throws SQLException {
        return fdUserDao.getByIdentity(identity);
    }

    @Override
    public void add(FdUser fdUser) throws SQLException {
        fdUser.setId(UUIDs.randomBase64UUID());
        fdUserDao.add(fdUser);
    }

    @Override
    public void update(FdUser fdUser) throws SQLException {
        fdUserDao.update(fdUser);
    }

    @Override
    public FdUser getById(String id) throws SQLException {
        return fdUserDao.getById(id);
    }
}
