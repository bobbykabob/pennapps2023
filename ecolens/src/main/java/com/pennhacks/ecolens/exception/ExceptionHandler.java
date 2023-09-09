package com.pennhacks.ecolens.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionHandler {
    //Handle TrashCanNotFoundException
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<TrashCanErrorResponse> handleTrashCanNotFoundException(TrashCanNotFoundException exception,
                                                                                 HttpServletRequest req){
        TrashCanErrorResponse error = new TrashCanErrorResponse(
                ZonedDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                req.getRequestURI(),
                exception.getMessage()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ItemErrorResponse> handleTrashCanNotFoundException(ItemNotFoundException exception,
                                                                                 HttpServletRequest req){
        ItemErrorResponse error = new ItemErrorResponse(
                ZonedDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                req.getRequestURI(),
                exception.getMessage()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
