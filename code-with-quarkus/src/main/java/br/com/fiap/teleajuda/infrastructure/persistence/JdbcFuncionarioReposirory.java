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
                stmt.setString(2, funcionario.getNome());
                stmt.setString(3, funcionario.getEmail());

                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0){
                    throw new UnsupportedOperationException("Erro ao salvar, nenhuma linha do banco foi alterada");
                }

                return funcionario;

            }catch (SQLException e) {
            throw new UnsupportedOperationException("Erro ao salvaro o cliente");
            } 
        }

        @Override
        public Funcionario buscarPorCodigo(String id) throws EntidadeNaoLocalizada {
            String sqlFunc = """
                SELECT ID, NOME, EMAIL FROM FUNCIONARIO WHERE ID = ?
                """;

            try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlFunc)) {

            stmt.setString(1, id);
            ResultSet resultSet = stmt.executeQuery();

                if (resultSet.next()) {
                    int idFromBd = resultSet.getInt("ID");
                    String nome = resultSet.getString("NOME");
                    String email = resultSet.getString("EMAIL");

                    resultSet.close();

                    return new Funcionario(nome, email, null, idFromBd);
                }
            } catch (SQLException e) {
                throw new EntidadeNaoLocalizada("Erro ao buscar funcionário por id", e);
            }

            throw new EntidadeNaoLocalizada("Funcionario nao encontrado");
        }

        @Override
        public void editar(Funcionario funcionario) {
            String sql = """
                UPDATE FUNCIONARIO SET NOME = ?, EMAIL = ?
                WHERE ID = ?
                """;

            try (Connection conn = databaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, funcionario.getNome());
                stmt.setString(2, funcionario.getEmail());
                stmt.setLong(3, funcionario.getCodigo());

                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new InfraestruturaException("Erro ao editar funcionario, nenhuma linha foi afetada");
                }

            } catch (SQLException e) {
                throw new InfraestruturaException("Erro ao editar funcionario", e);
            }
        }

    
}
