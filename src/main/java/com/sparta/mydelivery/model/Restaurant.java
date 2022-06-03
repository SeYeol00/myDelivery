package com.sparta.mydelivery.model;

import com.sparta.mydelivery.dto.RestaurantRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity
public class Restaurant {
    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String restaurantName;

    @Column(nullable = false)
    private int minOrderPrice;

    @Column(nullable = false)
    private int deliveryFee;


    public Restaurant(RestaurantRequestDto restaurantRequestDto){
        this.restaurantName = restaurantRequestDto.getRestaurantName();
        this.minOrderPrice = restaurantRequestDto.getMinOrderPrice();
        this.deliveryFee = restaurantRequestDto.getDeliveryFee();
    }
}
