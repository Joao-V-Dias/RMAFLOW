package com.joao.RMAFlow.exception;

public class NotFoundEntityException extends RuntimeException{
    public NotFoundEntityException(String message) {
        super(message);
    }
}
