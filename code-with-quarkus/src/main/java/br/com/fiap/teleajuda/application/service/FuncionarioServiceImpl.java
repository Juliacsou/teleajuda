package br.com.fiap.teleajuda.application.service;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.pessoa.Funcionario;
import br.com.fiap.teleajuda.domain.model.pessoa.Login;
import br.com.fiap.teleajuda.domain.model.pessoa.Paciente;
import br.com.fiap.teleajuda.domain.repository.FuncionarioRepository;
import br.com.fiap.teleajuda.domain.service.FuncionarioService;
import br.com.fiap.teleajuda.domain.service.LoginService;

public class FuncionarioServiceImpl implements FuncionarioService {

    private final LoginService loginService;
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioServiceImpl(LoginService loginService, FuncionarioRepository funcionarioRepository) {
        this.loginService = loginService;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public Funcionario criarFuncionario(Funcionario funcionario) {
        try {
            funcionarioRepository.buscarPorCodigo()
            throw new UnsupportedOperationException("Esse paciente já existe");
        } catch (EntidadeNaoLocalizada e) {
            loginService.criarLogin(funcionario.getLogin());
            try {
                Login login = loginService.buscarPorUser(funcionario.getLogin().getUser_login());
                funcionario.setLogin(login);
            } catch (EntidadeNaoLocalizada ex) {
                throw new RuntimeException(ex);
            }
            return funcionarioRepository.criarFuncionario(funcionario);
        }

        return null;
    }

    @Override
    public Funcionario buscarPorCodigo(int id) throws EntidadeNaoLocalizada {
        return null;
    }

    @Override
    public Funcionario buscarPorLoginId(int id_login) throws EntidadeNaoLocalizada {
        return null;
    }

    @Override
    public void editarFuncionario(Funcionario funcionario) {

    }

    @Override
    public void excluirFuncionario(int id) {

    }
}
