package com.sparta.mydelivery.service;

import com.sparta.mydelivery.dto.RestaurantRequestDto;
import com.sparta.mydelivery.exception.CustomException;
import com.sparta.mydelivery.exception.ErrorCode;
import com.sparta.mydelivery.model.Restaurant;
import com.sparta.mydelivery.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class RestaurtantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurtantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }
    @Transactional
    public Restaurant createRestaurant(RestaurantRequestDto restaurantRequestDto) throws Exception {

        Restaurant restaurant = new Restaurant(restaurantRequestDto);
        if(restaurant.getMinOrderPrice()>100000||restaurant.getMinOrderPrice()<1000){
            throw new CustomException("최소 주문 가격은 1,000원 ~ 100,000원 입니다.", ErrorCode.OUT_OF_RANGE_MIN_ORDER_PRICE);
        }
        if(restaurant.getMinOrderPrice()%100!=0){
            throw new CustomException("100원 단위로만 입력 가능합니다.",ErrorCode.NOT100WON_MIN_ORDER_PRICE);
        }
        if(restaurant.getDeliveryFee()>10000||restaurant.getDeliveryFee()<0){
            throw new CustomException("기본 배달 가격은 0원 ~ 10000원 입니다.", ErrorCode.OUT_OF_RANGE_DELIVERY_FEE);
        }
        if(restaurant.getDeliveryFee()%500!=0){
            throw new CustomException("500원 단위로만 입력 가능합니다.",ErrorCode.NOT500WON_MIN_DELIVERY_FEE);
        }
        restaurantRepository.save(restaurant);

        return restaurant;
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }
}
