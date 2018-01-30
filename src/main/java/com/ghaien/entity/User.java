package com.ghaien.entity;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ghaien on 2018/1/22.
 */
@Getter
@Setter
public class User implements Serializable {
    private static final long serialVersionUID = -5343460982269922412L;

    private Long id;

    private String userName;

    private String password;

    private Date createTime;

    private Date updateTime;

    private Integer enable;
}
