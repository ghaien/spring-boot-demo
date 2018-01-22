package com.ghaien.dao;

import com.ghaien.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by ghaien on 2018/1/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDao userDao;

    @Test
    public void testQueryAll() {
        List<User> userList = userDao.queryAll();
        log.info("userList.size() = " + userList.size());
        for (User user : userList) {
            log.info("user.userName = " + user.getUserName());
        }
    }
}
