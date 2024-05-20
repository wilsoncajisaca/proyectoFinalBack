package com.example.demo.pojos.errors;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author wilson.cajisaca
 * Create message api error for response resource request
 */
@Getter
@Setter
public class ApiError implements Serializable {
    private String message;

    public ApiError(){}

    public ApiError(String message){
        this.message = message;
    }
}
