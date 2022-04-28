package com.example.myFirstOwnSpringBoot.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        return "home";

        
    }

}
