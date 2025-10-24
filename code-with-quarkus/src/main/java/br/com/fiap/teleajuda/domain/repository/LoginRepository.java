package br.com.fiap.teleajuda.domain.repository;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.pessoa.Login;

public interface LoginRepository {
    Login criarLogin(Login login);

    Login buscarPorId(int id) throws EntidadeNaoLocalizada;
    Login buscarPorPaciente(String cpf_paciente) throws EntidadeNaoLocalizada;
    Login buscarPorFuncionario(int id_funcionario) throws EntidadeNaoLocalizada;
    Login buscarPorUserSenha(String user_login, String senha_login) throws EntidadeNaoLocalizada;

    void editar(Login user);

    void excluirLogin(int id);
}
