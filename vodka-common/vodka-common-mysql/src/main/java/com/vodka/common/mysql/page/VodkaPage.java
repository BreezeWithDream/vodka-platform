package com.vodka.common.mysql.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Breeze
 * @date 2023/9/6 22:33
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class VodkaPage {
    private Integer pageNum;

    private Integer pageSize;

    private Integer total;

    private Integer pages;
}
