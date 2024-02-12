package com.example.testprojectforktelabs.exceptions;

public class NotFoundEntityException extends RuntimeException{

    public NotFoundEntityException(String message) {
        super(message);
    }
}
