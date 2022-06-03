package com.sparta.mydelivery.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ErrorCode {
    // 400 Bad Request
    TEMPORARY_SERVER_ERROR(HttpStatus.BAD_REQUEST, "400_0", "잘못된 요청입니다."),
    OUT_OF_RANGE_MIN_ORDER_PRICE(HttpStatus.BAD_REQUEST, "400_1", "최소 주문 가격은 1,000원 ~ 100,000원 입니다."),
    NOT100WON_MIN_ORDER_PRICE(HttpStatus.BAD_REQUEST, "400_2", "100원 단위로만 입력 가능합니다."),
    OUT_OF_RANGE_DELIVERY_FEE(HttpStatus.BAD_REQUEST, "400_3", "기본 배달 가격은 0원 ~ 10000원 입니다."),
    NOT500WON_MIN_DELIVERY_FEE(HttpStatus.BAD_REQUEST, "400_4", "500원 단위로만 입력 가능합니다."),


    // 404 Not Found
    NOT_FOUND_RESTAURANT(HttpStatus.NOT_FOUND, "404_1", "해당 음식점 아이디가 존재하지 않습니다."),
    NOT_FOUND_FOOD(HttpStatus.NOT_FOUND, "404_2", "해당 음식 아이디가 존재하지 않습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

    /*ErrorCode(HttpStatus httpStatus, String errorCode, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }*/
}
