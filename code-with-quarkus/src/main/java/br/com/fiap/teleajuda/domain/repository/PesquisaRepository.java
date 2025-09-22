package br.com.fiap.teleajuda.domain.repository;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.PesquisaSatisfacao;

import java.util.List;

public interface PesquisaRepository {
    PesquisaSatisfacao criar(PesquisaSatisfacao pesquisa);
    PesquisaSatisfacao buscarPorCodigo(String codigo) throws EntidadeNaoLocalizada;
    PesquisaSatisfacao editar(PesquisaSatisfacao pesquisa);
    PesquisaSatisfacao deletar(PesquisaSatisfacao pesquisa);
    List<PesquisaSatisfacao> exibitTodasPesquisas ();
}
