package com.SpringShop.demo.Data;

public interface DesignRepository {
    Iterable<Design> findAll();
    Design findById(Long id);
    Design save(Design design);
}
