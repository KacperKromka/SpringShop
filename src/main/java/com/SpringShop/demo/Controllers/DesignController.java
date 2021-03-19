package com.SpringShop.demo.Controllers;

import com.SpringShop.demo.Data.Design;
import com.SpringShop.demo.Data.Design.Type;
import com.SpringShop.demo.Data.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/design")
public class DesignController {

    @GetMapping
    public String design(Model model) {
        List<Design> designs = Arrays.asList(
                new Design(1,"czarne kropki", Type.BLACKWHITE),
                new Design(2, "czarne paski", Type.BLACKWHITE),
                new Design(3, "kolorowe paski", Type.COLOR)
        );

        Type[] types = Design.Type.values();
        for (Type type: types){
            model.addAttribute(type.toString().toLowerCase(Locale.ROOT),filterByType(designs, type));
        }
        model.addAttribute("design", new Product());
        return "design";
    }

    @PostMapping
    public String processDesign(Design design){
        log.info("Przetwarzanie projektu " + design);
        return "redirect:/orders/current";
    }

    private List<Design> filterByType(List<Design> designs, Type type) {

        return designs.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());

    }
}
