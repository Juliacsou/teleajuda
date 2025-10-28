package br.com.fiap.teleajuda.application.exceptions;

public class LoginUnsupportedOperation extends RuntimeException {
    public LoginUnsupportedOperation(String message) {
        super(message);
    }
}
