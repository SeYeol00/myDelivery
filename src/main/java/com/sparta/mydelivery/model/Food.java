package com.sparta.mydelivery.model;

import com.sparta.mydelivery.dto.FoodRequestDto;
import com.sparta.mydelivery.validator.FoodValidator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity
public class Food {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "RESTUARANT_ID", nullable = false) //디비 연관관계 설정
    private Restaurant restaurant;

    @Column(nullable = false)
    private String foodName;

    @Column(nullable = false)
    private int foodPrice;

    public Food (FoodRequestDto foodRequestDto, Restaurant restaurant){
        this.restaurant =restaurant;
        this.foodName = foodRequestDto.getFoodName();
        this.foodPrice = foodRequestDto.getFoodPrice();
    }

}
