package br.com.fiap.teleajuda.domain.service;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.pessoa.Funcionario;

public interface FuncionarioService {
    Funcionario criarFuncionario(Funcionario funcionario);

    Funcionario buscarPorCodigo(int id) throws EntidadeNaoLocalizada;

    Funcionario buscarPorLoginId(int id_login) throws EntidadeNaoLocalizada;

    void editarFuncionario(Funcionario funcionario);

    void excluirFuncionario(int id);
}
