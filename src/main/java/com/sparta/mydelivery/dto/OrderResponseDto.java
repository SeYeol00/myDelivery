package com.sparta.mydelivery.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderResponseDto {

    String restaurantName;
    List<OrderDetailResponse> foods;

    int deliveryFee;
    int totalPrice;
}
