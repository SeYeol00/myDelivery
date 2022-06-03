package com.sparta.mydelivery.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDetailRequestDto {
    Long foodId;
    int quantity;

}
