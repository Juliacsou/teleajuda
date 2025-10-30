package br.com.fiap.teleajuda.infrastructure.config;

import br.com.fiap.teleajuda.domain.service.FuncionarioService;
import br.com.fiap.teleajuda.domain.service.PacienteService;
import br.com.fiap.teleajuda.domain.service.PesquisaService;
import br.com.fiap.teleajuda.domain.service.TicketService;
import br.com.fiap.teleajuda.interfaces.*;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ControllerConfig {

    @ApplicationScoped
    public FuncionarioController funcionarioController(FuncionarioService funcionarioService) {
        return new FuncionarioControllerImpl(funcionarioService);
    }

    @ApplicationScoped
    public PacienteController pacienteController(PacienteService pacienteService) {
        return new PacienteControllerImpl(pacienteService);
    }

    @ApplicationScoped
    public PesquisaController pesquisaController(PesquisaService pesquisaService) {
        return new PesquisaControllerImpl(pesquisaService);
    }

    @ApplicationScoped
    public TicketController ticketController(TicketService ticketService) {
        return new TicketControllerImpl(ticketService);
    }

}
