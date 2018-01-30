package com.ghaien.service.impl;

import com.ghaien.service.RedisService;
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
public class RedisServiceImplTest {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisService redisService;

    @Test
    public void put() throws Exception {

        redisService.put("first", "hello");
    }

    @Test
    public void get() throws Exception {
        log.info("first = " + redisService.get("first"));
    }

}