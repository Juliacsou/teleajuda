package br.com.fiap.teleajuda.application.service;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.pessoa.Paciente;
import br.com.fiap.teleajuda.domain.service.PacienteService;

import java.util.List;

public class PacienteServiceImpl implements PacienteService {
    @Override
    public Paciente criar(Paciente paciente) {
        return null;
    }

    @Override
    public Paciente buscarPorCpf(String cpf) throws EntidadeNaoLocalizada {
        return null;
    }

    @Override
    public List<Paciente> buscarPacientes() {
        return List.of();
    }

    @Override
    public Paciente buscarPorLoginId(int id_login) throws EntidadeNaoLocalizada {
        return null;
    }

    @Override
    public void editar(Paciente paciente) {

    }

    @Override
    public void excluirPaciente(Paciente paciente) {

    }
}
