package com.vodka.common.mysql.advice;

import com.vodka.common.base.page.VodkaPage;
import com.vodka.common.base.result.R;
import com.vodka.common.mysql.page.VodkaPageContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * @author Breeze
 * @date 2023/9/10 14:02
 * @description 用于处理返回的请求体, 对请求体进行增强, 因为Inteceptor主要用于处理ModelAndView, 对json数据的处理不太方便， 因此使用ResponseBodyAdvice进行增强
 */
@Slf4j
@RestControllerAdvice
public class PageResponseBodyAdvice implements ResponseBodyAdvice<R> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return Objects.requireNonNull(methodParameter.getMethod()).getReturnType() == R.class;
    }

    @Override
    public R beforeBodyWrite(R responseBody, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 判断分页上下文中是否包含page对象
        VodkaPage page = VodkaPageContext.getPage();
        log.info("page in response: {}", page);
        if (page != null) {
            responseBody.setPage(page);
        }
        return responseBody;
    }

}
