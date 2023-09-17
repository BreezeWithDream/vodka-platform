package com.vodka.ability.gateway.handler;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.fastjson.JSONObject;
import com.vodka.ability.gateway.properties.NacosDataSourceProperties;
import com.vodka.common.base.result.Codes;
import com.vodka.common.base.result.R;
import com.vodka.common.base.result.RUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Breeze
 * @date 2023/9/16 18:43
 * @description
 */
@Slf4j
@Configuration
public class SentinelExceptionHandler implements BlockRequestHandler {


    @Bean
    public void init() {
        GatewayCallbackManager.setBlockHandler(this);
    }

    @Override
    public Mono<ServerResponse> handleRequest(ServerWebExchange exchange, Throwable throwable) {

        R result = null;

        if (throwable instanceof ParamFlowException) {
            log.info("触发网关流控, uri->{}", exchange.getRequest().getURI());
            result = RUtil.create(Codes.FLOW_ERROR);
        } else if (throwable instanceof DegradeException) {
            result = RUtil.create(Codes.DEGRADE_ERROR);
        } else {
            result = RUtil.create(Codes.FAIL);
        }

        return ServerResponse
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .header("content-type", "application/json;charset=utf-8")
                .bodyValue(JSONObject.toJSONString(result));
    }
}
