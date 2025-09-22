package br.com.fiap.teleajuda.infrastructure.persistence;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.pessoa.Funcionario;
import br.com.fiap.teleajuda.domain.repository.FuncionarioRepository;
import br.com.fiap.teleajuda.infrastructure.exceptions.InfraestruturaException;

import java.sql.*;

public class JdbcFuncionarioReposirory implements FuncionarioRepository {

        private DatabaseConnection databaseConnection;

        public void JdbcClienteRepository(DatabaseConnection databaseConnection) {
            this.databaseConnection = databaseConnection;
        }

        @Override
        public Funcionario criar(Funcionario funcionario) {
            String sql = """
                INSERT INTO FUNCIONARIO (ID, NOME, EMAIL)
                VALUES (?, ?, ?)
                """;

            try (Connection conn = this.databaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setInt(1, funcionario.getCodigo());
                
                
                return funcionario

             }catch (SQLException e) {
            throw new UnsupportedOperationException("Unimplemented method 'criar'");
        }
            // TODO Auto-generated method stub
            
        }

        @Override
        public Funcionario buscarPorCodigo(String codigo) throws EntidadeNaoLocalizada {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'buscarPorCodigo'");
        }

        @Override
        public Funcionario editar(Funcionario funcionario) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'editar'");
        }

    
}
