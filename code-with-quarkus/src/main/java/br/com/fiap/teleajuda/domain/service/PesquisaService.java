package br.com.fiap.teleajuda.domain.service;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.PesquisaSatisfacao;
import br.com.fiap.teleajuda.domain.model.pessoa.Paciente;

import java.util.List;

public interface PesquisaService {
    PesquisaSatisfacao criar(PesquisaSatisfacao pesquisa);

    List<PesquisaSatisfacao> exibirPesquisasPaciente (Paciente paciente);
    PesquisaSatisfacao buscarPorId(int id) throws EntidadeNaoLocalizada;
    List<PesquisaSatisfacao> exibitTodasPesquisas ();

    void editar(PesquisaSatisfacao pesquisa);

    void excluirPesquisa(int id);
}
