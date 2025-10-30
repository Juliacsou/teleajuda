package br.com.fiap.teleajuda.application.exceptions;

public class TicketUnsupportedOperation extends RuntimeException {
    public TicketUnsupportedOperation(String message) {
        super(message);
    }
}
