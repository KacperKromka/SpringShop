package com.SpringShop.demo.Data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcProductRepository implements ProductRepository{

    private JdbcTemplate jdbc;

    public JdbcProductRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Product save(Product product) {
        long productId = saveProductInfo(product);
        product.setId(productId);
        for (Design design : product.getDesign()) {
            saveDesignToProduct(design, productId);
        }
        return product;
    }

    private long saveProductInfo(Product product) {
        product.setDate(new Date());
        PreparedStatementCreatorFactory preparedStatementCreatorFactory = new PreparedStatementCreatorFactory(
                "INSERT INTO Product (name, date) VALUES (?, ?)",
                Types.VARCHAR, Types.TIMESTAMP
        );
        preparedStatementCreatorFactory.setReturnGeneratedKeys(true);
        PreparedStatementCreator psc = preparedStatementCreatorFactory.newPreparedStatementCreator(
                Arrays.asList(
                        product.getName(),
                        new Timestamp(product.getDate().getTime())
                )
        );


        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);

        return keyHolder.getKey().longValue();
    }

/**
 *
 *
    private long saveProductInfo(Product product){
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbc);
        jdbcInsert.withTableName("Product").usingGeneratedKeyColumns("id");
        Map<String, Object> params = new HashMap<>();
        params.put("name", product.getName());
        params.put("date", product.getDate().getTime());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));

        return ((Number) key).longValue();
    }

**/
    private void saveDesignToProduct(Design design, long productId){
        jdbc.update("INSERT INTO Product_Design (product, design)" + " VALUES (?, ?)", productId, design.getId());
    }
}
