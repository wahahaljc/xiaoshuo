package com.example.mybatisplus.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ljc
 * @since 2021-04-03
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("test")
    public String test() {
        System.out.println("sdsdsd");
        return "test";
    }


}

