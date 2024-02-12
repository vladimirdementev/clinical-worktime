package com.example.testprojectforktelabs.Exceptions;

public class NotFoundEntityException extends RuntimeException{

    public NotFoundEntityException(String message) {
        super(message);
    }
}
