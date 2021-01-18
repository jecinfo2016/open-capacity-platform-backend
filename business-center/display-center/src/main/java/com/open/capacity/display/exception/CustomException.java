package com.open.capacity.display.exception;

/**
 * @author DUJIN
 * @Classname CustomException
 * @Description 自定义异常类
 * @Date 2020/8/25 13:50
 */
public class CustomException extends RuntimeException {
    public CustomException() {
    }

    public CustomException(String msg) {
        super(msg);
    }
}
