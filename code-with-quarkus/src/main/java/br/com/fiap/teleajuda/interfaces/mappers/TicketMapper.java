package br.com.fiap.teleajuda.interfaces.mappers;

import br.com.fiap.teleajuda.domain.model.Ticket;
import br.com.fiap.teleajuda.interfaces.dto.output.TicketOutputDto;

public class TicketMapper {
    private TicketMapper(){}

    public static TicketOutputDto toDto (Ticket ticket){
        TicketOutputDto ticketOutputDto = new TicketOutputDto();
        ticketOutputDto.setId_ticket(ticket.getId_ticket());
        ticketOutputDto.setAssunto(ticket.getAssunto());
        ticketOutputDto.setDescricao(ticket.getDescricao());
        ticketOutputDto.setResposta(ticket.getResposta());
        ticketOutputDto.setDt_abertura(ticket.getDt_abertura());
        ticketOutputDto.setDt_fechamento(ticket.getDt_fechamento());
        ticketOutputDto.setStatus(ticket.getStatus());
        ticketOutputDto.setPaciente(ticket.getPaciente());
        ticketOutputDto.setFuncionario(ticket.getFuncionario());

        return ticketOutputDto;
    }
}
