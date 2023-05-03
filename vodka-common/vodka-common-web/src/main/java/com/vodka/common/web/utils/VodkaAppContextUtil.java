package com.vodka.common.web.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Breeze
 * @date 2023/5/3 21:13
 * @description ApplicationContext工具类
 */
public class VodkaAppContextUtil implements ApplicationContextAware {

    public static ApplicationContext applicationContext;


    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        VodkaAppContextUtil.applicationContext = applicationContext;
    }
}
