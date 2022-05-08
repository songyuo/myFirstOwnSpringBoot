package com.example.myFirstOwnSpringBoot.service;

import com.example.myFirstOwnSpringBoot.entity.Order;

public interface OrderRepository {
    Order save(Order order);
}
