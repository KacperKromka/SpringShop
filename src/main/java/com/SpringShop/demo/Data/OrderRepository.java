package com.SpringShop.demo.Data;

import com.SpringShop.demo.Entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
