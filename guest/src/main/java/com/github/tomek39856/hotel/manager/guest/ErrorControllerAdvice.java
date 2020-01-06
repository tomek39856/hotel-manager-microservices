package com.github.tomek39856.hotel.manager.guest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorControllerAdvice {
  @ExceptionHandler
  public String error(Exception e) {
    return e.getMessage();
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public void notFound(NotFoundException e) {
  }
}