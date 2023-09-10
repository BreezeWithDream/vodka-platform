package com.vodka.common.web.exception;

import com.vodka.common.base.result.Codes;
import com.vodka.common.base.result.R;
import com.vodka.common.base.result.RUtil;
import com.vodka.common.web.utils.VodkaRequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Breeze
 * @date 2023/4/26 21:49
 * @description 统一异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalException {

    /**
     * 处理全局异常
     *
     * @param throwable throwable
     * @return  R
     */
    @ExceptionHandler(Throwable.class)
    public R exceptionHandler(Throwable throwable) {

        try {
            // 记录当前请求的URL
            HttpServletRequest request = VodkaRequestUtil.getHttpServletRequest();
            if (request != null) {
                String url = request.getRequestURL().toString();
                log.error("[Global Exception] - 全局异常信息 --- uri: " + url, throwable);
            }
        } catch (Exception e) {
            log.error("[Global Exception] - 全局异常信息 --- ", throwable);
        }

        return RUtil.create(Codes.FAIL);
    }

    @ExceptionHandler(BindException.class)
    public R handleBindException(BindException exception) {
        Set<String> errorSet = exception.getBindingResult().getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toSet());

        return RUtil.create(Codes.PARAM_ERROR, errorSet);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Set<String> errorSet = exception.getBindingResult().getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toSet());

        return RUtil.create(Codes.PARAM_ERROR, errorSet);
    }

    /**
     * 形参列表异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public R handleConstraintViolationException(ConstraintViolationException exception) {
        Set<String> errorSet = exception.getConstraintViolations().stream().map(constraintViolation -> constraintViolation.getMessage()).collect(Collectors.toSet());
        return RUtil.create(Codes.PARAM_ERROR, errorSet);
    }


}
