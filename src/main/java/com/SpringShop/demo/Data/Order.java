package com.SpringShop.demo.Data;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class Order {

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

}
