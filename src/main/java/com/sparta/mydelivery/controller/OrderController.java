package com.sparta.mydelivery.controller;

import com.sparta.mydelivery.dto.OrderRequestDto;
import com.sparta.mydelivery.dto.OrderResponseDto;
import com.sparta.mydelivery.repository.OrderRepository;
import com.sparta.mydelivery.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
/*@Tag(name = "OrderController")*/
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }
    @Operation(description = "주문 요청")
    @PostMapping("/order/request")
    public OrderResponseDto createOrder(@Parameter @RequestBody OrderRequestDto orderRequestDto){
        return orderService.createOrder(orderRequestDto);
    }
    @Operation(description = "주문 조회")
    @GetMapping("/orders")
    public List<OrderResponseDto> getOrders(){
        return orderService.getOrders();
    }

}
