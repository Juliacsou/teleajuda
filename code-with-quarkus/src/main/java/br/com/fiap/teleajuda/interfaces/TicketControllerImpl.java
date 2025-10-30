package br.com.fiap.teleajuda.interfaces;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Funcionario;
import br.com.fiap.teleajuda.domain.model.Paciente;
import br.com.fiap.teleajuda.domain.model.Ticket;
import br.com.fiap.teleajuda.domain.service.TicketService;
import br.com.fiap.teleajuda.interfaces.dto.output.FuncionarioOutputDto;
import br.com.fiap.teleajuda.interfaces.dto.output.TicketOutputDto;
import br.com.fiap.teleajuda.interfaces.mappers.TicketMapper;

import java.util.List;

public class TicketControllerImpl implements TicketController{

    private final TicketService ticketService;

    public TicketControllerImpl(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public Ticket criar(Ticket ticket) {
        return ticketService.criar(ticket);
    }

    @Override
    public TicketOutputDto buscarPorId(int id) throws EntidadeNaoLocalizada {
        Ticket ticket = ticketService.buscarPorId(id);
        return TicketMapper.toDto(ticket);
    }

    @Override
    public List<Ticket> exibirTodosTickets() {
        return ticketService.exibirTodosTickets();
    }

    @Override
    public List<Ticket> exibitTicketsPaciente(Paciente paciente) {
        return ticketService.exibitTicketsPaciente(paciente);
    }

    @Override
    public List<Ticket> exibitTicketsFuncionario(Funcionario funcionario) {
        return ticketService.exibitTicketsFuncionario(funcionario);
    }

    @Override
    public void editarDescricaoTicket(String problema, int id) {
        ticketService.editarDescricaoTicket(problema, id);
    }

    @Override
    public void responder(String resposta, int idTicket) {
        ticketService.responder(resposta, idTicket);
    }

    @Override
    public void fecharTicket(int id) {
        ticketService.fecharTicket(id);
    }

    @Override
    public void abrirTicket(int id) {
        ticketService.abrirTicket(id);
    }

    @Override
    public void deletarTicket(int id) {
        ticketService.deletarTicket(id);
    }
}
