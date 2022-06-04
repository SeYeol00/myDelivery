package com.sparta.mydelivery.service;

import com.sparta.mydelivery.dto.*;
import com.sparta.mydelivery.exception.CustomException;
import com.sparta.mydelivery.exception.ErrorCode;
import com.sparta.mydelivery.model.Food;
import com.sparta.mydelivery.model.Order;
import com.sparta.mydelivery.model.OrderDetail;
import com.sparta.mydelivery.model.Restaurant;
import com.sparta.mydelivery.repository.FoodRepository;
import com.sparta.mydelivery.repository.OrderDetailRepository;
import com.sparta.mydelivery.repository.OrderRepository;
import com.sparta.mydelivery.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    private final RestaurantRepository restaurantRepository;

    private final FoodRepository foodRepository;

    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,RestaurantRepository restaurantRepository,FoodRepository foodRepository,OrderDetailRepository orderDetailRepository){
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
        this.foodRepository =foodRepository;
        this.orderDetailRepository = orderDetailRepository;
    }
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        Optional<Restaurant> restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId());
        if(restaurant.isEmpty()){
            throw new CustomException("해당 음식점 아이디가 존재하지 않습니다.", ErrorCode.NOT_FOUND_RESTAURANT);
        }else{
            Restaurant res = restaurant.get();
            order.setRestaurant(res);
            order.setDeliveryFee(res.getDeliveryFee());
            order.setTotalPrice(0);
            orderRepository.save(order);

            List<OrderDetailRequestDto>foods = orderRequestDto.getFoods();
            int count = 0;
             for(OrderDetailRequestDto food:foods){
                 OrderDetail orderDetail = new OrderDetail();
                 Food food1 = foodRepository.findByRestaurantIdAndId(res.getId(),food.getFoodId());
                orderDetail.setFoodName(food1.getFoodName());
                if(food.getQuantity()>100||food.getQuantity()<1){
                    throw new CustomException("허용 값은 1부터 100입니다.",ErrorCode.FOOD_QUANTITY_NOT_ALLOWED);
                }
                orderDetail.setQuantity(food.getQuantity());
                orderDetail.setPrice(food1.getFoodPrice());
                orderDetail.setOrder(order);
                 count = count + orderDetail.getPrice() * orderDetail.getQuantity();
                 orderDetailRepository.save(orderDetail);
             }

             order.setTotalPrice(count);
            Order currentOrder = orderRepository.save(order);

            OrderResponseDto orderResponseDto = new OrderResponseDto();
            List<OrderDetailResponseDto> orderDetailResponseDtos= new ArrayList<>();
            List<OrderDetail> details = orderDetailRepository.findAllByOrder(currentOrder);
            for(OrderDetail detail:details){
                OrderDetailResponseDto detailResponseDto = new OrderDetailResponseDto();
                detailResponseDto.setFoodName(detail.getFoodName());
                detailResponseDto.setQuantity(detail.getQuantity());
                detailResponseDto.setPrice(detail.getPrice());
                orderDetailResponseDtos.add(detailResponseDto);
            }
            orderResponseDto.setRestaurantName(currentOrder.getRestaurant().getRestaurantName());
            orderResponseDto.setDeliveryFee(currentOrder.getDeliveryFee());
            orderResponseDto.setTotalPrice(currentOrder.getTotalPrice());
            orderResponseDto.setFoods(orderDetailResponseDtos);
            return orderResponseDto;







        }

    }

    public OrderAllResponseDto getOrders() {
        OrderAllResponseDto allResponseDto = new OrderAllResponseDto();
        List<Order> Orders = orderRepository.findAll();

        for(Order currentOrder:Orders){
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            List<OrderDetailResponseDto> orderDetailResponseDtos= new ArrayList<>();
            List<OrderDetail> details = orderDetailRepository.findAllByOrder(currentOrder);
            for(OrderDetail detail:details){
                OrderDetailResponseDto detailResponseDto = new OrderDetailResponseDto();
                detailResponseDto.setFoodName(detail.getFoodName());
                detailResponseDto.setQuantity(detail.getQuantity());
                detailResponseDto.setPrice(detail.getPrice());
                orderDetailResponseDtos.add(detailResponseDto);
            }
            orderResponseDto.setRestaurantName(currentOrder.getRestaurant().getRestaurantName());
            orderResponseDto.setDeliveryFee(currentOrder.getDeliveryFee());
            orderResponseDto.setTotalPrice(currentOrder.getTotalPrice());
            orderResponseDto.setFoods(orderDetailResponseDtos);
            allResponseDto.getOrders().add(orderResponseDto);
        }

        return allResponseDto;
    }
}
