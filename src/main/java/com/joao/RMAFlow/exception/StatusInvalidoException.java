package com.joao.RMAFlow.exception;

public class StatusInvalidoException extends RuntimeException{
    public StatusInvalidoException(String message) {
        super("Nao foi possivel encontrar este " + message);
    }
}
