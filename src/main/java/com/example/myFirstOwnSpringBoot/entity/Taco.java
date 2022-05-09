package com.example.myFirstOwnSpringBoot.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "createdAt")
    private Date createdAt;

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    //当任何配料都不选时，ingredients为null，@Size不会对null值进行校验，所以要加Notnull
    @NotNull(message = "You must choose at least 1 ingredient")
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    @ManyToMany(targetEntity = Ingredient.class)
    @JoinTable(name="Taco_Ingredients",
            joinColumns={@JoinColumn(name="Taco")},
            inverseJoinColumns={@JoinColumn(name="Ingredient")})
    private List<Ingredient> ingredients;

    @PrePersist()
    void createdAt(){
        this.createdAt = new Date();
    }
}
