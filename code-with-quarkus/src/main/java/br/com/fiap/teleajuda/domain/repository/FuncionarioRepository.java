package br.com.fiap.teleajuda.domain.repository;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.pessoa.Funcionario;

public interface FuncionarioRepository {
    Funcionario criar(Funcionario funcionario);
    Funcionario buscarPorCodigo(String id) throws EntidadeNaoLocalizada;
    void editar(Funcionario funcionario);
}
