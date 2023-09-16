package com.vodka.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.vodka.data.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 学生表(Stu)表实体类
 *
 * @author Breeze
 * @since 2023-05-04 21:53:23
 */
@Data
@Accessors(chain = true)
@TableName("stu")
public class Stu extends BaseEntity {

    //主键
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    // 班级id
    private Long cId;

    //姓名
    private String name;
    //年龄
    private Integer age;
    //邮箱
    private String email;
    //生日
    private Date birthday;
    //性别 0-女 1-男
    private Integer sex;

}

