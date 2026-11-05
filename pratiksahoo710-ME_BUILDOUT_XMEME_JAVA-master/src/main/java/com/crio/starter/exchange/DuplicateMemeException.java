package com.crio.starter.exchange;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateMemeException extends RuntimeException {

    public DuplicateMemeException(String message) {
        super(message);
    }
}

    
