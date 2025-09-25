package br.com.fiap.teleajuda.domain.repository;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Ticket;
import br.com.fiap.teleajuda.domain.model.pessoa.Funcionario;
import br.com.fiap.teleajuda.domain.model.pessoa.Paciente;

import java.util.List;

public interface TicketRepository {
    Ticket criar(Ticket ticket);
    Ticket buscarPorId(int id) throws EntidadeNaoLocalizada;
    void editarDescricao(String problema, int id);
    void responder(int idFuncionario, String resposta, int idTicket);
    void fecharTicket(int id);
    void abrirTicket(int id);
    List<Ticket> exibirTodosTickets ();
    List<Ticket> exibitTicketsPaciente (Paciente paciente);
    List<Ticket> exibitTicketsFuncionario (Funcionario funcionario);
}
