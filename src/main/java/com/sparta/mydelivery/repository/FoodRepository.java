package com.sparta.mydelivery.repository;

import com.sparta.mydelivery.model.Food;
import com.sparta.mydelivery.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food,Long> {
    List<Food> findAllByRestaurant(Restaurant restaurant);
    Food findByRestaurantIdAndId(Long restaurantId,Long Id);

    boolean existsFoodByFoodNameAndRestaurant(String foodName, Restaurant restaurant);
}
