package com.vodka.business.stu.controller;

import com.vodka.business.stu.input.StuInput;
import com.vodka.business.stu.service.StuService;
import com.vodka.common.mysql.page.VodkaPageContext;
import com.vodka.common.web.result.R;
import com.vodka.common.web.result.RUtil;
import com.vodka.data.entity.Stu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Breeze
 * @date 2023/4/23 19:55
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/stu")
public class StuController {


    @Autowired
    private StuService stuService;

    @GetMapping("/list")
    public Object list() {
        log.info("学生列表查询");
        VodkaPageContext.setPage(1, 1);
        List<Stu> stuList = stuService.list();

        return RUtil.success(stuList);
    }

    @PostMapping("/insert")
    public R insert(@Valid @RequestBody StuInput stuInput) {
        log.info("[stu-info]---{}", stuInput);
        return RUtil.success();
    }
}
