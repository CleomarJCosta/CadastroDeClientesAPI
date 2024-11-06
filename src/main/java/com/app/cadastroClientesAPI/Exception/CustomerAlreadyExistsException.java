package com.app.cadastroClientesAPI.Exception;

public class CustomerAlreadyExistsException extends Exception{
    public CustomerAlreadyExistsException(String message){
        super(message);
    }
}
