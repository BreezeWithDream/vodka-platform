package com.vodka.common.web.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @author Breeze
 * @date 2023/9/16 15:28
 * @description
 */
public class RequestParameterInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("param", "value");
    }
}
