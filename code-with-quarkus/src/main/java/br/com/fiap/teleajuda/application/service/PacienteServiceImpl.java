package br.com.fiap.teleajuda.application.service;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.pessoa.Login;
import br.com.fiap.teleajuda.domain.model.pessoa.Paciente;
import br.com.fiap.teleajuda.domain.repository.PacienteRepository;
import br.com.fiap.teleajuda.domain.service.LoginService;
import br.com.fiap.teleajuda.domain.service.PacienteService;

import java.util.List;

public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;
    private final LoginService loginService;

    public PacienteServiceImpl(PacienteRepository pacienteRepository, LoginService loginService) {
        this.pacienteRepository = pacienteRepository;
        this.loginService = loginService;
    }

    @Override
    public Paciente criar(Paciente paciente) {
        try {
            pacienteRepository.buscarPorCpf(paciente.getCpf_paciente());
            throw new UnsupportedOperationException("Esse paciente já existe");
        } catch (EntidadeNaoLocalizada e) {
            loginService.criarLogin(paciente.getLogin());
            try {
                Login login = loginService.buscarPorUser(paciente.getLogin().getUser_login());
                paciente.setLogin(login);
            } catch (EntidadeNaoLocalizada ex) {
                throw new RuntimeException(ex);
            }
            return pacienteRepository.criar(paciente);
        }
    }

    @Override
    public Paciente buscarPorCpf(String cpf) throws EntidadeNaoLocalizada {
        return pacienteRepository.buscarPorCpf(cpf);
    }

    @Override
    public List<Paciente> buscarPacientes() {
        return pacienteRepository.buscarPacientes();
    }

    @Override
    public Paciente buscarPorLoginId(int id_login) throws EntidadeNaoLocalizada {
        return pacienteRepository.buscarPorLoginId(id_login);
    }

    @Override
    public void editar(Paciente paciente) {
        pacienteRepository.editar(paciente);
    }

    @Override
    public void excluirPaciente(Paciente paciente) {
        pacienteRepository.excluirPaciente(paciente);

    }
}
