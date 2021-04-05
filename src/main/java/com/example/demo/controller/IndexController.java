package com.example.demo.controller;

import com.example.demo.mapper.goodsTypeMapper;
import com.example.demo.pojo.GoodsType;
import com.example.demo.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/home")
public class IndexController {

    @Autowired
    private goodsTypeMapper goodsTypeMapper;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<GoodsType> getAll() {
        return goodsTypeMapper.getAll();
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ResponseBody
    public GoodsType getOne(@RequestParam("tid") int tid) {
        return goodsTypeMapper.getOne(tid);
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    @ResponseBody
    public String addGoodsType(
            @RequestParam(value = "tid") int tid,
            @RequestParam(value = "typeName") String typeName,
            @RequestParam(value = "createtime", required = false) Date createtime,
            @RequestParam(value = "orderseq", defaultValue = "0") int orderseq,
            @RequestParam(value = "bannerimg", required = false) String bannerimg,
            @RequestParam(value = "typeimg", required = false) String typeimg) {
        goodsTypeMapper.addGoodsType(new GoodsType(tid, typeName, createtime, orderseq, bannerimg, typeimg));
        return "添加成功";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(
            @RequestParam(value = "tid", required = false) int tid,
            @RequestParam(value = "typeName") String typeName,
            @RequestParam(value = "createtime", required = false) Date createtime,
            @RequestParam(value = "orderseq", required = false) int orderseq,
            @RequestParam(value = "bannerimg", required = false) String bannerimg,
            @RequestParam(value = "typeimg", required = false) String typeimg
    ) {
        GoodsType goodsType = new GoodsType(tid, typeName, createtime, orderseq, bannerimg, typeimg);
        goodsTypeMapper.updateGoodsType(goodsType);
        return "更新成功";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@RequestParam("tid") int tid) {
        goodsTypeMapper.deleteGoodsType(tid);
        return "删除成功";
    }



}
