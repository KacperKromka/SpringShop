package com.SpringShop.demo.Controllers;

import com.SpringShop.demo.Data.Design;
import com.SpringShop.demo.Data.Design.Type;
import com.SpringShop.demo.Data.DesignRepository;
import com.SpringShop.demo.Data.JdbcDesignRepository;
import com.SpringShop.demo.Data.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/design")
public class DesignController {
    private final DesignRepository designRepository;

    @Autowired
    public DesignController(DesignRepository designRepository){
        this.designRepository = designRepository;
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
        model.addAttribute("design", new Product());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Product design, Errors errors){
        if(errors.hasErrors()) {
            return "design";
        }

        log.info("Przetwarzanie projektu " + design);
        return "redirect:/orders/current";
    }

    private List<Design> filterByType(List<Design> designs, Type type) {

        return designs.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());

    }
}
