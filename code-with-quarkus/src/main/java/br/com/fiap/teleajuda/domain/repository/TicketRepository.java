package br.com.fiap.teleajuda.domain.repository;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Ticket;
import br.com.fiap.teleajuda.domain.model.pessoa.Paciente;

import java.util.List;

public interface TicketRepository {
    Ticket criar(Ticket ticket);

    Ticket buscarPorId(int id) throws EntidadeNaoLocalizada;
    List<Ticket> exibirTodosTickets ();
    List<Ticket> exibitTicketsPaciente (Paciente paciente);

    void editarDescricaoTicket(String problema, int id);
    void responder(int idFuncionario, String resposta, int idTicket);
    void fecharTicket(int id);
    void abrirTicket(int id);

    void deletarTicket(int id);

}
