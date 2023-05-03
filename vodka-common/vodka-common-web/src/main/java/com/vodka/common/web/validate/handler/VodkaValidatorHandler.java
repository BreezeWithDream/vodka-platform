package com.vodka.common.web.validate.handler;


import com.vodka.common.web.utils.VodkaAppContextUtil;
import com.vodka.common.web.validate.VodkaValid;
import com.vodka.common.web.validate.annotation.VodkaValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Breeze
 * @date 2023/4/27 23:05
 * @description 自定义校验注解处理类
 */
public class VodkaValidatorHandler implements ConstraintValidator<VodkaValidator, Object> {


    private VodkaValidator vodkaValidator;


    @Override
    public void initialize(VodkaValidator vodkaValidator) {
        this.vodkaValidator = vodkaValidator;
    }

    /**
     * 核心校验方法
     *
     * @param value
     * @param context
     * @return 返回true： 校验通过， 返回false： 校验未通过
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        //
        if (value != null) {
            Class<? extends VodkaValid> validatorClz = vodkaValidator.validator();
            VodkaValid validator = VodkaAppContextUtil.getBean(validatorClz);
            if (validator == null) {
                return true;
            }
            return validator.isValid(vodkaValidator, value);
        }
        // 为空统统返回true， 如果为空， 就应该由@NotNull/@NotBlank等进行处理
        return true;
    }
}
