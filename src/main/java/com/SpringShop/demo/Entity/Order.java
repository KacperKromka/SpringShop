package com.SpringShop.demo.Entity;

import com.SpringShop.demo.Entity.Product;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Product_Order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date date;

    @NotBlank(message = "We need your name")
    private String name;

    @NotBlank(message = "We need your full address")
    private String street;

    @NotBlank(message = "We need your full address")
    private String city;

    @NotBlank(message = "We need your full address")
    private String state;

    @NotBlank(message = "We need your full address")
    private String zip;

    @CreditCardNumber(message = "It is not correct credit card number")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Correct format is MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Incorrect CVV")
    private String ccCVV;

    @ManyToMany(targetEntity = Product.class)
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product){
        this.products.add(product);
    }

    @PrePersist
    void date() {
        this.date = new Date();
    }
}
