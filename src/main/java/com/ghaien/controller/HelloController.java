package com.ghaien.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ghaien on 2018/1/19.
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/say")
    public String sayHello() {
        return "hello spring-boot";
    }
}
