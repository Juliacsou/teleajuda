package br.com.fiap.teleajuda.application.service;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Ticket;
import br.com.fiap.teleajuda.domain.model.pessoa.Paciente;
import br.com.fiap.teleajuda.domain.service.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {
    @Override
    public Ticket criar(Ticket ticket) {
        return null;
    }

    @Override
    public Ticket buscarPorId(int id) throws EntidadeNaoLocalizada {
        return null;
    }

    @Override
    public List<Ticket> exibirTodosTickets() {
        return List.of();
    }

    @Override
    public List<Ticket> exibitTicketsPaciente(Paciente paciente) {
        return List.of();
    }

    @Override
    public void editarDescricaoTicket(String problema, int id) {

    }

    @Override
    public void responder(String resposta, int idTicket) {

    }

    @Override
    public void fecharTicket(int id) {

    }

    @Override
    public void abrirTicket(int id) {

    }

    @Override
    public void deletarTicket(int id) {

    }
}
