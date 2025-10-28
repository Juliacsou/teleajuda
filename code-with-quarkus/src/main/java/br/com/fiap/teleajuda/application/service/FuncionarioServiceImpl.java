package br.com.fiap.teleajuda.application.service;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.pessoa.Funcionario;
import br.com.fiap.teleajuda.domain.service.FuncionarioService;

public class FuncionarioServiceImpl implements FuncionarioService {
    @Override
    public Funcionario criarFuncionario(Funcionario funcionario) {
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
