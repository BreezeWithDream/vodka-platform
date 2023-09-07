package com.vodka.common.mysql.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Breeze
 * @date 2023/9/5 22:38
 * @description
 */
@Data
@ConfigurationProperties(prefix = "vodka.plugins.sql")
public class SQLPluginConfigProperties {

    private Record record;
    private Page page;

    @Data
    private static class Record {
        private boolean enable;
    }

    @Data
    private static class Page {
        /**
         * 默认开启
         */
        private boolean enable = true;
    }
}
