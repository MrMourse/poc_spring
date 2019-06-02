package com.example.demo.exceptions;

class ApiValidationError extends ApiSubError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }

    public ApiValidationError(Object object, String field, Object rejectedValue, String message) {
        super();
    }
}