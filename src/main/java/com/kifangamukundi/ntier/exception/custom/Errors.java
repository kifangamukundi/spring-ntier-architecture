package com.kifangamukundi.ntier.exception.custom;

import com.kifangamukundi.ntier.exception.global.ErrorResponse;
import org.springframework.http.HttpStatus;

public enum Errors implements ErrorResponse {
    RESOURCE_NOT_FOUND( "RESOURCE_NOT_FOUND", HttpStatus.NOT_FOUND , "Resource with id {id} not found");

    String key;
    HttpStatus httpStatus;
    String message;

    Errors(String key, HttpStatus httpStatus, String message) {
        this.message = message;
        this.key = key;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
