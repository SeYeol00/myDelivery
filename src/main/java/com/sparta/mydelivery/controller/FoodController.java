package com.sparta.mydelivery.controller;

import com.sparta.mydelivery.dto.FoodRequestDto;
import com.sparta.mydelivery.model.Food;
import com.sparta.mydelivery.model.Restaurant;
import com.sparta.mydelivery.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FoodController {

    private FoodService foodService;
    @Autowired
    public FoodController(FoodService foodService){
        this.foodService = foodService;
    }



    @PostMapping("/restaurant/{restaurantId}/food/register")
    public Food createFood(@PathVariable Long restaurantId, FoodRequestDto foodRequestDto){
        return foodService.createFood(restaurantId,foodRequestDto);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getFoods(@PathVariable Long restaurantId){
        return foodService.getFoods(restaurantId);
    }

}
