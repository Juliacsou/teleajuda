package br.com.fiap.teleajuda.application.service;

import br.com.fiap.teleajuda.application.exceptions.LoginUnsupportedOperation;
import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.pessoa.Login;
import br.com.fiap.teleajuda.domain.repository.LoginRepository;
import br.com.fiap.teleajuda.domain.service.LoginService;

public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;

    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public Login criarLogin(Login login) {
        try {
            buscarPorUser(login.getUser_login());
            throw new LoginUnsupportedOperation("Este usuario já existe, escolha outro!");
        } catch (EntidadeNaoLocalizada e) {
            return loginRepository.criarLogin(login);
        }
    }

    @Override
    public Login buscarPorId(int id) throws EntidadeNaoLocalizada {
        return loginRepository.buscarPorId(id);
    }

    @Override
    public Login buscarPorPaciente(String cpf_paciente) throws EntidadeNaoLocalizada {
        return loginRepository.buscarPorPaciente(cpf_paciente);
    }

    @Override
    public Login buscarPorFuncionario(int id_funcionario) throws EntidadeNaoLocalizada {
        return loginRepository.buscarPorFuncionario(id_funcionario);
    }

    @Override
    public Login buscarPorUserSenha(String user_login, String senha_login) throws EntidadeNaoLocalizada {
        return loginRepository.buscarPorUserSenha(user_login, senha_login);
    }

    @Override
    public Login buscarPorUser(String user) throws EntidadeNaoLocalizada {
        return loginRepository.buscarPorUser(user);
    }

    @Override
    public void editar(Login user) throws EntidadeNaoLocalizada {
        loginRepository.editar(user);
    }

    @Override
    public void excluirLogin(int id) {
        loginRepository.excluirLogin(id);
    }
}
