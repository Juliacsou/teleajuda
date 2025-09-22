package br.com.fiap.teleajuda.domain.repository;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.pessoa.Funcionario;

public interface FuncionarioRepository {
    Funcionario criar(Funcionario funcionario);
    Funcionario buscarPorCodigo(String codigo) throws EntidadeNaoLocalizada;
    Funcionario editar(Funcionario funcionario);
}
