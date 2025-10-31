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
import java.util.Objects;

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
    public List<Ticket> exibitTicketsPaciente(String cpf) {
        return ticketService.exibitTicketsPaciente(cpf);
    }

    @Override
    public List<Ticket> exibitTicketsFuncionario(String cpf) {
        return ticketService.exibitTicketsFuncionario(cpf);
    }

    @Override
    public void editarDescricaoTicket(Ticket ticket) {
        ticketService.editarDescricaoTicket(ticket);
    }

    @Override
    public void responder(Ticket ticket) {
        ticketService.responder(ticket);
    }

    @Override
    public void fecharTicket(int id) {
        try {
            Ticket ticket = ticketService.buscarPorId(id);
            if(Objects.equals(ticket.getStatus(), "F")){
                throw new RuntimeException("Ticket já está fechado");
            }else{
                ticketService.fecharTicket(id);
            }
        } catch (EntidadeNaoLocalizada e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void abrirTicket(int id) {
        try {
            Ticket ticket = ticketService.buscarPorId(id);
            if(Objects.equals(ticket.getStatus(), "A")){
                throw new RuntimeException("Ticket já está aberto");
            }else{
                ticketService.abrirTicket(id);
            }
        } catch (EntidadeNaoLocalizada e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletarTicket(int id) {
        ticketService.deletarTicket(id);
    }
}
