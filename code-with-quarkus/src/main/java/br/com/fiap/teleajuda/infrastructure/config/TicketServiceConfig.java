package br.com.fiap.teleajuda.infrastructure.config;

import br.com.fiap.teleajuda.application.service.TicketServiceImpl;
import br.com.fiap.teleajuda.domain.repository.TicketRepository;
import br.com.fiap.teleajuda.domain.service.TicketService;
import jakarta.enterprise.context.RequestScoped;

public class TicketServiceConfig {
    private final TicketRepository ticketRepository;

    public TicketServiceConfig(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @RequestScoped
    public TicketService ticketService(){
        return new TicketServiceImpl(ticketRepository);
    }
}
