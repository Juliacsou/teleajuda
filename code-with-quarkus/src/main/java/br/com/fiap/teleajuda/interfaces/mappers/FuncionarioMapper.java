package br.com.fiap.teleajuda.interfaces.mappers;

import br.com.fiap.teleajuda.domain.model.Funcionario;
import br.com.fiap.teleajuda.interfaces.dto.output.FuncionarioOutputDto;

public class FuncionarioMapper {

    private FuncionarioMapper(){}

    public static FuncionarioOutputDto toDto (Funcionario funcionario){
        FuncionarioOutputDto funcionarioOutputDto = new FuncionarioOutputDto();
        funcionarioOutputDto.setCpf_funcionario(funcionario.getCpf_funcionario());
        funcionarioOutputDto.setNm_funcionario(funcionario.getNm_funcionario());
        funcionarioOutputDto.setMail_funcionario(funcionario.getMail_funcionario());
        funcionarioOutputDto.setSenha(funcionario.getSenha());

        return funcionarioOutputDto;
    }
}
