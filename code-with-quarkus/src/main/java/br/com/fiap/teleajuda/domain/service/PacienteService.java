package br.com.fiap.teleajuda.domain.service;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.pessoa.Paciente;

import java.util.List;

public interface PacienteService {
    Paciente criar(Paciente paciente);

    Paciente buscarPorCpf(String cpf) throws EntidadeNaoLocalizada;
    List<Paciente> buscarPacientes();
    Paciente buscarPorLoginId(int id_login) throws EntidadeNaoLocalizada;

    void editar(Paciente paciente);

    void excluirPaciente(Paciente paciente);
}
