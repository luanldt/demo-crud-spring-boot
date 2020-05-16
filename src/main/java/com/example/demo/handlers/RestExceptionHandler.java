package com.example.demo.handlers;

import com.example.demo.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler {
  @ExceptionHandler({ResourceNotFoundException.class})
  public ResponseEntity<Map<String, String>> resourceNotFound(ResourceNotFoundException e) {
    Map<String, String> data = new HashMap<>();
    data.put("message", e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
  }
}
