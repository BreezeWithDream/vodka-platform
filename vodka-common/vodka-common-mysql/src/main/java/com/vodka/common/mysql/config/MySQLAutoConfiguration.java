package com.vodka.common.mysql.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Breeze
 * @date 2023/5/4 22:35
 * @description DB自动配置类
 */
@Configuration
@MapperScan("com.vodka.data.mapper")
@EnableTransactionManagement
public class MySQLAutoConfiguration {
}
