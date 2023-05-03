package com.vodka.business.stu.controller;

import com.vodka.business.stu.input.StuInput;
import com.vodka.common.web.result.R;
import com.vodka.common.web.result.RUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Breeze
 * @date 2023/4/23 19:55
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/stu")
public class StuController {

    @Value("${spring.datasource.url}")
    private String dbUrl;


    @GetMapping("/list")
    public Object list() {
        int a = 1 / 0;

        return "stu list";
    }

    @PostMapping("/insert")
    public R insert(@Valid @RequestBody StuInput stuInput) {
        log.info("[stu-info]---{}", stuInput);
        return RUtil.success();
    }
}
