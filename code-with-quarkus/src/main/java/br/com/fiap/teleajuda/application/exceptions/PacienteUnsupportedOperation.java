package br.com.fiap.teleajuda.application.exceptions;

public class PacienteUnsupportedOperation extends RuntimeException {
    public PacienteUnsupportedOperation(String message) {
        super(message);
    }
}
