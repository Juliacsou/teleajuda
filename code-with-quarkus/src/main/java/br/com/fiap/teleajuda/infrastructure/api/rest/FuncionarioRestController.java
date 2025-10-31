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
    @Path("/cpf/{cpf}")
    public Response buscarPorCpf(@PathParam("cpf") String cpf) {
        try {
            FuncionarioOutputDto funcionario = this.funcionarioController.buscarPorCpf(cpf);
            return Response.ok(funcionario).build();
        } catch (RuntimeException | EntidadeNaoLocalizada e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("validar/{email}/{senha}")
    public Response validarFuncionario(@PathParam("email") String email, @PathParam("senha") String senha) {
        try {
            FuncionarioOutputDto cliente = this.funcionarioController.validarFuncionario(email, senha);
            return Response.ok(cliente).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response criarFuncionario(Funcionario funcionarioInput) {
        try {
            Funcionario funcionario = this.funcionarioController.criarFuncionario(funcionarioInput);
            return Response.status(Response.Status.CREATED).entity(funcionario).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    public Response editarFuncionario(Funcionario funcionarioInput) {
        try {
            this.funcionarioController.editarFuncionario(funcionarioInput);
            return Response.status(Response.Status.ACCEPTED).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/cpf/{cpf}")
    public Response delete(@PathParam("cpf") String cpf) {
        try {
            this.funcionarioController.excluirFuncionario(cpf);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }




}
