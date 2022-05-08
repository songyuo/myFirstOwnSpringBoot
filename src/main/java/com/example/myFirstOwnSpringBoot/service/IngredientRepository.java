package com.example.myFirstOwnSpringBoot.service;

import com.example.myFirstOwnSpringBoot.entity.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();

    Ingredient findOne(String id);

    Ingredient save(Ingredient ingredient);
}
