package com.sparta.mydelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class MyDeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyDeliveryApplication.class, args);
    }

}
