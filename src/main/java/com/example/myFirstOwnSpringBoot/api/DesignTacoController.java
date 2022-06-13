package com.example.myFirstOwnSpringBoot.api;

import com.example.myFirstOwnSpringBoot.entity.Ingredient;
import com.example.myFirstOwnSpringBoot.entity.Ingredient.Type;
import com.example.myFirstOwnSpringBoot.entity.Order;
import com.example.myFirstOwnSpringBoot.entity.Taco;
import com.example.myFirstOwnSpringBoot.repository.IngredientRepository;
import com.example.myFirstOwnSpringBoot.repository.TacoRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
    private final IngredientRepository ingredientRepository;

    private final  TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository){
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute
    private void addIngredientToModel(Model model){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);
        Type[] types = Type.values();
        for(Type type : types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }
    
    @GetMapping
    public String showDesignForm(Model model){
        return "design";
    }


    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
        return ingredients.stream().filter(o -> o.getType().equals(type)).collect(Collectors.toList());
    }

    @PostMapping
    public String processingDesign( @Valid Taco design, Errors errors, Model model, @ModelAttribute Order order){
        if(errors.hasErrors()){
            return "design";
        }

        Taco saved = tacoRepository.save(design);
        order.addDesign(saved);

        log.info("Processing design : " + saved);
        return "redirect:/orders/current";
    }
}
