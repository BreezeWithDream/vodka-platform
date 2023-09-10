package com.vodka.business.cls.controller;

import com.vodka.common.base.result.R;
import com.vodka.common.base.result.RUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Breeze
 * @date 2023/9/10 17:01
 * @description
 */
@Slf4j
@RestController
public class StuController {

    @GetMapping("/getById")
    public R getById(Integer id) {
        return RUtil.success();
    }
}
