package com.open.capacity.backup.exception;

/**
 * 自定义异常-API调用异常
 * @author Jk
 */
public class ApiException extends RuntimeException {
    private String message;

    public ApiException() {
        super();
    }

    public ApiException(String message) {
        super(message);
        this.message = message;
    }
}
