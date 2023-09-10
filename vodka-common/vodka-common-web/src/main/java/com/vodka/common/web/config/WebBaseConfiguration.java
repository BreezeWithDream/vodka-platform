package com.vodka.common.web.config;

import com.vodka.common.web.exception.GlobalException;
import com.vodka.common.web.utils.VodkaAppContextUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Breeze
 * @date 2023/4/26 21:53
 * @description Web层自动装配配置类
 */
@Configuration
public class WebBaseConfiguration {

    /**
     * 注册全局异常处理类组件到容器中
     *
     * @return GlobalException
     */
    @Bean
    public GlobalException globalException() {
        return new GlobalException();
    }

    /**
     * 将ApplicationContext工具类注册到容器中
     *
     * @return VodkaAppContextUtil对象
     */
    @Bean
    public VodkaAppContextUtil vodkaAppContextUtil() {
        return new VodkaAppContextUtil();
    }

    /**
     * 注册WebInterceptor注册器到容器中
     *
     * @return WebInterceptorRegister
     */
    @Bean
    public WebInterceptorRegister webInterceptorRegister() {
        return new WebInterceptorRegister();
    }

}
