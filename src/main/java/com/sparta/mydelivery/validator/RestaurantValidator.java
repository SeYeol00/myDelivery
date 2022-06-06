package com.sparta.mydelivery.validator;


import com.sparta.mydelivery.dto.RestaurantRequestDto;
import com.sparta.mydelivery.exception.CustomException;
import com.sparta.mydelivery.exception.ErrorCode;
import org.springframework.stereotype.Component;

@Component
public class RestaurantValidator {
    public static void validateRestaurantInput(RestaurantRequestDto requestDto){
        if(requestDto.getMinOrderPrice()>100000||requestDto.getMinOrderPrice()<1000){
            throw new CustomException("최소 주문 가격은 1,000원 ~ 100,000원 입니다.", ErrorCode.OUT_OF_RANGE_MIN_ORDER_PRICE);
        }
        if(requestDto.getMinOrderPrice()%100!=0){
            throw new CustomException("100원 단위로만 입력 가능합니다.",ErrorCode.NOT100WON_MIN_ORDER_PRICE);
        }
        if(requestDto.getDeliveryFee()>10000||requestDto.getDeliveryFee()<0){
            throw new CustomException("기본 배달 가격은 0원 ~ 10000원 입니다.", ErrorCode.OUT_OF_RANGE_DELIVERY_FEE);
        }
        if(requestDto.getDeliveryFee()%500!=0){
            throw new CustomException("500원 단위로만 입력 가능합니다.",ErrorCode.NOT500WON_MIN_DELIVERY_FEE);
        }

    }
}
