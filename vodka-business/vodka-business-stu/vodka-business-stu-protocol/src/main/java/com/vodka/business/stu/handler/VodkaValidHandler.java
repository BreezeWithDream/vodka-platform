package com.vodka.business.stu.handler;

import com.vodka.business.stu.annotation.VodkaValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Breeze
 * @date 2023/4/27 23:05
 * @description 自定义校验注解处理类
 */
public class VodkaValidHandler implements ConstraintValidator<VodkaValid, String> {
    /**
     * 核心校验方法
     *
     * @param value
     * @param context
     * @return 返回true： 校验通过， 返回false： 校验未通过
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //
        if (value != null) {
            if ("男".equals(value.trim()) || "女".equals(value.trim())) {
                return true;
            }else {
                return false;
            }
        }
        // 为空统统返回true， 如果为空， 就应该由@NotNull/@NotBlank等进行处理
        return true;
    }
}
