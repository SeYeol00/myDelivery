package com.sparta.mydelivery.controller;

import com.sparta.mydelivery.dto.FoodRequestDto;
import com.sparta.mydelivery.model.Food;
import com.sparta.mydelivery.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodController {

    private FoodService foodService;
    @Autowired
    public FoodController(FoodService foodService){
        this.foodService = foodService;
    }



    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void createFood(@PathVariable Long restaurantId, @RequestBody List<FoodRequestDto> foodList){
        System.out.println(foodList.toString());
        foodService.createFood(restaurantId,foodList);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getFoods(@PathVariable Long restaurantId){
        return foodService.getFoods(restaurantId);
    }

}
