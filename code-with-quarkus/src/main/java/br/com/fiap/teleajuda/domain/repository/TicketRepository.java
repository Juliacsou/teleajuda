package br.com.fiap.teleajuda.domain.repository;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Ticket;
import br.com.fiap.teleajuda.domain.model.pessoa.Funcionario;

import java.util.List;

public interface TicketRepository {
    Ticket criar(Ticket ticket);
    Ticket buscarPorCodigo(String codigo) throws EntidadeNaoLocalizada;
    Ticket editar(Ticket ticket);
    Ticket responder(Funcionario funcionario, String resposta);
    Ticket fecharTicket(boolean status);
    Ticket abrirTicket(boolean status);
    List<Ticket> exibitTodosTickets ();
}
