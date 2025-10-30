package br.com.fiap.teleajuda.application.service;

import br.com.fiap.teleajuda.application.exceptions.PesquisaUnsupportedOperation;
import br.com.fiap.teleajuda.application.exceptions.TicketUnsupportedOperation;
import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Funcionario;
import br.com.fiap.teleajuda.domain.model.Ticket;
import br.com.fiap.teleajuda.domain.model.Paciente;
import br.com.fiap.teleajuda.domain.repository.TicketRepository;
import br.com.fiap.teleajuda.domain.service.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket criar(Ticket ticket) {
        try{
            return ticketRepository.criar(ticket);
        } catch (Exception e) {
            throw new PesquisaUnsupportedOperation("Erro ao criar ticket");
        }
    }

    @Override
    public Ticket buscarPorId(int id) throws EntidadeNaoLocalizada {
        return ticketRepository.buscarPorId(id);
    }

    @Override
    public List<Ticket> exibirTodosTickets() {
        return ticketRepository.exibirTodosTickets();
    }

    @Override
    public List<Ticket> exibitTicketsPaciente(Paciente paciente) {
        return ticketRepository.exibitTicketsPaciente(paciente);
    }

    @Override
    public List<Ticket> exibitTicketsFuncionario(Funcionario funcionario) {
        return ticketRepository.exibitTicketsFuncionario(funcionario);
    }

    @Override
    public void editarDescricaoTicket(String problema, int id) {
        try {
            Ticket ticketExistente = buscarPorId(id);
            ticketRepository.editarDescricaoTicket(problema, id);
        } catch (EntidadeNaoLocalizada e) {
            throw new TicketUnsupportedOperation("Erro ao editar descrição");
        }
    }

    @Override
    public void responder(String resposta, int idTicket) {
        try {
            Ticket ticketExistente = buscarPorId(idTicket);
            ticketRepository.responder(resposta, idTicket);
        } catch (EntidadeNaoLocalizada e) {
            throw new TicketUnsupportedOperation("Erro ao responder ticket");
        }
    }

    @Override
    public void fecharTicket(int id) {
        try {
            Ticket ticketExistente = buscarPorId(id);
            ticketRepository.fecharTicket(id);
        } catch (EntidadeNaoLocalizada e) {
            throw new TicketUnsupportedOperation("Erro ao fechar ticket");
        }
    }

    @Override
    public void abrirTicket(int id) {
        try {
            Ticket ticketExistente = buscarPorId(id);
            ticketRepository.abrirTicket(id);
        } catch (EntidadeNaoLocalizada e) {
            throw new TicketUnsupportedOperation("Erro ao abrir ticket");
        }
    }

    @Override
    public void deletarTicket(int id) {
        try {
            Ticket ticketExistente = buscarPorId(id);
            ticketRepository.deletarTicket(id);
        } catch (EntidadeNaoLocalizada e) {
            throw new TicketUnsupportedOperation("Erro ao deletar ticket");
        }
    }
}
