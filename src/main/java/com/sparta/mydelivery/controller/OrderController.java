package com.sparta.mydelivery.controller;

import com.sparta.mydelivery.dto.OrderRequestDto;
import com.sparta.mydelivery.dto.OrderResponseDto;
import com.sparta.mydelivery.repository.OrderRepository;
import com.sparta.mydelivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/order/request")
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto orderRequestDto){
        return orderService.createOrder(orderRequestDto);
    }

    @GetMapping("/orders")
    public List<OrderResponseDto> getOrders(){
        return orderService.getOrders();
    }

}
