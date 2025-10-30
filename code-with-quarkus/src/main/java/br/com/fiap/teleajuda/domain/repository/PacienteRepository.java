package br.com.fiap.teleajuda.domain.repository;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Paciente;

import java.util.List;

public interface PacienteRepository {
    Paciente criar(Paciente paciente);

    Paciente buscarPorCpf(String cpf) throws EntidadeNaoLocalizada;
    Paciente validarPaciente(String email, String senha) throws EntidadeNaoLocalizada;

    void editar(Paciente paciente);

    void excluirPaciente(String cpf);
}
