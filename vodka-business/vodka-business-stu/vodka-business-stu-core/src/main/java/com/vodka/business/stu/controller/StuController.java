package com.vodka.business.stu.controller;

import com.vodka.business.feign.cls.ClsFeign;
import com.vodka.business.stu.input.StuInput;
import com.vodka.business.stu.service.StuService;
import com.vodka.common.base.result.R;
import com.vodka.common.base.result.RUtil;
import com.vodka.data.entity.Cls;
import com.vodka.data.entity.Stu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RefreshScope
@RequestMapping("/stu")
public class StuController {


    @Autowired
    private StuService stuService;

    @Autowired
    private ClsFeign clsFeign;

    @GetMapping("/list")
    public R list() {
        log.info("学生列表查询");
        List<Stu> stuList = stuService.list();

        return RUtil.success(stuList);
    }

    @PostMapping("/insert")
    public R insert(@Valid @RequestBody StuInput stuInput) {
        log.info("[stu-info]---{}", stuInput);
        return RUtil.success();
    }

    @GetMapping("/getBySId")
    public R getBySId(@RequestParam("sId") Long sId) {
        Stu stu = stuService.getById(sId);

        Long cId = stu.getCId();
        log.info("cId: {}", cId);
        R<Cls> result = clsFeign.getClsByCId(cId);
        log.info("cls: {}", result.getData());

        return RUtil.success(stu);

    }
}
