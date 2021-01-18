package com.open.capacity.display.exception;

import com.open.capacity.common.web.Result;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  全局异常处理控制器
 * @author DUJIN
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {


    /**
     * 参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 注意，这里返回类型是自定义响应体
        return Result.failedWith(objectError.getDefaultMessage(),1001,"参数校验失败");
    }

    /**
     * 自定义异常拦截
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public Result myExceptionHandler(CustomException e) {
        return Result.failedWith(e.getMessage(),1002, "业务异常");
    }

    /**
     * 通用异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result<String> exceptionHandler(Exception e) {
        // 注意哦，这里返回类型是自定义响应体
        return Result.failed("系统服务异常");
    }
}
