package com.example.demo.mapper;

import com.example.demo.pojo.GoodsType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsTypeMapper {
    List<GoodsType> getAllType();
    GoodsType getOne(int tid);
    int addGoodsType(GoodsType goodsType);
    int updateGoodsType(GoodsType goodsType);
    int deleteGoodsType(int tid);
}
