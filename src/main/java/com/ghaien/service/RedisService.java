package com.ghaien.service;

/**
 * Created by ghaien on 2018/1/30.
 */
public interface RedisService {
    void put(String key, String value);

    String get(String key);
}
