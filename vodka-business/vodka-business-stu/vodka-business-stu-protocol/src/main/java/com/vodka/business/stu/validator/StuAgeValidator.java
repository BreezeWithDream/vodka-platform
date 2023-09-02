package com.vodka.business.stu.validator;

import com.vodka.business.stu.input.StuInput;
import com.vodka.common.web.validate.VodkaValid;
import com.vodka.common.web.validate.annotation.VodkaValidator;
import org.springframework.stereotype.Component;

/**
 * @author Breeze
 * @date 2023/5/3 21:37
 * @description 自定义StuInput年龄校验器， 实现VodkaValid接口， 并加入到容器中
 */
@Component
public class StuAgeValidator implements VodkaValid<StuInput> {
    @Override
    public boolean isValid(VodkaValidator vodkaValidator, StuInput value) {
        // ...校验逻辑
        return true;
    }
}
