package com.example.trainingcenter.controllers;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ControllerUtils {
    static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage
        );
        List<FieldError> viewErrors = new ArrayList<>() { };

        boolean match;
        for (FieldError error : bindingResult.getFieldErrors()){
            if (viewErrors.isEmpty()) viewErrors.add(error);
            match = false;
            for (FieldError viewError : viewErrors){
                if (viewError.getField().equals(error.getField())){
                    match = true;
                    break;
                }
            }
            if (!match) viewErrors.add(error);
        }

        return viewErrors.stream().collect(collector);
    }
}
