package com.ghaien.service.impl;

import com.ghaien.dao.UserDao;
import com.ghaien.entity.User;
import com.ghaien.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ghaien on 2018/1/19.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAll() {
        return userDao.queryAll();
    }

    @Override
    public User getUserByName(String userName) {
        return userDao.queryByUserName(userName);
    }
}
