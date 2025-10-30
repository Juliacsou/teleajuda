package br.com.fiap.teleajuda.domain.service;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Funcionario;
import br.com.fiap.teleajuda.domain.model.Ticket;
import br.com.fiap.teleajuda.domain.model.Paciente;

import java.util.List;

public interface TicketService {
    Ticket criar(Ticket ticket);

    Ticket buscarPorId(int id) throws EntidadeNaoLocalizada;
    List<Ticket> exibirTodosTickets ();
    List<Ticket> exibitTicketsPaciente (Paciente paciente);
    List<Ticket> exibitTicketsFuncionario (Funcionario funcionario);

    void editarDescricaoTicket(String problema, int id);
    void responder(String resposta, int idTicket);
    void fecharTicket(int id);
    void abrirTicket(int id);

    void deletarTicket(int id);
}
