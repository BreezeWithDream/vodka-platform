package com.vodka.common.sentinel.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Breeze
 * @date 2023/9/17 16:31
 * @description
 */
@Data
@ConfigurationProperties(prefix = "spring.cloud.sentinel.nacos")
public class NacosDataSourceProperties {

    private String serverAddress;

    private String namespace;

    private String groupId;

    private String flowDataId;

    private String degradeDataId;

}
