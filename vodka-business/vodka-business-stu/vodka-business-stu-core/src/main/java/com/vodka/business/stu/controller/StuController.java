package com.vodka.business.stu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Breeze
 * @date 2023/4/23 19:55
 * @description
 */
@RestController
@RequestMapping("/stu")
public class StuController {

    @Value("${spring.datasource.url}")
    private String dbUrl;


    @GetMapping("/list")
    public Object list() {
        System.out.println("list..");
        System.out.println(dbUrl);

        return "stu list";
    }
}
