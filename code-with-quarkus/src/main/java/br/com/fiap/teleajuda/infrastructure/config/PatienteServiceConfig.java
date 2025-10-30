package br.com.fiap.teleajuda.infrastructure.config;

import br.com.fiap.teleajuda.application.service.PacienteServiceImpl;
import br.com.fiap.teleajuda.domain.repository.PacienteRepository;
import br.com.fiap.teleajuda.domain.service.PacienteService;
import jakarta.enterprise.context.RequestScoped;

public class PatienteServiceConfig {

    private final PacienteRepository pacienteRepository;

    public PatienteServiceConfig(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @RequestScoped
    public PacienteService pacienteService(){
        return new PacienteServiceImpl(pacienteRepository);
    }
}
