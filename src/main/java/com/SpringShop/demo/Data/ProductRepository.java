package com.SpringShop.demo.Data;

import com.SpringShop.demo.Entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
