package com.ghaien.utils.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by ghaien on 2018/1/29.
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
