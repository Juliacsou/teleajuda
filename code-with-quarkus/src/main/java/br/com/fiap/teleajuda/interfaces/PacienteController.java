package br.com.fiap.teleajuda.interfaces;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Paciente;
import br.com.fiap.teleajuda.interfaces.dto.output.PacienteOutputDto;

public interface PacienteController {
    PacienteOutputDto criar(Paciente paciente);

    PacienteOutputDto buscarPorCpf(String cpf) throws EntidadeNaoLocalizada;
    PacienteOutputDto validarPaciente(String cpf, String senha) throws EntidadeNaoLocalizada;

    void editar(Paciente paciente);

    void excluirPaciente(String cpf);
}
