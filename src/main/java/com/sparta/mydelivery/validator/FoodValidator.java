package com.sparta.mydelivery.validator;

import com.sparta.mydelivery.dto.FoodRequestDto;
import com.sparta.mydelivery.exception.CustomException;
import com.sparta.mydelivery.exception.ErrorCode;
import com.sparta.mydelivery.model.Restaurant;
import com.sparta.mydelivery.repository.FoodRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class FoodValidator {
    private FoodRepository foodRepository;

    @Autowired
    public FoodValidator(FoodRepository foodRepository){
        this.foodRepository = foodRepository;
    }
    public boolean validateFoodInput(FoodRequestDto requestDto, Restaurant restaurant){

        if(foodRepository.existsFoodByFoodNameAndRestaurant(requestDto.getFoodName(), restaurant)){
            throw new CustomException("같은 이름의 음식이 존재합니다.", ErrorCode.SAME_FOOD_EXISTS);
        }
        if(requestDto.getFoodPrice()>1000000||requestDto.getFoodPrice()<100){
            throw new CustomException("음식 가격은 100원 ~ 1000000원 입니다.",ErrorCode.OUT_OF_RANGE_FOOD_PRICE);
        }
        if(requestDto.getFoodPrice()%100!=0){
            throw new CustomException("100원 단위로만 입력 가능합니다.",ErrorCode.NOT100WON_FOOD_PRICE);

        }return true;
    }
}
