package br.com.fiap.teleajuda.interfaces.mappers;

import br.com.fiap.teleajuda.domain.model.Paciente;
import br.com.fiap.teleajuda.interfaces.dto.output.PacienteOutputDto;

public class PacienteMapper {
    private PacienteMapper(){}

    public static PacienteOutputDto toDto (Paciente paciente){
        PacienteOutputDto pacienteOutputDto = new PacienteOutputDto();
        pacienteOutputDto.setCpf_paciente(paciente.getCpf_paciente());
        pacienteOutputDto.setNm_paciente(paciente.getNm_paciente());
        pacienteOutputDto.setTel_paciente(paciente.getTel_paciente());
        pacienteOutputDto.setMail_paciente(paciente.getMail_paciente());
        pacienteOutputDto.setRghc(paciente.getRghc());
        pacienteOutputDto.setDt_nasc_paciente(paciente.getDt_nasc_paciente());
        pacienteOutputDto.setSenha_paciente(paciente.getSenha_paciente());

        return pacienteOutputDto;

    }
}
