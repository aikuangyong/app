package com.example.demo.service;

import com.example.demo.mapper.GoodsMapper;
import com.example.demo.pojo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public List<Goods> getAllGoods(){
        return goodsMapper.getAllGoods();
    }
}
