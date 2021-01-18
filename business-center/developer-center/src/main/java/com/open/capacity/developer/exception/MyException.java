package com.open.capacity.developer.exception;

/**
 * @author DUJIN
 * @Classname MyException
 * @Description 自定义异常类
 * @Date 2020/8/25 13:50
 */
public class MyException extends RuntimeException{
    public MyException() {
    }

    public MyException(String msg) {
        super(msg);
    }
}
