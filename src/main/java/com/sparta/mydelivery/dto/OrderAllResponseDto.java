package com.sparta.mydelivery.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Getter
public class OrderAllResponseDto {
    List<OrderResponseDto> orders;
}
