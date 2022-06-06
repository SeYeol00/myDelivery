package com.sparta.mydelivery.controller;


import com.sparta.mydelivery.dto.RestaurantRequestDto;
import com.sparta.mydelivery.model.Restaurant;
import com.sparta.mydelivery.repository.RestaurantRepository;
import com.sparta.mydelivery.service.RestaurtantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
/*@Tag(name = "RestaurantController")*/
public class RestaurantController {
    private final RestaurtantService restaurtantService;

    @Autowired
    public RestaurantController( RestaurtantService restaurtantService){
        this.restaurtantService = restaurtantService;
    }
    @Operation(description = "음식점 등록")
    @PostMapping("/restaurant/register")
    public Restaurant createRestaurant(@Parameter @RequestBody RestaurantRequestDto restaurantRequestDto) throws Exception {
        return restaurtantService.createRestaurant(restaurantRequestDto);
    }
    @Operation(description = "음식점 조회")
    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants(){
        return restaurtantService.getRestaurants();
    }

}
