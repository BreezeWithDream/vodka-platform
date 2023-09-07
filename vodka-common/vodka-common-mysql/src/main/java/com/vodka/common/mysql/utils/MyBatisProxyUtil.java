package com.vodka.common.mysql.utils;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

/**
 * @author Breeze
 * @date 2023/9/7 22:41
 * @description
 */
public class MyBatisProxyUtil {

    /**
     * 根据目标对象, 获取没有代理过的源对象, 因为代理可能会嵌套(AOP嵌套)
     *
     * @param targetObj 目标对象
     * @return 没有代理过的源对象
     */
    public static Object getNoProxyObject(Object targetObj) {
        MetaObject metaObject = SystemMetaObject.forObject(targetObj);
        String proxyUniqueField = "h.target";
        while (metaObject.hasGetter(proxyUniqueField)) {
            targetObj = metaObject.getValue(proxyUniqueField);
            metaObject = SystemMetaObject.forObject(targetObj);
        }
        return targetObj;
    }
}
