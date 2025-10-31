package br.com.fiap.teleajuda.application.service;

import br.com.fiap.teleajuda.application.exceptions.FuncionarioUnsupportedOperation;
import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Funcionario;
import br.com.fiap.teleajuda.domain.repository.FuncionarioRepository;
import br.com.fiap.teleajuda.domain.service.FuncionarioService;

public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public Funcionario criarFuncionario(Funcionario funcionario) {
        try{
            buscarPorCpf(funcionario.getCpf_funcionario());
            throw new FuncionarioUnsupportedOperation("Funcionario já cadastrado");
        } catch (EntidadeNaoLocalizada e) {
            return funcionarioRepository.criarFuncionario(funcionario);
        }
    }

    @Override
    public Funcionario buscarPorCpf(String cpf) throws EntidadeNaoLocalizada {
        return funcionarioRepository.buscarPorCpf(cpf);
    }

    @Override
    public Funcionario validarFuncionario(String email, String senha) {
        return funcionarioRepository.validarFuncionario(email,senha);
    }

    @Override
    public void editarFuncionario(Funcionario funcionario) {
        try {
            Funcionario funcionarioExistente = buscarPorCpf(funcionario.getCpf_funcionario());
            funcionarioRepository.editarFuncionario(funcionario);
        } catch (EntidadeNaoLocalizada e) {
            throw new FuncionarioUnsupportedOperation("Cliente não encontrado");
        }
    }

    @Override
    public void excluirFuncionario(String cpf) {
        try {
            Funcionario funcionarioExistente = buscarPorCpf(cpf);
            funcionarioRepository.excluirFuncionario(cpf);
        } catch (EntidadeNaoLocalizada e) {
            throw new FuncionarioUnsupportedOperation("Cliente não encontrado");
        }
    }
}
