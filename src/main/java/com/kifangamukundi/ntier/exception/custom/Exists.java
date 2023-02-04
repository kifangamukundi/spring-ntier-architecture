package com.kifangamukundi.ntier.exception.custom;

import com.kifangamukundi.ntier.exception.global.ErrorResponse;
import org.springframework.http.HttpStatus;

public enum Exists implements ErrorResponse {
    RESOURCE_EXISTS( "RESOURCE_EXISTS", HttpStatus.CONFLICT , "Resource {id} already exists");

    String key;
    HttpStatus httpStatus;
    String message;

    Exists(String key, HttpStatus httpStatus, String message) {
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
