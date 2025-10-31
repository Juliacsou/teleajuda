package br.com.fiap.teleajuda.infrastructure.api.rest;


import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Paciente;
import br.com.fiap.teleajuda.interfaces.PacienteController;
import br.com.fiap.teleajuda.interfaces.dto.output.PacienteOutputDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/paciente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PacienteRestController {

    private final PacienteController pacienteController;

    @Inject
    public PacienteRestController(PacienteController pacienteController) {
        this.pacienteController = pacienteController;
    }

    @POST
    public Response criarPaciente(Paciente pacienteInput) {
        try {
            PacienteOutputDto paciente = this.pacienteController.criar(pacienteInput);
            return Response.status(Response.Status.CREATED).entity(paciente).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/cpf/{cpf}")
    public Response buscarPorCpf(@PathParam("cpf") String cpf) {
        try {
            PacienteOutputDto paciente = this.pacienteController.buscarPorCpf(cpf);
            return Response.ok(paciente).build();
        } catch (RuntimeException | EntidadeNaoLocalizada e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("validar/{email}/{senha}")
    public Response validarPaciente(@PathParam("email") String email, @PathParam("senha") String senha) {
        try {
            PacienteOutputDto paciente = this.pacienteController.validarPaciente(email, senha);
            return Response.ok(paciente).build();
        } catch (RuntimeException | EntidadeNaoLocalizada e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    public Response editarPaciente(Paciente pacienteInput) {
        try {
            this.pacienteController.editar(pacienteInput);
            return Response.status(Response.Status.ACCEPTED).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/cpf/{cpf}")
    public Response delete(@PathParam("cpf") String cpf) {
        try {
            this.pacienteController.excluirPaciente(cpf);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }




}
