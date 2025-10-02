package br.com.fiap.teleajuda.infrastructure.main;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.PesquisaSatisfacao;
import br.com.fiap.teleajuda.domain.model.Ticket;
import br.com.fiap.teleajuda.domain.model.pessoa.Funcionario;
import br.com.fiap.teleajuda.domain.model.pessoa.Paciente;
import br.com.fiap.teleajuda.domain.model.pessoa.User;
import br.com.fiap.teleajuda.domain.repository.*;
import br.com.fiap.teleajuda.infrastructure.exceptions.InfraestruturaException;
import io.quarkus.runtime.QuarkusApplication;
import jakarta.inject.Inject;

public class TeleAjudaApplication implements QuarkusApplication {

    @Inject
    PacienteRepository pacienteRepository;
    @Inject
    UserRepository userRepository;
    @Inject
    FuncionarioRepository funcionarioRepository;
    @Inject
    TicketRepository ticketRepository;
    @Inject
    PesquisaRepository pesquisaRepository;

    User userPaciente = new User(1029, "Mariazinha", "1234", "P");
    User userFuncionario = new User(1116, "Robertinha", "1234", "F");
    Paciente paciente = new Paciente("Maria", "maria@email.com", userPaciente, "1234567891033", "11 91234-5678", "2001-02-17", "3");
    Funcionario funcionario = new Funcionario("Roberta", "roberta@mail.com", userFuncionario, 10);
    PesquisaSatisfacao pesquisa = new PesquisaSatisfacao(1, 5, 5, 5, paciente);
    Ticket ticket = new Ticket(1, "Ajuda com mic", "Meu microfone não esta funcionando", true, paciente, null);

    @Override
    public int run(String... args) throws Exception {
        createPaciente(paciente);
        findByCpf("12345678901234");
        updatePaciente(paciente);


        createFuncionario(funcionario);
        findFuncByID(funcionario);
        updateFuncionario(funcionario);
        
        createTicket(ticket);
        //findTicketByID(ticket.getCodigo());
        //updateTicketDesc(ticket.getDescricao(), ticket.getCodigo());
        //responderTicket(funcionario.getCodigo(), ticket.getResposta(), ticket.getCodigo());
        //closeTicket(ticket.getCodigo());
        //openTicket(ticket.getCodigo());
        //findTickets();
        //findTicketsByPaciente(paciente);
        //findTicketByFuncionario(funcionario);
        return 0;
    }

    private void findTicketByFuncionario(Funcionario funcionario) {
    }

    private void findTicketsByPaciente(Paciente paciente) {
        
    }

    private void findTickets() {
        
    }

    private void openTicket(int codigo) {
        
    }

    private void closeTicket(int codigo) {
        
    }

    private void responderTicket(int codigo, String resposta, int codigo1) {
        
    }

    private void updateTicketDesc(String descricao, int codigo) {
        
    }

    private void findTicketByID(int codigo) {
        
    }

    private void createTicket(Ticket ticket) {
        try {
            this.ticketRepository.criar(ticket);
            System.out.println("\nTicket criado com sucesso: ");
            System.out.println(ticket.getPaciente().getNome());
            System.out.println(ticket.getAssunto());
            System.out.println(ticket.getDescricao());

        } catch (InfraestruturaException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateFuncionario(Funcionario funcionario) {
        try {
            funcionario.setNome("Novo nome");
            funcionario.setEmail("novo@email.com");
            this.funcionarioRepository.editar(funcionario);
            System.out.println("\nfuncionario alterado");
            System.out.println(funcionario.getCodigo());
            System.out.println(funcionario.getNome());
            System.out.println(funcionario.getEmail());
        } catch (InfraestruturaException e) {
            System.out.println(e.getMessage());
        }
    }

    private void findFuncByID(Funcionario funcionario) {
        try {
            Funcionario funcionarioLocalizado = this.funcionarioRepository.buscarPorCodigo(2);
            System.out.println("\nFuncionario localizado: ");
            System.out.println(funcionarioLocalizado.getCodigo());
            System.out.println(funcionarioLocalizado.getNome());
            System.out.println(funcionarioLocalizado.getEmail());
        } catch (EntidadeNaoLocalizada e) {
            System.out.println(e.getMessage());
        }
    }

    private void createFuncionario(Funcionario funcionario) {
        try {
            this.userRepository.criar(funcionario.getUser());
            this.funcionarioRepository.criar(funcionario);
            System.out.println("\nFuncionario criado com sucesso: ");
            System.out.println(funcionario.getNome());
            System.out.println(funcionario.getCodigo());
        } catch (InfraestruturaException e) {
            System.out.println(e.getMessage());
        }
    }


    private void updatePaciente(Paciente paciente) {
        try {
            paciente.setNome("Novo nome");
            paciente.setEmail("novo@email.com");
            this.pacienteRepository.editar(paciente);
            System.out.println("paciente alterado");
        } catch (InfraestruturaException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createPaciente(Paciente paciente) {
        try {
            this.userRepository.criar(paciente.getUser());
            this.pacienteRepository.criar(paciente);
            System.out.println("\nPaciente criado com sucesso: ");
            System.out.println(paciente.getNome());
            System.out.println(paciente.getCpf());
        } catch (InfraestruturaException e) {
            System.out.println(e.getMessage());
        }
    }

    private void findByCpf(String cpf) {
        try {
            Paciente pacienteLocalizado = this.pacienteRepository.buscarPorCpf(cpf);
            System.out.println("\nPaciente localizado: ");
            System.out.println(pacienteLocalizado.getNome());
        } catch (EntidadeNaoLocalizada e) {
            System.out.println(e.getMessage());
        }
    }


}
