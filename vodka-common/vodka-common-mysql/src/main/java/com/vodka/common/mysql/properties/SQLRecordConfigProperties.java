package com.vodka.common.mysql.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Breeze
 * @date 2023/9/5 22:38
 * @description
 */
@Data
@ConfigurationProperties(prefix = "vodka.plugins.sql.record")
public class SQLRecordConfigProperties {

    private boolean enable;
}
