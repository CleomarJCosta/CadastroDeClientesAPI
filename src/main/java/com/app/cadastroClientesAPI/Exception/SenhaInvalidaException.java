package com.app.cadastroClientesAPI.Exception;

public class SenhaInvalidaException extends Exception {
    public SenhaInvalidaException(String message){
        super("Senha Inválida");
    }
}
