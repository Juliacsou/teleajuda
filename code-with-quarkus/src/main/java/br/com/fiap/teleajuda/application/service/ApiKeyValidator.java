package br.com.fiap.teleajuda.application.service;

public interface ApiKeyValidator {

    boolean isValid(String apiKey);

    boolean isPresent(String apikey);
}
