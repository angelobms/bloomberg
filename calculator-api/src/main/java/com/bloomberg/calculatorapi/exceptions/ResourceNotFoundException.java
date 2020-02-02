package com.bloomberg.calculatorapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private String id;

    public ResourceNotFoundException(String id) {
        super(ErrorCode.RESOURCE_NOT_FOUND);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
