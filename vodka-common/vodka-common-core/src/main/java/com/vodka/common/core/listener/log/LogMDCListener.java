package com.vodka.common.core.listener.log;

import org.slf4j.MDC;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/**
 * @author Breeze
 * @date 2023/4/25 23:02
 * @description LogMDC监听器
 */
public class LogMDCListener implements GenericApplicationListener {

    private final String APPLICATION_CONFIG_PROPERTIES = "configurationProperties";

    private final String APPLICATION_NAME_PROPERTY = "spring.application.name";

    @Override
    public boolean supportsEventType(ResolvableType resolvableType) {
        return ApplicationEnvironmentPreparedEvent.class == resolvableType.getRawClass();
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        ApplicationEnvironmentPreparedEvent environmentPreparedEvent = (ApplicationEnvironmentPreparedEvent) applicationEvent;

        ConfigurableEnvironment environment = environmentPreparedEvent.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();

        PropertySource<?> propertySource = propertySources.get(APPLICATION_CONFIG_PROPERTIES);
        String applicationName = (String) propertySource.getProperty(APPLICATION_NAME_PROPERTY);

        // 将当前应用名称放入MDC中
        MDC.put("logName", applicationName);
        MDC.put("logPath", applicationName);


    }

    /**
     * 设置监听器的优先级
     *
     * @return 优先级
     */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 19;
    }
}
