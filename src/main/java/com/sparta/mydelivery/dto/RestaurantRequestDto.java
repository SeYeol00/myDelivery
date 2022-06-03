package com.sparta.mydelivery.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestaurantRequestDto {
    String restaurantName;
    int minOrderPrice;
    int deliveryFee;
}
