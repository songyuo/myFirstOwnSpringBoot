package com.example.myFirstOwnSpringBoot.entity;

import lombok.Data;

import java.util.List;

@Data
public class Taco {

    private String name;
    private List<String> ingredients;
}
