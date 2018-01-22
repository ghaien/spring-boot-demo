package com.ghaien.dao;

import com.ghaien.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ghaien on 2018/1/19.
 */
public interface UserDao {

    List<User> queryAll();

    User queryByUserName(@Param(value = "userName") String userName);
}
