package br.com.fiap.teleajuda.infrastructure.main;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Main {
    public static void main(String[] args) {
        Quarkus.run(TeleAjudaApplication.class, args);
    }
}
