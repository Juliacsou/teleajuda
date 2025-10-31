package br.com.fiap.teleajuda.interfaces;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Funcionario;
import br.com.fiap.teleajuda.domain.service.FuncionarioService;
import br.com.fiap.teleajuda.interfaces.dto.output.FuncionarioOutputDto;
import br.com.fiap.teleajuda.interfaces.mappers.FuncionarioMapper;

public class FuncionarioControllerImpl implements FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioControllerImpl(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @Override
    public Funcionario criarFuncionario(Funcionario funcionario) {
        return funcionarioService.criarFuncionario(funcionario);
    }

    @Override
    public FuncionarioOutputDto buscarPorCpf(String cpf) throws EntidadeNaoLocalizada {
        Funcionario funcionario = funcionarioService.buscarPorCpf(cpf);
        return FuncionarioMapper.toDto(funcionario);
    }


    @Override
    public FuncionarioOutputDto validarFuncionario(String email, String senha) {
        Funcionario novoFunc = funcionarioService.validarFuncionario(email, senha);
        return FuncionarioMapper.toDto(novoFunc);
    }

    @Override
    public void editarFuncionario(Funcionario funcionario) {
        funcionarioService.editarFuncionario(funcionario);
    }

    @Override
    public void excluirFuncionario(String cpf) {
        funcionarioService.excluirFuncionario(cpf);
    }
}
