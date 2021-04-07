package com.example.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "hello控制类")
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }
}
