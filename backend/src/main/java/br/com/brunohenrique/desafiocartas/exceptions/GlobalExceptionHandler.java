package br.com.brunohenrique.desafiocartas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Issue handleBadRequestException(BadRequestException e) {

    return e.getIssue();
  }

  @ExceptionHandler
  public Issue handleException(Exception e) {
    return new Issue(e.getMessage(), "INTERNAL_ERROR");
  }
}
