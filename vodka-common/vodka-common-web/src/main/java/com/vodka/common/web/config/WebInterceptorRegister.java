package com.vodka.common.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.List;

/**
 * @author Breeze
 * @date 2023/9/10 13:03
 * @description
 */
@Slf4j
public class WebInterceptorRegister implements WebMvcConfigurer {

    /**
     * 从容器中获取HandlerInterceptorAdapter集合， 统一进行注册
     * 对于其他模块的Interceptor也可以进行注册，只需要他们将对应的Interceptor注册到容器中即可
     * 解耦合
     */
    @Autowired
    private List<HandlerInterceptorAdapter> handlerInterceptorAdapters;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (handlerInterceptorAdapters != null && handlerInterceptorAdapters.size() != 0) {
            log.info("当前容器中 handlerInterceptorAdapters : {}", handlerInterceptorAdapters);
            for (HandlerInterceptorAdapter handlerInterceptorAdapter : handlerInterceptorAdapters) {
                registry.addInterceptor(handlerInterceptorAdapter);
            }
        }
    }
}
