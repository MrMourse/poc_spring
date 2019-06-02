package com.example.demo.exceptions;

class ApiValidationError extends ApiSubError {
    private String field;
    private Object rejectedValue;

    ApiValidationError(String object, String message) {
    }

    public ApiValidationError(Object object, String field, Object rejectedValue, String message) {
        super();
    }
}