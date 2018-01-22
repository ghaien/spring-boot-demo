package com.ghaien.utils.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by ghaien on 2018/1/22.
 */
@Getter
@Setter
public class ResponseData<T> implements Serializable {

    private static final long serialVersionUID = -1596277479268602839L;

    private static final Integer SUCCESS = 0;  // 成功代码
    /**
     * 成功或失败代码
     */
    private Integer code;
    /**
     * 需要返回的数据
     */
    private T data;
    /**
     * 成功或失败信息
     */
    private String message;

    public ResponseData() {
        this.code = SUCCESS;
    }

    public ResponseData(T data) {
        this.code = SUCCESS;
        this.data = data;
    }

    public ResponseData(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseData(String message, T data) {
        this.code = SUCCESS;
        this.message = message;
        this.data = data;
    }
}
