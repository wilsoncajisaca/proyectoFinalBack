package com.example.demo.exception;

import com.example.demo.pojos.errors.ApiFieldError;

public class RequestValidationException extends Exception {
    private static final long serialVersionUID = 1L;
    private ApiFieldError fieldError;

    public RequestValidationException(ApiFieldError listError){
        this.fieldError = listError;
    }

    public ApiFieldError getApiListError(){
        return this.fieldError;
    }
}
