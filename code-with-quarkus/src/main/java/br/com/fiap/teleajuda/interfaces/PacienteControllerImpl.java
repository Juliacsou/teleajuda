package br.com.fiap.teleajuda.interfaces;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Paciente;
import br.com.fiap.teleajuda.domain.service.PacienteService;
import br.com.fiap.teleajuda.interfaces.dto.output.PacienteOutputDto;
import br.com.fiap.teleajuda.interfaces.mappers.PacienteMapper;

public class PacienteControllerImpl implements PacienteController {

    private final PacienteService pacienteService;

    public PacienteControllerImpl(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @Override
    public PacienteOutputDto criar(Paciente paciente) {
        Paciente novoPaciente = pacienteService.criar(paciente);
        return PacienteMapper.toDto(novoPaciente);
    }

    @Override
    public PacienteOutputDto buscarPorCpf(String cpf) throws EntidadeNaoLocalizada {
        Paciente novoPaciente = pacienteService.buscarPorCpf(cpf);
        return PacienteMapper.toDto(novoPaciente);
    }

    @Override
    public PacienteOutputDto validarPaciente(String cpf, String senha) throws EntidadeNaoLocalizada {
        Paciente novoPaciente = pacienteService.validarPaciente(cpf, senha);
        return PacienteMapper.toDto(novoPaciente);
    }

    @Override
    public void editar(Paciente paciente) {
        pacienteService.editar(paciente);
    }

    @Override
    public void excluirPaciente(String cpf) {
        pacienteService.excluirPaciente(cpf);
    }
}
