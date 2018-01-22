package com.ghaien.service;

import com.ghaien.entity.User;

import java.util.List;

/**
 * Created by ghaien on 2018/1/19.
 */
public interface UserService {

    List<User> getAll();

    User getUserByName(String userName);
}
