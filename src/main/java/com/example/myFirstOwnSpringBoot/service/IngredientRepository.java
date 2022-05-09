package com.example.myFirstOwnSpringBoot.service;

import com.example.myFirstOwnSpringBoot.entity.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
