package br.com.fiap.teleajuda.infrastructure.api.rest;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Funcionario;
import br.com.fiap.teleajuda.interfaces.FuncionarioController;
import br.com.fiap.teleajuda.interfaces.dto.output.FuncionarioOutputDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/funcionario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FuncionarioRestController {

    private final FuncionarioController funcionarioController;

    @Inject
    public FuncionarioRestController(FuncionarioController funcionarioController) {
        this.funcionarioController = funcionarioController;
    }

    @GET
    @Path("/{cpf}")
    public Response buscarPorCpf(@PathParam("cpf") String cpf) {
        try {
            FuncionarioOutputDto cliente = this.funcionarioController.buscarPorCpf(cpf);
            return Response.ok(cliente).build();
        } catch (RuntimeException | EntidadeNaoLocalizada e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


}
