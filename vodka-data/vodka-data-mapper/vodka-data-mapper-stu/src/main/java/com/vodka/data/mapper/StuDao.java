package com.vodka.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vodka.data.entity.Stu;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学生表(Stu)表数据库访问层
 *
 * @author Breeze
 * @since 2023-05-04 21:53:21
 */
@Mapper
public interface StuDao extends BaseMapper<Stu> {

}

