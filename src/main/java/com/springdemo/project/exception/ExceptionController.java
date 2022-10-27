package com.springdemo.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController
{
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<NotFoundExceptionResponse> customException(NotFoundException exc)
    {
        NotFoundExceptionResponse error = new NotFoundExceptionResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        //return response entity
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    public ResponseEntity<Object> globalException(Exception exc)
    {
        return new ResponseEntity<>("Sorry , this was a bad request!",HttpStatus.BAD_REQUEST);
    }


}
