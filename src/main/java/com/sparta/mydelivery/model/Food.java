package com.sparta.mydelivery.model;

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

    @Column(nullable = false,unique = true)
    private String foodName;

    @Column(nullable = false)
    private int foodPrice;

}
