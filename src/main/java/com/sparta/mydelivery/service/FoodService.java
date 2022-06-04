package com.sparta.mydelivery.service;

import com.sparta.mydelivery.dto.FoodRequestDto;
import com.sparta.mydelivery.exception.CustomException;
import com.sparta.mydelivery.exception.ErrorCode;
import com.sparta.mydelivery.model.Food;
import com.sparta.mydelivery.model.Restaurant;
import com.sparta.mydelivery.repository.FoodRepository;
import com.sparta.mydelivery.repository.RestaurantRepository;
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

    @Autowired
    public FoodService(FoodRepository foodRepository,RestaurantRepository restaurantRepository){
        this.foodRepository =  foodRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    public void createFood(Long restaurantId, List<FoodRequestDto> foodList) {

        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);

        if(restaurant.isPresent()){
            Restaurant res = restaurant.get();
            for(FoodRequestDto foodRequestDto: foodList){
                Food food = new Food();
                food.setRestaurant(res);
                food.setFoodName(foodRequestDto.getFoodName());
                if(foodRepository.existsFoodByFoodNameAndRestaurant(food.getFoodName(), res)){
                    throw new CustomException("같은 이름의 음식이 존재합니다.", ErrorCode.SAME_FOOD_EXISTS);
                }
                food.setFoodPrice(foodRequestDto.getFoodPrice());
                if(food.getFoodPrice()>1000000||food.getFoodPrice()<100){
                    throw new CustomException("음식 가격은 100원 ~ 1000000원 입니다.",ErrorCode.OUT_OF_RANGE_FOOD_PRICE);

                }
                if(food.getFoodPrice()%100!=0){
                    throw new CustomException("100원 단위로만 입력 가능합니다.",ErrorCode.NOT100WON_FOOD_PRICE);

                }
                foodRepository.save(food);
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
