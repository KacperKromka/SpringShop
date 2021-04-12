package com.SpringShop.demo.Data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository{

    private SimpleJdbcInsert orderInsert;
    private SimpleJdbcInsert orderProductInsert;
    private ObjectMapper objectMapper;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbc) {
        this.orderInsert = new SimpleJdbcInsert(jdbc)
                .withTableName("Product_Order")
                .usingGeneratedKeyColumns("id");

        this.orderProductInsert = new SimpleJdbcInsert(jdbc)
                .withTableName("Product_Order_Products");

        this.objectMapper  = new ObjectMapper();
    }

    @Override
    public Order save(Order order) {
        order.setDate(new Date());
        long orderId = saveOrderDetails(order);
        order.setId(orderId);
        List<Product> products = order.getProducts();
        for (Product product : products) {
            saveProductToOrder(product, orderId);
        }
        return order;
    }

    private long saveOrderDetails(Order order) {
        @SuppressWarnings("unchecked")
        Map<String, Object> values = objectMapper.convertValue(order, Map.class);
        values.put("date",order.getDate());
        long orderId = orderInsert.executeAndReturnKey(values).longValue();
        return orderId;
    }

    private void saveProductToOrder(Product product, long orderId){
        Map<String, Object> values = new HashMap<>();
        values.put("productOrder", orderId);
        values.put("productId", product.getId());
        orderProductInsert.execute(values);
    }
}

