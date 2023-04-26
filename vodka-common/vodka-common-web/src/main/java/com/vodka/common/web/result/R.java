package com.vodka.common.web.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Breeze
 * @date 2023/4/26 21:33
 * @description 统一返回对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class R<T> implements Serializable {

    private Integer code;

    private String msg;

    private Object data;
}
