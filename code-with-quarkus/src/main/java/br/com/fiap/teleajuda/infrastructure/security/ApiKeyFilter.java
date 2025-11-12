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
            // Se for OPTIONS, retorna imediatamente.
            // O Quarkus agora será responsável por adicionar os headers CORS.
            return;
        }

        // 2. Continua com a verificação de segurança para todos os outros métodos (GET, POST, etc.)
        final String apiKey = requestContext.getHeaderString(API_KEY_HEADER);
        if (!apiKeyValidator.isValid(apiKey)) {
            // Lança 401 Unauthorized para requisições bloqueadas
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("Invalid API key")
                            .build()
            );
        }
    }
}
