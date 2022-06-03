package com.sparta.mydelivery.controller;


import com.sparta.mydelivery.dto.RestaurantRequestDto;
import com.sparta.mydelivery.model.Restaurant;
import com.sparta.mydelivery.repository.RestaurantRepository;
import com.sparta.mydelivery.service.RestaurtantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {
    private final RestaurtantService restaurtantService;

    @Autowired
    public RestaurantController( RestaurtantService restaurtantService){
        this.restaurtantService = restaurtantService;
    }

    @PostMapping("/restaurant/register")
    public Restaurant createRestaurant(@RequestBody RestaurantRequestDto restaurantRequestDto) throws Exception {
        return restaurtantService.createRestaurant(restaurantRequestDto);
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants(){
        return restaurtantService.getRestaurants();
    }

}
