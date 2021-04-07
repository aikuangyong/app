package com.example.demo.controller;

import com.example.demo.pojo.Goods;
import com.example.demo.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "商品控制类")
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;


    @ApiOperation("获取所有商品")
    @GetMapping("/getGoods")
    public List<Goods> getAllGoods() {
        return goodsService.getAllGoods();
    }

}
