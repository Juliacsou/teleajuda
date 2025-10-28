package br.com.fiap.teleajuda.application.service;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.PesquisaSatisfacao;
import br.com.fiap.teleajuda.domain.model.pessoa.Paciente;
import br.com.fiap.teleajuda.domain.service.PesquisaService;

import java.util.List;

public class PesquisaServiceImpl implements PesquisaService {
    @Override
    public PesquisaSatisfacao criar(PesquisaSatisfacao pesquisa) {
        return null;
    }

    @Override
    public List<PesquisaSatisfacao> exibirPesquisasPaciente(Paciente paciente) {
        return List.of();
    }

    @Override
    public PesquisaSatisfacao buscarPorId(int id) throws EntidadeNaoLocalizada {
        return null;
    }

    @Override
    public List<PesquisaSatisfacao> exibitTodasPesquisas() {
        return List.of();
    }

    @Override
    public void editar(PesquisaSatisfacao pesquisa) {

    }

    @Override
    public void excluirPesquisa(int id) {

    }
}
