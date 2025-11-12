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
        // SOLUÇÃO: Ignorar a verificação para o método OPTIONS (o preflight CORS)
        if (requestContext.getMethod().equalsIgnoreCase("OPTIONS")) {
            return;
        }

        final String apiKey = requestContext.getHeaderString(API_KEY_HEADER);
        if(!apiKeyValidator.isValid(apiKey)) {
            throw new NotAuthorizedException("Invalid API key");
        }

    }
}
