package com.sparta.mydelivery.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity
public class OrderDetail {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", nullable = false) //디비 연관관계 설정
    private Order order;


    @Column(nullable = false)//디비 연관관계 설정
    private String foodName;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int price;
}
