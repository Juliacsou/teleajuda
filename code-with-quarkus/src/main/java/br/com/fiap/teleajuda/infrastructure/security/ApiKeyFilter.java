package br.com.fiap.teleajuda.infrastructure.security;

import br.com.fiap.teleajuda.application.service.ApiKeyValidator;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
@Priority(Priorities.ENTITY_CODER)
public class ApiKeyFilter implements ContainerRequestFilter {

    private static final String API_KEY_HEADER = "X-API-Key";

    private final ApiKeyValidator apiKeyValidator;

    @Inject
    public ApiKeyFilter(ApiKeyValidator apiKeyValidator) {
        this.apiKeyValidator = apiKeyValidator;
    }

    @Override
public void filter(ContainerRequestContext requestContext) throws IOException {

    // 1. Verificar se é uma requisição OPTIONS (Preflight)
    if ("OPTIONS".equalsIgnoreCase(requestContext.getMethod())) {

        // ** SOLUÇÃO DEFINITIVA: ABORTAR COM RESPOSTA 204 E CABEÇALHOS CORS **
        // Embora o Quarkus deva gerenciar isso, esta etapa garante que a resposta
        // de preflight não seja bloqueada pelo seu filtro ou por outras camadas.
        requestContext.abortWith(
            Response.ok()
                // Garante que o frontend receba o cabeçalho 'Allow-Origin'
                .header("Access-Control-Allow-Origin", requestContext.getHeaderString("Origin"))
                // Garante que o navegador saiba que o X-API-Key é aceito
                .header("Access-Control-Allow-Headers", "accept,authorization,content-type,x-requested-with,x-api-key")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, PATCH, OPTIONS")
                .status(Response.Status.NO_CONTENT) // 204 No Content é o padrão para preflight
                .build()
        );
        return;
    }

    // 2. Lógica de autenticação para todos os outros métodos (GET, POST, etc.)
    final String apiKey = requestContext.getHeaderString(API_KEY_HEADER);
    if (!apiKeyValidator.isValid(apiKey)) {
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Invalid API key")
                        .build()
                );
        }
}

}
