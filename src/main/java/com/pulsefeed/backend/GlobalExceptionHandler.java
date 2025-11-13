package com.pulsefeed.backend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String , Object>> handleRuntimeExceptions(RuntimeException runtimeException){
        Map<String,Object> error = new HashMap<>();
        error.put("status","error");
        error.put("message",runtimeException.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException exception){

        Map<String , Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("status" ,"error");

        String fieldError = exception.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .findFirst()
                .orElse("Invalid Input");

        stringObjectMap.put("error" ,fieldError);

        return new ResponseEntity<>(stringObjectMap,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String ,Object>> handleExceptions(Exception e){
        Map<String, Object> error = new HashMap<>();
        error.put("status","error");
        error.put("message","Internal Server Error : "+e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
