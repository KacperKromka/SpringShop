package com.SpringShop.demo.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcDesignRepository implements DesignRepository{

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcDesignRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<Design> findAll() {
        return jdbc.query("select id, name, type from Design", this::mapRowToDesign);
    }

    @Override
    public Design findById(Long id) {
        return jdbc.queryForObject("select id, name, type from Design where id=?", this::mapRowToDesign, id);
    }

    @Override
    public Design save(Design design) {
         jdbc.update("insert into Design (id, name, type) values (?, ?, ?)",
                design.getId(),
                design.getName(),
                design.getType().toString());
         return design;
    }

    private Design mapRowToDesign(ResultSet rs, int rowNum) throws SQLException {
        return new Design(
                rs.getLong("id"),
                rs.getString("name"),
                Design.Type.valueOf(rs.getString("type"))
        );
    }
}
