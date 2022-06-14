package com.example.myFirstOwnSpringBoot.entity;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Taco_Order")
public class Order {

    @NotBlank(message = "deliveryName is required")
    private String deliveryName;

    @NotBlank(message = "deliveryStreet is required")
    private String deliveryStreet;

    @NotBlank(message = "deliveryCity is required")
    private String deliveryCity;

    @NotBlank(message = "deliveryState is required")
    private String deliveryState;

    @NotBlank(message = "deliveryZip code is required")
    private String deliveryZip;

    @NotBlank(message = "CreditCard is required")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])(/)([1-9]\\d)$", message = "Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date placedAt;

    @ManyToMany(targetEntity = Taco.class)
    @JoinTable(name="Taco_Order_Tacos",
            joinColumns={@JoinColumn(name="tacoOrder")},
            inverseJoinColumns={@JoinColumn(name="taco")})
    private List<Taco> tacos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id") //不写也是user_id 注解的参考资料https://www.hxstrive.com/subject/open_jpa.htm?id=565
    private User user;

    public void addDesign(Taco design){
        this.tacos.add(design);
    }

    @PrePersist
    void placedAt(){
        this.placedAt = new Date();
    }
}

