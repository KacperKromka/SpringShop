package com.SpringShop.demo.Controllers;

import com.SpringShop.demo.Data.*;
import com.SpringShop.demo.Data.Design.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignController {

    private final DesignRepository designRepository;
    private ProductRepository productRepository;

    @Autowired
    public DesignController(DesignRepository designRepository, ProductRepository productRepository){
        this.designRepository = designRepository;
        this.productRepository = productRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "product")
    public Product product() {
        return new Product();
    }

    @GetMapping
    public String designForm(Model model) {
        List<Design> designs = new ArrayList<>();
        designRepository.findAll().forEach(d -> designs.add(d));

        Type[] types = Design.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(designs, type));
        }

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Product design, Errors errors, @ModelAttribute Order order){

        if(errors.hasErrors()) {
            return "design";
        }

        Product saved = productRepository.save(design);
        order.addProduct(saved);

        return "redirect:/orders/current";
    }

    private List<Design> filterByType(List<Design> designs, Type type) {

        return designs.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());

    }
}
