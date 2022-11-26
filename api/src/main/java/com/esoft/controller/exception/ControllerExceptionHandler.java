package com.esoft.controller.exception;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {

  private static final String CODE = "400";
  private static final String MESSAGE = "Division by zero!";
  private static final String PARAMS = "Empty params!";

  @ExceptionHandler({
      NoHandlerFoundException.class,
      IllegalArgumentException.class,
      HttpRequestMethodNotSupportedException.class,
      InvalidFormatException.class,
  })
  public ResponseEntity<CustomExceptionAnswer> handleNoHandlerFoundException(Exception ex) {
    return new ResponseEntity<>(
        new CustomExceptionAnswer(CODE, ex.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({
      JacksonException.class,
  })
  public ResponseEntity<CustomExceptionAnswer> handleJacksonException(Exception ex) {
    return new ResponseEntity<>(
        new CustomExceptionAnswer(CODE, PARAMS), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({
      MethodArgumentNotValidException.class
  })
  public ResponseEntity<CustomExceptionAnswer> handleMethodArgumentNotValidException(Exception ex) {
    return new ResponseEntity<>(
        new CustomExceptionAnswer(CODE, MESSAGE), HttpStatus.BAD_REQUEST);
  }
}
