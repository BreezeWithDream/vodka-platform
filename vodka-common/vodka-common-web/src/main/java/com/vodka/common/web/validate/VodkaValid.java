package com.vodka.common.web.validate;

import com.vodka.common.web.validate.annotation.VodkaValidator;

/**
 * @author Breeze
 * @date 2023/5/3 21:26
 * @description 对外开放接口
 */
public interface VodkaValid<T> {

    public boolean isValid(VodkaValidator vodkaValidator, T value);
}
