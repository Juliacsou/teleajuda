package br.com.fiap.teleajuda.infrastructure.config;

import br.com.fiap.teleajuda.application.service.ApiKeyValidator;
import br.com.fiap.teleajuda.infrastructure.security.ApiKeyValidatorImpl;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class SecurityConfig {

    @ApplicationScoped
    public ApiKeyValidator apiKeyValidator(@ConfigProperty(name = "api.key") String validApiKey) {
        return new ApiKeyValidatorImpl(validApiKey);
    }

}
