package br.com.fiap.teleajuda.interfaces;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Funcionario;
import br.com.fiap.teleajuda.interfaces.dto.output.FuncionarioOutputDto;

public interface FuncionarioController {
    Funcionario criarFuncionario(Funcionario funcionario);

    FuncionarioOutputDto buscarPorCpf(String cpf) throws EntidadeNaoLocalizada;
    FuncionarioOutputDto validarFuncionario (String email, String senha);

    void editarFuncionario(Funcionario funcionario);

    void excluirFuncionario(String cpf);
}
