package com.example.demo.controller;

import com.example.demo.pojo.GoodsType;
import com.example.demo.service.GoodsTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "商品类型控制类")
@RestController
@RequestMapping("/goodsType")
public class GoodsTypeController {
    @Autowired
    private GoodsTypeService goodsTypeService;


    @ApiOperation("获取所有商品类型")
    @GetMapping("/getAll")
    public List<GoodsType> getAllType() {
        return goodsTypeService.getAllType();
    }

    @ApiOperation("获取单个商品类型")
    @GetMapping("/getOne")
    public GoodsType getOne(int tid) {
        System.out.println(tid);
        return goodsTypeService.getOne(tid);
    }


    @ApiOperation("添加商品类型")
    @PostMapping("/add")
    public Map<String, String> addGoodsType(@RequestParam(value = "typeName") String typeName,
                                            @RequestParam(value = "orderseq", defaultValue = "0") int orderseq,
                                            @RequestParam(value = "bannerimg", required = false) String bannerimg,
                                            @RequestParam(value = "typeimg", required = false) String typeimg) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("typeName", typeName);
        int ret = goodsTypeService.addGoodsType(0, typeName, orderseq, bannerimg, typeimg);
        if (ret > 0) {
            hashMap.put("status", "添加成功");
            return hashMap;
        } else {
            hashMap.put("status", "添加失败");
            return hashMap;
        }
    }

    @ApiOperation("更新商品类型")
    @PostMapping("/update")
    public Map<String, String> update( @RequestParam(value = "tid", required = false) int tid,
                                       @RequestParam(value = "typeName") String typeName,
                                       @RequestParam(value = "orderseq", required = false, defaultValue = "0") int orderseq,
                                       @RequestParam(value = "bannerimg", required = false) String bannerimg,
                                       @RequestParam(value = "typeimg", required = false) String typeimg

    ) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("typeName", typeName);
        int res = goodsTypeService.updateGoodsType(tid, typeName, orderseq, bannerimg, typeimg);
        if (res > 0) {
            hashMap.put("status", "更新成功");
            return hashMap;
        } else {
            hashMap.put("status", "更新失败");
            return hashMap;
        }
    }
}
