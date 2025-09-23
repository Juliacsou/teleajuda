package br.com.fiap.teleajuda.domain.repository;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.PesquisaSatisfacao;
import br.com.fiap.teleajuda.domain.model.pessoa.Paciente;

import java.util.List;

public interface PesquisaRepository {
    PesquisaSatisfacao criar(PesquisaSatisfacao pesquisa);
    List<PesquisaSatisfacao> exibirPesquisasPaciente (Paciente paciente);
    PesquisaSatisfacao buscarPorId(int id) throws EntidadeNaoLocalizada;
    void editar(PesquisaSatisfacao pesquisa);
    List<PesquisaSatisfacao> exibitTodasPesquisas ();
}
