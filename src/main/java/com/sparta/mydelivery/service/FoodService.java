package com.sparta.mydelivery.service;

import com.sparta.mydelivery.dto.FoodRequestDto;
import com.sparta.mydelivery.exception.CustomException;
import com.sparta.mydelivery.exception.ErrorCode;
import com.sparta.mydelivery.model.Food;
import com.sparta.mydelivery.model.Restaurant;
import com.sparta.mydelivery.repository.FoodRepository;
import com.sparta.mydelivery.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository,RestaurantRepository restaurantRepository){
        this.foodRepository =  foodRepository;
        this.restaurantRepository = restaurantRepository;
    }


    public Food createFood(Long restaurantId, FoodRequestDto foodRequestDto) {
        Food food = new Food();
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if(restaurant.isPresent()){
            Restaurant res = restaurant.get();
            food.setRestaurant(res);
            food.setFoodName(foodRequestDto.getFoodName());
            food.setFoodPrice(foodRequestDto.getFoodPrice());
        }else{
            throw new CustomException("해당 음식점 아이디가 존재하지 않습니다.", ErrorCode.NOT_FOUND_RESTAURANT);
        }

        if(food.getFoodPrice()>1000000||food.getFoodPrice()<100){
            throw new CustomException("음식 가격은 100원 ~ 1000000원 입니다.",ErrorCode.OUT_OF_RANGE_FOOD_PRICE);

        }
        if(food.getFoodPrice()%100!=0){
            throw new CustomException("100원 단위로만 입력 가능합니다.",ErrorCode.NOT100WON_FOOD_PRICE);

        }

        foodRepository.save(food);
        return food;
    }

    public List<Food> getFoods(Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if(restaurant.isPresent()){
            Restaurant res = restaurant.get();
            List<Food> foods = foodRepository.findAllByRestaurant(res);
            return foods;
        }else{
            throw new CustomException("해당 음식점 아이디가 존재하지 않습니다.", ErrorCode.NOT_FOUND_RESTAURANT);
        }
    }
}
