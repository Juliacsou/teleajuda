package br.com.fiap.teleajuda.domain.repository;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Funcionario;
import br.com.fiap.teleajuda.domain.model.Ticket;
import br.com.fiap.teleajuda.domain.model.Paciente;

import java.util.List;

public interface TicketRepository {
    Ticket criar(Ticket ticket);

    Ticket buscarPorId(int id) throws EntidadeNaoLocalizada;
    List<Ticket> exibirTodosTickets ();
    List<Ticket> exibitTicketsPaciente (String cpf);
    List<Ticket> exibitTicketsFuncionario (String cpf);

    void editarDescricaoTicket(Ticket ticket);
    void responder(Ticket ticket);
    void fecharTicket(int id);
    void abrirTicket(int id);

    void deletarTicket(int id);

}
