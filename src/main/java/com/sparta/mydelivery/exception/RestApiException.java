package com.sparta.mydelivery.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class RestApiException { // http 본문에 담아서 보내줄 형태
    private String errorMessage;
    private HttpStatus httpStatus;
}
