package com.sparta.mydelivery.controller;

import com.sparta.mydelivery.dto.FoodRequestDto;
import com.sparta.mydelivery.model.Food;
import com.sparta.mydelivery.service.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
/*@Tag(name = "FoodController")*/
public class FoodController {

    private FoodService foodService;
    @Autowired
    public FoodController(FoodService foodService){
        this.foodService = foodService;
    }


    @Operation(description = "음식 등록")
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void createFood(@Parameter @PathVariable Long restaurantId,@Parameter @RequestBody List<FoodRequestDto> foodList){
        System.out.println(foodList.toString());
        foodService.createFood(restaurantId,foodList);
    }
    @Operation(description = "음식 조회")
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getFoods(@Parameter @PathVariable Long restaurantId){
        return foodService.getFoods(restaurantId);
    }

}
