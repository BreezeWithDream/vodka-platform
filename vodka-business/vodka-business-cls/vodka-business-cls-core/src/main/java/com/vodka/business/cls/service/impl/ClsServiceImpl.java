package com.vodka.business.cls.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vodka.business.cls.service.ClsService;
import com.vodka.data.entity.Cls;
import com.vodka.data.mapper.ClsDao;
import org.springframework.stereotype.Service;

/**
 * (Cls)表服务实现类
 *
 * @author makejava
 * @since 2023-09-16 13:47:00
 */
@Service("clsService")
public class ClsServiceImpl extends ServiceImpl<ClsDao, Cls> implements ClsService {

}
