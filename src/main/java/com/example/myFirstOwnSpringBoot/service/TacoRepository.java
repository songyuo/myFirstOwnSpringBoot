package com.example.myFirstOwnSpringBoot.service;

import com.example.myFirstOwnSpringBoot.entity.Taco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepository extends CrudRepository<Taco, Long> {
}
