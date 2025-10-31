package br.com.fiap.teleajuda.infrastructure.config;

import br.com.fiap.teleajuda.domain.repository.*;
import br.com.fiap.teleajuda.infrastructure.persistence.*;
import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DatabaseConfig {

    @ApplicationScoped
    public DatabaseConnection databaseConnection(AgroalDataSource dataSource) {
        return new DatabaseConnectionImpl(dataSource);
    }

    @ApplicationScoped
    public FuncionarioRepository funcionarioRepository(DatabaseConnection databaseConnection) {
        return new JdbcFuncionarioRepository(databaseConnection);
    }

    @ApplicationScoped
    public PacienteRepository pacienteRepository(DatabaseConnection databaseConnection) {
        return new JdbcPacienteRepository (databaseConnection);
    }

    @ApplicationScoped
    public PesquisaRepository pesquisaRepository(DatabaseConnection databaseConnection) {
        return new JdbcPesquisaRepository(databaseConnection);
    }

    @ApplicationScoped
    public TicketRepository ticketRepository(DatabaseConnection databaseConnection) {
        return new JdbcTicketRepository (databaseConnection);
    }

}