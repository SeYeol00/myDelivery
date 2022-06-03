package com.sparta.mydelivery.controller;

import com.sparta.mydelivery.dto.OrderRequestDto;
import com.sparta.mydelivery.repository.OrderRepository;
import com.sparta.mydelivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/order/request")
    public void createOrder(@RequestBody OrderRequestDto orderRequestDto){
        orderService.createOrder(orderRequestDto);
    }
}
