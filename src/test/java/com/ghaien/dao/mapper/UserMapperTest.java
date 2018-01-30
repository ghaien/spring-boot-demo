package com.ghaien.dao.mapper;

import com.ghaien.dao.pojo.vo.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by ghaien on 2018/1/29.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void TestSelectOne() {
        User user = new User();
        user.setId(1L);
        user = userMapper.selectOne(user);
        log.info("userName = " + user.getUserName());
    }

    @Test
    public void testQueryById() {
        User user = userMapper.queryById(1L);
        log.info("userName = " + user.getUserName());
    }

    @Test
    public void testQueryByPage() {
//        PageHelper.startPage(2, 1);
//        List<User> users = userMapper.selectAll();
//        for (User user : users) {
//            log.info("userName = " + user.getUserName());
//        }

        Page<User> page = PageHelper.startPage(2, 1).doSelectPage(() -> {
            userMapper.selectAll();
        });
        for (User user : page) {
            log.info("userName = " + user.getUserName());
        }
    }

}