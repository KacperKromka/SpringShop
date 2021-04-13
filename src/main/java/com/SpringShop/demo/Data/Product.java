package com.SpringShop.demo.Data;

import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date date;

    @NotNull
    @Size(min = 3, message = "Name has to be 3 character long at least")
    private String name;

    @ManyToMany(targetEntity = Design.class)
    @Size(min = 1, message = "Blank t-shirts are available at store")
    private List<Design> design = new ArrayList<>();

    @PrePersist
    void date() {
        this.date = new Date();
    }
}
