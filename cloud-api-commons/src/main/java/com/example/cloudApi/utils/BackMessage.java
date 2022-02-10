package com.example.cloudApi.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author VM
 * Date 2021/6/5 13:48
 * Description: 返回值包装类
 */
@Data
@ToString
@NoArgsConstructor
public class BackMessage<T> {
    private Integer code;
    private String message;
    private T data;

    public BackMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public BackMessage(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BackMessage(BackEnum backEnum) {
        this.code = backEnum.getCode();
        this.message = backEnum.getMessage();
        this.data = null;
    }

    public BackMessage(BackEnum backEnum, T data) {
        this.code = backEnum.getCode();
        this.message = backEnum.getMessage();
        this.data = data;
    }

    public BackMessage<T> successWithMessage(String message) {
        setCode(200);
        setMessage(message);
        setData(null);
        return this;
    }

    public BackMessage<T> successWithMessageAndData(String message, T data) {
        setCode(200);
        setMessage(message);
        setData(data);
        return this;
    }

    public BackMessage<T> failureWithMessage(String message) {
        setCode(400);
        setMessage(message);
        setData(null);
        return this;
    }
}
