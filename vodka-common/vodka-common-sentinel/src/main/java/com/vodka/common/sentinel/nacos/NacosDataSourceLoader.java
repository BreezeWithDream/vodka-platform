package com.vodka.common.sentinel.nacos;

import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.property.SentinelProperty;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.vodka.common.sentinel.properties.NacosDataSourceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.List;
import java.util.Properties;

/**
 * @author Breeze
 * @date 2023/9/17 15:55
 * @description
 */
@Slf4j
public class NacosDataSourceLoader implements CommandLineRunner {

    @Autowired
    private NacosDataSourceProperties nacosDataSourceProperties;

    @Override
    public void run(String... args) throws Exception {

        log.info("reading flow rule form nacos");

        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, nacosDataSourceProperties.getServerAddress());
        properties.put(PropertyKeyConst.NAMESPACE, nacosDataSourceProperties.getNamespace());
        NacosDataSource<List<FlowRule>> nacosDataSourceFlow = new NacosDataSource<>(properties, nacosDataSourceProperties.getGroupId(), nacosDataSourceProperties.getFlowDataId(), json -> JSONObject.parseObject(json, new TypeReference<List<FlowRule>>() {
        }));
        SentinelProperty<List<FlowRule>> flowProperty = nacosDataSourceFlow.getProperty();


        FlowRuleManager.register2Property(flowProperty);


        // 熔断降级
        NacosDataSource<List<DegradeRule>> nacosDataSourceDegrade = new NacosDataSource<>(properties,
                nacosDataSourceProperties.getGroupId(), nacosDataSourceProperties.getDegradeDataId(), json -> JSONObject.parseObject(json, new TypeReference<List<DegradeRule>>() {
        }));
        SentinelProperty<List<DegradeRule>> degradeProperty = nacosDataSourceDegrade.getProperty();
        DegradeRuleManager.register2Property(degradeProperty);

    }
}
