package com.vodka.common.web.aspect;

import com.vodka.common.core.listener.exception.IExceptionListener;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Breeze
 * @date 2023/9/17 14:27
 * @description
 */
@Slf4j
@Aspect
public class ControllerExceptionAspect {

    @Autowired(required = false)
    private List<IExceptionListener> exceptionListeners;

    /**
     * 针对@RestController注解标注的方法进行增强
     *
     * @param joinPoint joinPoint
     * @return result
     */
    @Around("@within(org.springframework.web.bind.annotation.RestController)")
    public Object handleControllerException(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;

        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            if (exceptionListeners != null) {
                for (IExceptionListener exceptionListener : exceptionListeners) {
                    exceptionListener.handleException(throwable);
                }
            }
            throw throwable;
        }


        return result;
    }
}
