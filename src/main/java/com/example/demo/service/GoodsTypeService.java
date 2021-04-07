package com.example.demo.service;

import com.example.demo.mapper.GoodsTypeMapper;
import com.example.demo.pojo.GoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class GoodsTypeService {

    @Autowired
    private GoodsTypeMapper goodsTypeMapper;


    public List<GoodsType> getAllType() {
        return goodsTypeMapper.getAllType();
    }

    public GoodsType getOne(@RequestParam("tid") int tid) {
        return goodsTypeMapper.getOne(tid);
    }

    public int addGoodsType(int tid, String typeName, int orderseq, String bannerimg, String typeimg
    ) {
        return goodsTypeMapper.addGoodsType(new GoodsType(0, typeName, orderseq, bannerimg, typeimg));
    }

    public int updateGoodsType(
            int tid, String typeName,
            int orderseq, String bannerimg, String typeimg
    ) {
        GoodsType goodsType = new GoodsType(tid, typeName, orderseq, bannerimg, typeimg);
        return goodsTypeMapper.updateGoodsType(goodsType);
    }
}
