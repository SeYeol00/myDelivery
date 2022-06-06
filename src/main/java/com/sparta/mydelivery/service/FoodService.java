package com.sparta.mydelivery.service;

import com.sparta.mydelivery.dto.FoodRequestDto;
import com.sparta.mydelivery.exception.CustomException;
import com.sparta.mydelivery.exception.ErrorCode;
import com.sparta.mydelivery.model.Food;
import com.sparta.mydelivery.model.Restaurant;
import com.sparta.mydelivery.repository.FoodRepository;
import com.sparta.mydelivery.repository.RestaurantRepository;
import com.sparta.mydelivery.validator.FoodValidator;
import org.cef.handler.CefLoadHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    private final RestaurantRepository restaurantRepository;

    private final FoodValidator foodValidator;

    @Autowired
    public FoodService(FoodRepository foodRepository,RestaurantRepository restaurantRepository,FoodValidator foodValidator){
        this.foodRepository =  foodRepository;
        this.restaurantRepository = restaurantRepository;
        this.foodValidator = foodValidator; // 빈으로 꼭 등록하기
    }

    @Transactional
    public void createFood(Long restaurantId, List<FoodRequestDto> foodList) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);

        if(restaurant.isPresent()){
            Restaurant res = restaurant.get();
            for(FoodRequestDto foodRequestDto: foodList){
                if(foodValidator.validateFoodInput(foodRequestDto,res)){
                    Food food = new Food(foodRequestDto,res);
                    foodRepository.save(food);
                }
            }
        }else{
            throw new CustomException("해당 음식점 아이디가 존재하지 않습니다.", ErrorCode.NOT_FOUND_RESTAURANT);
        }

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
