package com.example.demo.pojos.errors;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author wilson.cajisaca
 * Create message api error for response resource request
 */
@Getter
@Setter
public class ApiFieldError implements Serializable {
    private List<String> messages;

    public ApiFieldError(List<String> messages){
        this.messages = messages;
    }
}
