package com.vodka.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.vodka.data.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Breeze
 * @date 2023/9/10 16:53
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Cls extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String clsName;

    private Integer clsNum;

}
