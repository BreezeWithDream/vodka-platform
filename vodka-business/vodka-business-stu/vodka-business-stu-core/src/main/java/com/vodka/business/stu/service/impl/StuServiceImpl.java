package com.vodka.business.stu.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vodka.business.stu.service.StuService;
import com.vodka.data.entity.Stu;
import com.vodka.data.mapper.StuDao;
import org.springframework.stereotype.Service;

/**
 * 学生表(Stu)表服务实现类
 *
 * @author Breeze
 * @since 2023-05-04 21:53:27
 */
@Service("stuService")
public class StuServiceImpl extends ServiceImpl<StuDao, Stu> implements StuService {

}

