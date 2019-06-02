package com.example.demo.exceptions;

class ApiValidationError implements ApiSubError {

    ApiValidationError(String object, String message) {
    }

    public ApiValidationError(Object object, String field, Object rejectedValue, String message) {
        super();
    }
}