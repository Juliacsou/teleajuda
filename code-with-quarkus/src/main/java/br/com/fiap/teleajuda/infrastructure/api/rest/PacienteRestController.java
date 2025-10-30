package br.com.fiap.teleajuda.infrastructure.api.rest;


import br.com.fiap.teleajuda.interfaces.PacienteController;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/paciente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PacienteRestController {

    private final PacienteController pacienteController;

    @Inject
    public PacienteRestController(PacienteController pacienteController) {
        this.pacienteController = pacienteController;
    }


}
