package br.com.fiap.teleajuda.domain.repository;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Funcionario;

public interface FuncionarioRepository {
    Funcionario criarFuncionario(Funcionario funcionario);

    Funcionario buscarPorCpf(String cpf) throws EntidadeNaoLocalizada;
    Funcionario validarFuncionario (String cpf, String senha);

    void editarFuncionario(Funcionario funcionario);

    void excluirFuncionario(String cpf);
}
