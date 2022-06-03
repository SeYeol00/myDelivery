package com.sparta.mydelivery.service;

import com.sparta.mydelivery.dto.RestaurantRequestDto;
import com.sparta.mydelivery.model.Restaurant;
import com.sparta.mydelivery.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurtantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurtantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant createRestaurant(RestaurantRequestDto restaurantRequestDto) throws Exception {

        Restaurant restaurant = new Restaurant(restaurantRequestDto);
        if(restaurant.getMinOrderPrice()>100000||restaurant.getMinOrderPrice()<1000){
            throw new Exception("최소 주문 가격은 1,000원 ~ 100,000원 입니다.");
        }
        if(restaurant.getMinOrderPrice()%100!=0){
            throw new Exception("100원 단위로만 입력 가능합니다.");
        }
        restaurantRepository.save(restaurant);

        return restaurant;
    }
}
