package com.vodka.common.sentinel.listener;

import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.vodka.common.core.listener.exception.IExceptionListener;

/**
 * @author Breeze
 * @date 2023/9/17 14:39
 * @description
 */
public class SentinelExceptionListener implements IExceptionListener {
    @Override
    public void handleException(Throwable throwable) {
        // 如果不是Sentinel自己本身的异常， 已经记录
        if (!BlockException.isBlockException(throwable)) {
            Tracer.trace(throwable);
        }
    }
}
