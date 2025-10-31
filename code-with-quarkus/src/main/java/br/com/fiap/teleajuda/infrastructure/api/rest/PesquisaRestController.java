package br.com.fiap.teleajuda.infrastructure.api.rest;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.PesquisaSatisfacao;
import br.com.fiap.teleajuda.interfaces.PesquisaController;
import br.com.fiap.teleajuda.interfaces.dto.output.PesquisaOutputDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/pesquisa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PesquisaRestController {

    private final PesquisaController pesquisaController;

    @Inject
    public PesquisaRestController(PesquisaController pesquisaController) {
        this.pesquisaController = pesquisaController;
    }

    @POST
    public Response criarPesquisa(PesquisaSatisfacao pesquisaInput) {
        try {
            PesquisaSatisfacao pesquisa = this.pesquisaController.criar(pesquisaInput);
            return Response.status(Response.Status.CREATED).entity(pesquisa).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("cpf/{cpf}")
    public Response exibirPorPaciente(String cpf) {
        try {
            List<PesquisaSatisfacao> pesquisas = this.pesquisaController.exibirPesquisasPaciente(cpf);
            return Response.ok(pesquisas).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("id/{id}")
    public Response exibirPorId(int id) {
        try {
            PesquisaOutputDto pesquisa = this.pesquisaController.buscarPorId(id);
            return Response.ok(pesquisa).build();
        } catch (RuntimeException | EntidadeNaoLocalizada e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public Response exibirTodos() {
        try {
            List<PesquisaSatisfacao> pesquisas = this.pesquisaController.exibirTodasPesquisas();
            return Response.ok(pesquisas).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    public Response editarPaciente(PesquisaSatisfacao pesquisaInput) {
        try {
            this.pesquisaController.editar(pesquisaInput);
            return Response.status(Response.Status.ACCEPTED).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/id/{id}")
    public Response delete(@PathParam("id") int id) {
        try {
            this.pesquisaController.excluirPesquisa(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
