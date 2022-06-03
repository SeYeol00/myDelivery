package com.sparta.mydelivery.dto;

import com.sparta.mydelivery.model.Food;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class OrderRequestDto {
    Long restaurantId;
    List<OrderDetailRequestDto> foods;

}
