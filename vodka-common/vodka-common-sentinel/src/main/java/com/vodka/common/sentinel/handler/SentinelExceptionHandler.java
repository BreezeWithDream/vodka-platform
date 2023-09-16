package com.vodka.common.sentinel.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.fastjson.JSONObject;
import com.vodka.common.base.result.Codes;
import com.vodka.common.base.result.R;
import com.vodka.common.base.result.RUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author Breeze
 * @date 2023/9/16 17:58
 * @description Sentinel 异常处理类
 */
public class SentinelExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {

        R result = null;

        if (e instanceof FlowException) {
            result = RUtil.create(Codes.FLOW_ERROR);
        } else {
            result = RUtil.create(Codes.FAIL);
        }

        String resultJson = JSONObject.toJSONString(result);

        response.setStatus(result.getCode());
        response.setContentType("application/json;charset=utf-8");

        PrintWriter writer = response.getWriter();
        writer.print(resultJson);


    }
}
