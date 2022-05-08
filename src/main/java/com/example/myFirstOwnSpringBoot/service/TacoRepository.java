package com.example.myFirstOwnSpringBoot.service;

import com.example.myFirstOwnSpringBoot.entity.Taco;

public interface TacoRepository {
    Taco save(Taco design);
}
