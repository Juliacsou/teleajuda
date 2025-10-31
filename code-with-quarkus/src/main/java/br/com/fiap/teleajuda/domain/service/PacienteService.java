package br.com.fiap.teleajuda.domain.service;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Paciente;

public interface PacienteService {
    Paciente criar(Paciente paciente);

    Paciente buscarPorCpf(String cpf) throws EntidadeNaoLocalizada;
    Paciente validarPaciente(String cpf, String senha) throws EntidadeNaoLocalizada;

    void editar(Paciente paciente);

    void excluirPaciente(String cpf);
}
