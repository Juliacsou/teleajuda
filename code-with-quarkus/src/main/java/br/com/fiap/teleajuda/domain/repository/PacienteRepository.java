package br.com.fiap.teleajuda.domain.repository;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.pessoa.Paciente;

public interface PacienteRepository {
    Paciente criar(Paciente paciente);
    Paciente buscarPorCpf(String cpf) throws EntidadeNaoLocalizada;
    void editar(Paciente paciente);
}
