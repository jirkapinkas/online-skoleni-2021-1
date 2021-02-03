package com.test.eshopweb.controller;

import com.test.eshopweb.exception.DeleteException;
import com.test.eshopweb.pojo.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<Message> handleDeleteException(DeleteException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Message(e.getMessage()));
    }

}
