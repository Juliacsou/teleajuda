package br.com.fiap.teleajuda.infrastructure.api.rest;


import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.PesquisaSatisfacao;
import br.com.fiap.teleajuda.domain.model.Ticket;
import br.com.fiap.teleajuda.interfaces.TicketController;
import br.com.fiap.teleajuda.interfaces.dto.output.PesquisaOutputDto;
import br.com.fiap.teleajuda.interfaces.dto.output.TicketOutputDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/ticket")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TicketRestController {

    private final TicketController ticketController;

    @Inject
    public TicketRestController(TicketController ticketController) {
        this.ticketController = ticketController;
    }

    @POST
    public Response criarTicket(Ticket ticketInput) {
        try {
            Ticket ticket = this.ticketController.criar(ticketInput);
            return Response.status(Response.Status.CREATED).entity(ticket).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/id/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        try {
            TicketOutputDto ticket = this.ticketController.buscarPorId(id);
            return Response.ok(ticket).build();
        } catch (RuntimeException | EntidadeNaoLocalizada e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public Response exibirTodos() {
        try {
            List<Ticket> tickets = this.ticketController.exibirTodosTickets();
            return Response.ok(tickets).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("paciente/{cpf}")
    public Response buscarPorPaciente(@PathParam("cpf") String cpf) {
        try {
            List<Ticket> ticket = this.ticketController.exibitTicketsPaciente(cpf);
            return Response.ok(ticket).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("funcionario/{cpf}")
    public Response buscarPorFuncionario(@PathParam("cpf") String cpf) {
        try {
            List<Ticket> ticket = this.ticketController.exibitTicketsFuncionario(cpf);
            return Response.ok(ticket).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/descricao")
    public Response editarDescricao(Ticket ticketInput) {
        try {
            this.ticketController.editarDescricaoTicket(ticketInput);
            return Response.status(Response.Status.ACCEPTED).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/responder")
    public Response responder(Ticket ticketInput) {
        try {
            this.ticketController.responder(ticketInput);
            return Response.status(Response.Status.ACCEPTED).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/abrir/{id}")
    public Response abrirTicket(@PathParam("id") int id) {
        try {
            this.ticketController.abrirTicket(id);
            return Response.status(Response.Status.ACCEPTED).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/fechar/{id}")
    public Response fecharTicket(@PathParam("id") int id) {
        try {
            this.ticketController.fecharTicket(id);
            return Response.status(Response.Status.ACCEPTED).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/id/{id}")
    public Response delete(@PathParam("id") int id) {
        try {
            this.ticketController.deletarTicket(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
