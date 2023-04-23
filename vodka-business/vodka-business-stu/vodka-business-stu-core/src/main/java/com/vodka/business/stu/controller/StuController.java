package com.vodka.business.stu.controller;

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
    @GetMapping("/list")
    public Object list() {
        System.out.println("list..");
        return "stu list";
    }
}
