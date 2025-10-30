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
    List<Ticket> exibitTicketsPaciente (Paciente paciente);
    List<Ticket> exibitTicketsFuncionario (Funcionario funcionario);

    void editarDescricaoTicket(String problema, int id);
    void responder(String resposta, int idTicket);
    void fecharTicket(int id);
    void abrirTicket(int id);

    void deletarTicket(int id);
}
