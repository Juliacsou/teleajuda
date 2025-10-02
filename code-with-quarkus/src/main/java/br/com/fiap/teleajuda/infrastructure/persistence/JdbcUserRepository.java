package br.com.fiap.teleajuda.infrastructure.persistence;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.pessoa.User;
import br.com.fiap.teleajuda.domain.repository.UserRepository;
import br.com.fiap.teleajuda.infrastructure.exceptions.InfraestruturaException;

import java.sql.*;

public class JdbcUserRepository implements UserRepository {

        private final DatabaseConnection databaseConnection;

        public JdbcUserRepository(DatabaseConnection databaseConnection) {
            this.databaseConnection = databaseConnection;
        }


    @Override
        public User buscarUser(int id) throws EntidadeNaoLocalizada {
            String sqlUser = """
                SELECT ID_LOGIN, USER_LOGIN, SENHA_LOGIN, TP_LOGIN FROM USER WHERE ID_LOGIN = ?
                """;            
            
            try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlUser)) {

                stmt.setInt(1, id);
                ResultSet resultSet = stmt.executeQuery();

                if (resultSet.next()) {
                    int idFromBd = resultSet.getInt("ID_LOGIN");
                    String user = resultSet.getString("USER_LOGIN");
                    String senha = resultSet.getString("SENHA_LOGIN");
                    String tipo = resultSet.getString("TP_LOGIN");

                    resultSet.close();

                    return new User(id, user, senha, tipo);
                }

            } catch (SQLException e) {
                throw new EntidadeNaoLocalizada("Erro ao buscar usuario por id", e);
            }
            throw new EntidadeNaoLocalizada("Erro ao buscar usuario por id");
        }


        @Override
        public User criar(User user) {
            String sql = """
                INSERT INTO T_TAJ_LOGIN (id_login, user_login, senha_login, tp_login)
                VALUES (?, ?, ?, ?)
                """;

            try (Connection conn = this.databaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, user.getId());
                stmt.setString(2, user.getUser());
                stmt.setString(3, user.getSenha());
                stmt.setString(4, user.getTipo());

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
                UPDATE PACIENTE SET USER_LOGIN = ?, SENHA_LOGIN = ?, TP_LOGIN = ?
                WHERE ID_LOGIN = ?
                """;

            try (Connection conn = databaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, user.getUser());
                stmt.setString(2, user.getSenha());
                stmt.setString(3, user.getTipo());
                stmt.setInt(4, user.getId());

                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new InfraestruturaException("Erro ao editar usuario pois nenhuma linha foi afetada");
                }

            } catch (SQLException e) {
                throw new InfraestruturaException("Erro ao editar usuario", e);
            }
        }

    
}
