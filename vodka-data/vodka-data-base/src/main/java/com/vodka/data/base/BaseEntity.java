package com.vodka.data.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Breeze
 * @date 2023/5/4 22:29
 * @description
 */
@Data
public class BaseEntity implements Serializable {


    private static final long serialVersionUID = 1L;
    //创建时间
    private Date createTime = new Date();
    //修改时间
    private Date updateTime = new Date();
    //状态
    private Integer status=0;
    //删除标识 0-可用 1-删除
    private Integer delFlag=0;
}
