package br.com.fiap.teleajuda.infrastructure.config;

import br.com.fiap.teleajuda.application.service.FuncionarioServiceImpl;
import br.com.fiap.teleajuda.domain.repository.FuncionarioRepository;
import br.com.fiap.teleajuda.domain.service.FuncionarioService;
import jakarta.enterprise.context.RequestScoped;

public class FuncionarioServiceConfig {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioServiceConfig(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @RequestScoped
    public FuncionarioService funcionarioService(){
        return new FuncionarioServiceImpl(funcionarioRepository);
    }
}
