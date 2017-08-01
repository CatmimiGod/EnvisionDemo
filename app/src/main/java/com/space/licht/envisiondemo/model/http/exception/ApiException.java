package com.space.licht.envisiondemo.model.http.exception;

/**
 *  ApiException  自定义异常
 */
public class ApiException extends Exception {
    public ApiException(String msg)
    {
        super(msg);
    }
}
