package com.vodka.business.stu.annotation;

import com.vodka.business.stu.handler.VodkaValidHandler;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.groups.Default;
import java.lang.annotation.*;

/**
 * @author Breeze
 * @date 2023/4/27 22:54
 * @description 自定义校验注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@Constraint(validatedBy = VodkaValidHandler.class)    // 自定义校验注解必须标注的类, validateBy用于指定具体使用的校验类
public @interface VodkaValid {
    /**
     * 校验失败后的信息
     *
     * @return message
     */
    String message() default "校验未通过";

    /**
     * 分组信息
     * eg:
     * 一个input对象被多个操作共用， 比如： 添加和修改 都用这一个类
     * insert的时候不需要id， update的时候id不能为空
     * 这个时候就需要进行分组
     *
     * @return Class数组
     */
    Class<?>[] groups() default {};

    /**
     * 校验的负载---源数据
     *
     * @return
     */
    Class<? extends Payload>[] payload() default {};

}
