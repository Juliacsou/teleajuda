package br.com.fiap.teleajuda.interfaces;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Funcionario;
import br.com.fiap.teleajuda.domain.model.Paciente;
import br.com.fiap.teleajuda.domain.model.Ticket;
import br.com.fiap.teleajuda.interfaces.dto.output.TicketOutputDto;

import java.util.List;

public interface TicketController {
    Ticket criar(Ticket ticket);

    TicketOutputDto buscarPorId(int id) throws EntidadeNaoLocalizada;
    List<Ticket> exibirTodosTickets ();
    List<Ticket> exibitTicketsPaciente (String cpf);
    List<Ticket> exibitTicketsFuncionario (String cpf);

    void editarDescricaoTicket(Ticket ticket);
    void responder(Ticket ticket);
    void fecharTicket(int id);
    void abrirTicket(int id);

    void deletarTicket(int id);
}
