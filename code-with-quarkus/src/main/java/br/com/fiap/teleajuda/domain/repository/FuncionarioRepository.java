package br.com.fiap.teleajuda.domain.repository;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.pessoa.Funcionario;
import br.com.fiap.teleajuda.domain.model.pessoa.Login;

public interface FuncionarioRepository {
    Funcionario criarFuncionario(Funcionario funcionario);

    Funcionario buscarPorCodigo(int id) throws EntidadeNaoLocalizada;

    Funcionario buscarPorLoginId(int id_login) throws EntidadeNaoLocalizada;

    void editarFuncionario(Funcionario funcionario);

    void excluirFuncionario(int id);
}
