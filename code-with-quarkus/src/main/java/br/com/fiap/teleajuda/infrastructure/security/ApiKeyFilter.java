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
@Priority(Priorities.AUTHENTICATION)
public class ApiKeyFilter implements ContainerRequestFilter {

    private static final String API_KEY_HEADER = "X-API-Key";

    private final ApiKeyValidator apiKeyValidator;

    @Inject
    public ApiKeyFilter(ApiKeyValidator apiKeyValidator) {
        this.apiKeyValidator = apiKeyValidator;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // 1. Bypass para o Swagger
        if (requestContext.getUriInfo().getPath().contains("swagger") || requestContext.getUriInfo().getPath().contains("q/swagger-ui")) {
            return;
        }

        // 2. [LINHA MAIS IMPORTANTE] Bypass para requisições OPTIONS (Preflight do CORS)
        // Se este "if" não estiver aqui, o CORS VAI FALHAR.
        if (requestContext.getMethod().equalsIgnoreCase("OPTIONS")) {
            return;
        }

        // 3. Validação da API Key
        String apiKey = requestContext.getHeaderString("X-API-KEY");
        if (apiKey == null || !apiKeyValidator.isValid(apiKey)) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("API Key inválida ou ausente.")
                            .build());

        }
    }
}
