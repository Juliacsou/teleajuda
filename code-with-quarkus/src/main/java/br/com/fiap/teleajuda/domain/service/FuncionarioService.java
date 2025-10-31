package br.com.fiap.teleajuda.domain.service;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Funcionario;

public interface FuncionarioService {
    Funcionario criarFuncionario(Funcionario funcionario);

    Funcionario buscarPorCpf(String cpf) throws EntidadeNaoLocalizada;
    Funcionario validarFuncionario (String cpf, String senha);

    void editarFuncionario(Funcionario funcionario);

    void excluirFuncionario(String cpf);
}
