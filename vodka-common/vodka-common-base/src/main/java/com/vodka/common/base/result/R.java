package com.vodka.common.base.result;

import com.vodka.common.base.page.BaseReturnPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Breeze
 * @date 2023/4/26 21:33
 * @description 统一返回对象(包含分页信息对象)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class R<T> extends BaseReturnPage {

    private Integer code;

    private String msg;

    private Object data;
}
