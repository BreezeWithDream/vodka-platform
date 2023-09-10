package com.vodka.common.base.page;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Breeze
 * @date 2023/9/10 13:59
 * @description BaseReturnPage, 返回给前端参数中， 包含page对象, R对象继承于该类
 */
@Data
public class BaseReturnPage implements Serializable {
    /**
     * page对象
     */
    private VodkaPage page;
}
