package com.vodka.common.mysql.plugin;

import com.vodka.common.mysql.utils.MyBatisProxyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Statement;
import java.util.Locale;

/**
 * @author Breeze
 * @date 2023/9/5 22:07
 * @description SQL记录插件/拦截器
 */
@Slf4j
@Intercepts({
        // 对Statement的查询进行拦截
        @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        // 对更新/新增进行拦截
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class})})
public class SQLRecordPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 通过MyBatisProxyUtil 获取最终的根源对象， 而不是代理过之后的对象
        StatementHandler statementHandler = (StatementHandler) MyBatisProxyUtil.getNoProxyObject(invocation.getTarget());
        log.info("记录插件中的 statementHandler: {}", statementHandler.getClass().getSimpleName());
        // 获取目标sql语句
        String targetSql = statementHandler.getBoundSql().getSql().toLowerCase(Locale.ROOT).replaceAll("\n", "");


        // 记录耗时
        long start = System.currentTimeMillis();
        // 方形目标方法执行
        Object result = invocation.proceed();

        long end = System.currentTimeMillis();

        BigDecimal timeUsage = BigDecimal.valueOf(end).subtract(BigDecimal.valueOf(start)).divide(BigDecimal.valueOf(1000)).setScale(4, RoundingMode.DOWN);

        log.info("targetSql executed, sql: {} =====> timeUsage: {}", targetSql, timeUsage);

        return result;
    }
}
