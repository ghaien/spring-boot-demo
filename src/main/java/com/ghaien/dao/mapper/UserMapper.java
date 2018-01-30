package com.ghaien.dao.mapper;

import com.ghaien.dao.pojo.vo.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

public interface UserMapper extends BaseMapper<User> {
    /**
     * 测试UserMapper.xml文件路径是否正确
     * @param id
     * @return
     */
    User queryById(@Param("id") Long id);
}