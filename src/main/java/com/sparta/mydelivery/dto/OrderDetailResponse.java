package com.sparta.mydelivery.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDetailResponse {

    String foodName;
    int quantity;
    int price;
}
