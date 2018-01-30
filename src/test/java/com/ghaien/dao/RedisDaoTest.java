package com.ghaien.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ghaien on 2018/1/30.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisDaoTest {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisDao redisDao;

    @Test
    public void get() throws Exception {

        log.info("first = " + redisDao.get("first"));
    }

}