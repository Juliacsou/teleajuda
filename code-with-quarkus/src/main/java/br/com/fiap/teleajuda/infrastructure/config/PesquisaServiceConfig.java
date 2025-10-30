package br.com.fiap.teleajuda.infrastructure.config;

import br.com.fiap.teleajuda.application.service.PesquisaServiceImpl;
import br.com.fiap.teleajuda.domain.repository.PesquisaRepository;
import br.com.fiap.teleajuda.domain.service.PesquisaService;
import jakarta.enterprise.context.RequestScoped;

public class PesquisaServiceConfig {

    private final PesquisaRepository pesquisaRepository;

    public PesquisaServiceConfig(PesquisaRepository pesquisaRepository) {
        this.pesquisaRepository = pesquisaRepository;
    }

    @RequestScoped
    public PesquisaService pesquisaService(){
        return new PesquisaServiceImpl(pesquisaRepository);
    }

}
