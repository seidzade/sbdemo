package com.java2016.sbdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException {
  public ObjectNotFoundException(String type, long id) {
    super(String.format("%s with id %s not found.",type,id));
  }
}
