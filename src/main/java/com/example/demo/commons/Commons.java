package com.example.demo.commons;

import com.example.demo.exception.RequestValidationException;
import com.example.demo.pojos.errors.ApiFieldError;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.validation.Errors;

import java.util.stream.Collectors;

public class Commons {
    public static void validateFieldRequest(Errors errors) throws RequestValidationException {
        if (errors.hasErrors()) {
            throw new RequestValidationException(new ApiFieldError(errors.getAllErrors()
                    .stream()
                    .map(m -> m.getDefaultMessage())
                    .collect(Collectors.toList())));
        }
    }

    public static String createAleatoryPassword() {
        return RandomStringUtils.randomAlphanumeric(8);
    }
}
