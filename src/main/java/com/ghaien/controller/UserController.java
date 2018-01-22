package com.ghaien.controller;

import com.ghaien.entity.User;
import com.ghaien.service.UserService;
import com.ghaien.utils.bean.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ghaien on 2018/1/22.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Integer USER_ERROR = 10000;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/all")
    public ResponseData<List<User>> getAll() {
        return new ResponseData<>(userService.getAll());
    }

    @GetMapping(value = "/by/{userName}")
    public ResponseData<User> getUserByName(@PathVariable("userName") String userName) {
        User user = userService.getUserByName(userName);
        if (user == null) {
            return new ResponseData<>(USER_ERROR, "该用户不存在");
        }
        return new ResponseData<>("查询成功",
                userService.getUserByName(userName));
    }
}
