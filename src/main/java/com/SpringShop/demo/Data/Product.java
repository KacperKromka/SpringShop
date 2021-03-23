package com.SpringShop.demo.Data;

import lombok.Data;

import java.util.List;

@Data
public class Product {


    private int id;
    private String name;
    private List<String> design;
}
