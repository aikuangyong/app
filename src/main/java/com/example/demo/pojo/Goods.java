package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    private int id;
    private String goodsName;
    private String descreption;
    private double price;
    private String goodsimg;
    private String shopName;
    private GoodsType goodsType;

}
