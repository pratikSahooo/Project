package com.crio.starter.exchange;

import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class MemeNotFoundException extends RuntimeException {
    public MemeNotFoundException(String message) {
        super(message);
    }
}
