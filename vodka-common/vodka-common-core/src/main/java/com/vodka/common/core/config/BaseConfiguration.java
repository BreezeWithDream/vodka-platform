package com.vodka.common.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Breeze
 * @date 2023/4/23 20:11
 * @description
 */
@Configuration
@ComponentScan(basePackages = {"com.vodka.business"})
public class BaseConfiguration {
}
