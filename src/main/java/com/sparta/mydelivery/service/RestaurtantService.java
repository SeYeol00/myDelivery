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

        restaurantRepository.save(restaurant);

        return restaurant;
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }
}
