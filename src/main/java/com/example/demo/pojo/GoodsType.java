package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsType {
    private int tid;
    private String typeName;
    private int orderseq;
    private String bannerimg;
    private String typeimg;
}
