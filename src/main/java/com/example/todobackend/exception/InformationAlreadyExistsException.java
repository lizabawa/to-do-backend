package com.example.todobackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InformationAlreadyExistsException extends RuntimeException{

    public InformationAlreadyExistsException(String message){
        super(message);
    }
}
