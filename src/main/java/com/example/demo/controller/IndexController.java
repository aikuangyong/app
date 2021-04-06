package com.example.demo.controller;

import com.example.demo.pojo.GoodsType;
import com.example.demo.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/home")
public class IndexController {

    @Autowired
    private GoodsTypeService goodsTypeService;


    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<GoodsType> getAll() {
        return goodsTypeService.getAll();
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ResponseBody
    public GoodsType getOne(int tid) {
        System.out.println(tid);
        return goodsTypeService.getOne(tid);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addGoodsType(int tid, String typeName, Date createtime,
                               int orderseq, String bannerimg, String typeimg) {
        int ret = goodsTypeService.addGoodsType(tid, typeName, createtime, orderseq, bannerimg, typeimg);
        if (ret > 0) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(
            int tid, String typeName, Date createtime,
            int orderseq, String bannerimg, String typeimg
    ) {
        int res = goodsTypeService.updateGoodsType(tid, typeName, createtime, orderseq, bannerimg, typeimg);
        if (res > 0) {
            return "更新成功";
        } else {
            return "更新失败";
        }
    }
}
