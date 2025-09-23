package br.com.fiap.teleajuda.infrastructure.persistence;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.pessoa.User;
import br.com.fiap.teleajuda.domain.repository.UserRepository;
import br.com.fiap.teleajuda.infrastructure.exceptions.InfraestruturaException;

import java.sql.*;

public class JdbcUserReposirory implements UserRepository {

        private DatabaseConnection databaseConnection;

        public void JdbcClienteRepository(DatabaseConnection databaseConnection) {
            this.databaseConnection = databaseConnection;
        }


        @Override
        public User buscarUser(String user) throws EntidadeNaoLocalizada {
            String sqlUser = """
                SELECT USER, SENHA, TIPO FROM USER WHERE USER = ?
                """;            
            
            try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlUser)) {

                stmt.setString(1, user);
                ResultSet resultSet = stmt.executeQuery();

                if (resultSet.next()) {
                    String userFromBd = resultSet.getString("USER");
                    String senha = resultSet.getString("SENHA");
                    String tipo = resultSet.getString("TIPO");

                    resultSet.close();

                    return new User(userFromBd, senha, tipo);
                }

            } catch (SQLException e) {
                throw new EntidadeNaoLocalizada("Erro ao buscar usuario por id", e);
            }
            throw new EntidadeNaoLocalizada("Erro ao buscar usuario por id");
        }


        @Override
        public User criar(User user) {
            String sql = """
                INSERT INTO USER (USER, SENHA, TIPO)
                VALUES (?, ?, ?)
                """;

            try (Connection conn = this.databaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setString(1, user.getUser());
                stmt.setString(2, user.getSenha());
                stmt.setString(3, user.getTipo());

                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0){
                    throw new UnsupportedOperationException("Erro ao salvar usuario pois nenhuma linha do banco foi alterada");
                }

                return user;

            }catch (SQLException e) {
            throw new UnsupportedOperationException("Erro ao criar o usuario");
            }
        }


        @Override
        public void editar(User user) {
            String sql = """
                UPDATE PACIENTE SET USER = ?, SENHA = ?, TIPO = ?
                WHERE USER = ?
                """;

            try (Connection conn = databaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, user.getUser());
                stmt.setString(2, user.getSenha());
                stmt.setString(3, user.getTipo());
                stmt.setString(4, user.getUser());

                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new InfraestruturaException("Erro ao editar usuario pois nenhuma linha foi afetada");
                }

            } catch (SQLException e) {
                throw new InfraestruturaException("Erro ao editar usuario", e);
            }
        }

    
}
