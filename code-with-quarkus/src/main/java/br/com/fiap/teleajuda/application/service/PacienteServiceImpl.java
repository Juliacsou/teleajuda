package br.com.fiap.teleajuda.application.service;

import br.com.fiap.teleajuda.application.exceptions.PacienteUnsupportedOperation;
import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Paciente;
import br.com.fiap.teleajuda.domain.repository.PacienteRepository;
import br.com.fiap.teleajuda.domain.service.PacienteService;

public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }


    @Override
    public Paciente criar(Paciente paciente) {
        try{
            buscarPorCpf(paciente.getCpf_paciente());
            throw new PacienteUnsupportedOperation("Paciente já cadastrado");
        } catch (EntidadeNaoLocalizada e) {
            return pacienteRepository.criar(paciente);
        }
    }

    @Override
    public Paciente buscarPorCpf(String cpf) throws EntidadeNaoLocalizada {
        return pacienteRepository.buscarPorCpf(cpf);
    }

    @Override
    public Paciente validarPaciente(String email, String senha) throws EntidadeNaoLocalizada {
        return pacienteRepository.validarPaciente(email,senha);
    }

    @Override
    public void editar(Paciente paciente) {
        try {
            Paciente pacienteExistente = buscarPorCpf(paciente.getCpf_paciente());
            pacienteRepository.criar(paciente);
        } catch (EntidadeNaoLocalizada e) {
            throw new PacienteUnsupportedOperation("Paciente não encontrado");
        }

    }

    @Override
    public void excluirPaciente(String cpf) {
        try {
            Paciente pacienteExistente = buscarPorCpf(cpf);
            pacienteRepository.excluirPaciente(cpf);
        } catch (EntidadeNaoLocalizada e) {
            throw new PacienteUnsupportedOperation("Paciente não encontrado");
        }

    }
}
