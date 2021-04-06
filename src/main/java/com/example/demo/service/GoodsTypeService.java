package com.example.demo.service;

import com.example.demo.mapper.GoodsTypeMapper;
import com.example.demo.pojo.GoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Service
public class GoodsTypeService {

    @Autowired
    private GoodsTypeMapper goodsTypeMapper;


    public List<GoodsType> getAll() {
        return goodsTypeMapper.getAll();
    }

    public GoodsType getOne(@RequestParam("tid") int tid) {
        return goodsTypeMapper.getOne(tid);
    }

    public int addGoodsType(
            @RequestParam(value = "tid") int tid,
            @RequestParam(value = "typeName") String typeName,
            @RequestParam(value = "createtime", required = false) Date createtime,
            @RequestParam(value = "orderseq", defaultValue = "0") int orderseq,
            @RequestParam(value = "bannerimg", required = false) String bannerimg,
            @RequestParam(value = "typeimg", required = false) String typeimg
    ) {
        return goodsTypeMapper.addGoodsType(new GoodsType(tid, typeName, createtime, orderseq, bannerimg, typeimg));
    }

    public int updateGoodsType(
            @RequestParam(value = "tid", required = false) int tid,
            @RequestParam(value = "typeName") String typeName,
            @RequestParam(value = "createtime", required = false) Date createtime,
            @RequestParam(value = "orderseq", required = false) int orderseq,
            @RequestParam(value = "bannerimg", required = false) String bannerimg,
            @RequestParam(value = "typeimg", required = false) String typeimg
    ) {
        GoodsType goodsType = new GoodsType(tid, typeName, createtime, orderseq, bannerimg, typeimg);
        return goodsTypeMapper.updateGoodsType(goodsType);
    }
}
