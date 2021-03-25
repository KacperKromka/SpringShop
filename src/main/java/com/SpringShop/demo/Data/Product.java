package com.SpringShop.demo.Data;

import lombok.Data;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Product {


    private long id;
    private Date date;

    @NotNull
    @Size(min = 3, message = "Name has to be 3 character long at least")
    private String name;
    @NotNull
    @Size(min = 1, message = "Blank t-shirts are available at store")
    private List<String> design;
}
