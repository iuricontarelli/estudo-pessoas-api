package com.pessoas.pessoas.exception;

public class ForbiddenRequestException extends RuntimeException{

    public ForbiddenRequestException(String message){
        super(message);
    }
}
