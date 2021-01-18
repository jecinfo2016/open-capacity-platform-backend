package com.open.capacity.backup.exception;

import com.open.capacity.common.web.Result;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Jk
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * 参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return Result.failed(objectError.getDefaultMessage());
    }

    /**
     * API调用异常
     * @param e
     * @return
     */
    @ExceptionHandler(ApiException.class)
    public Result APIExceptionHandler(ApiException e) {
        return Result.failed(e.getMessage());
    }

    /**
     * 运行时异常
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public Result RunTimeExceptionHandler(RuntimeException e) {
        return Result.failed(e.getMessage());
    }
}
