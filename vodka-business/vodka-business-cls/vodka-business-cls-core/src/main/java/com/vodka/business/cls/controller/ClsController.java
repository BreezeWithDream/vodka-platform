package com.vodka.business.cls.controller;

import com.vodka.business.cls.service.ClsService;
import com.vodka.common.base.result.R;
import com.vodka.common.base.result.RUtil;
import com.vodka.data.entity.Cls;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author Breeze
 * @date 2023/9/10 17:01
 * @description
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/cls")
public class ClsController {

    @Autowired
    private ClsService clsService;

    @GetMapping("/getByCId")
    public R<Cls> getById(@NotNull(message = "cid不能为空") Integer cId) {
        Cls cls = clsService.getById(cId);
        return RUtil.success(cls);
    }
}
