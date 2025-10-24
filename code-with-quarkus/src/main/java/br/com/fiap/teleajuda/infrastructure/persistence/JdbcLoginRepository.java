package br.com.fiap.teleajuda.infrastructure.persistence;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.pessoa.Login;
import br.com.fiap.teleajuda.domain.repository.LoginRepository;
import br.com.fiap.teleajuda.infrastructure.exceptions.InfraestruturaException;

import java.sql.*;

public class JdbcLoginRepository implements LoginRepository {

        private final DatabaseConnection databaseConnection;

        public JdbcLoginRepository(DatabaseConnection databaseConnection) {
            this.databaseConnection = databaseConnection;
        }

    @Override
        public Login buscarPorId(int id) throws EntidadeNaoLocalizada {
            String sqlUser = """
                SELECT ID_LOGIN, USER_LOGIN, SENHA_LOGIN, TP_LOGIN
                FROM T_TAJ_LOGIN
                WHERE ID_LOGIN = ?
                """;            
            
            try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlUser)) {

                stmt.setInt(1, id);

                try(ResultSet rs = stmt.executeQuery()){
                    if (rs.next()) {
                        Login login = mapearLogin(rs);
                        return login;
                    }else{
                        throw new EntidadeNaoLocalizada("Não foi possivel encontrar o login");
                    }
                }
            } catch (SQLException e) {
                throw new InfraestruturaException("Erro ao buscar usuario por id", e);
            }
        }

    @Override
    public Login buscarPorPaciente(String cpf_paciente) throws EntidadeNaoLocalizada {
        String sql = """
            SELECT L.ID_LOGIN, L.USER_LOGIN, L.SENHA_LOGIN, L.TP_LOGIN
            FROM T_TAJ_LOGIN L
            JOIN T_TAJ_PACIENTE P ON P.LOGIN_id_login = L.ID_LOGIN
            WHERE P.cpf_paciente = ?
            """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf_paciente); // ex: "123.456.789-00"

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Login login = mapearLogin(rs);
                    return login;
                }else{
                    throw new EntidadeNaoLocalizada("Não foi possivel encontrar o login");
                }
            }
        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao buscar usuario por id", e);
        }
    }

    @Override
    public Login buscarPorFuncionario(int id_funcionario) throws EntidadeNaoLocalizada {
        String sql = """
        SELECT L.ID_LOGIN,
               L.USER_LOGIN,
               L.SENHA_LOGIN,
               L.TP_LOGIN
          FROM T_TAJ_LOGIN L
          JOIN T_TAJ_FUNCIONARIO F ON F.LOGIN_ID_LOGIN = L.ID_LOGIN
         WHERE F.ID_FUNCIONARIO = ?
        """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id_funcionario);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Login login = mapearLogin(rs);
                    return login;
                } else {
                    throw new EntidadeNaoLocalizada("Não foi possível encontrar o login do funcionário.");
                }
            }

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao buscar login do funcionário por ID", e);
        }
    }

    @Override
    public Login buscarPorUserSenha(String user_login, String senha_login) throws EntidadeNaoLocalizada {
        final String sql = """
        SELECT ID_LOGIN,
               USER_LOGIN,
               SENHA_LOGIN,
               TP_LOGIN
          FROM T_TAJ_LOGIN
         WHERE USER_LOGIN = ? AND SENHA_LOGIN = ?
        """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user_login);
            stmt.setString(2, senha_login);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearLogin(rs);
                } else {
                    throw new EntidadeNaoLocalizada("Usuário ou senha inválidos.");
                }
            }

        } catch (SQLException e) {
            throw new EntidadeNaoLocalizada("Erro ao buscar login por usuário e senha.", e);
        }
    }



    @Override
        public Login criarLogin(Login login) {
            String sql = """                
                INSERT INTO T_TAJ_LOGIN (USER_LOGIN, SENHA_LOGIN, TP_LOGIN)
                VALUES (?, ?, ?)
                """;

            try (Connection conn = this.databaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, login.getUser_login());
                stmt.setString(2, login.getSenha_login());
                stmt.setString(3, login.getTp_login());

                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0){
                    throw new UnsupportedOperationException("Erro ao criar usuario pois nenhuma linha do banco foi alterada");
                }
                return login;
            }catch (SQLException e) {
            throw new UnsupportedOperationException("Erro ao criar o usuario");
            }
        }


        @Override
        public void editar(Login login) {
            String sql = """
                UPDATE T_TAJ_LOGIN
                SET USER_LOGIN = ?, SENHA_LOGIN = ?, TP_LOGIN = ?
                WHERE ID_LOGIN = ?
                """;

            try (Connection conn = databaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, login.getUser_login());
                stmt.setString(2, login.getSenha_login());
                stmt.setString(3, login.getTp_login());
                stmt.setInt(4, login.getId_login());

                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new InfraestruturaException("Erro ao editar usuario pois nenhuma linha foi afetada");
                }

            } catch (SQLException e) {
                throw new InfraestruturaException("Erro ao editar usuario", e);
            }
        }

    @Override
    public void excluirLogin(int id_login) {
        String sql = "DELETE FROM T_TAJ_LOGIN WHERE ID_LOGIN = ?";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id_login);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new InfraestruturaException("Erro ao excluir usuario");
            }
        }catch (SQLException e) {
            throw new InfraestruturaException("Erro ao excluir usuario", e);
        }

    }

    private Login mapearLogin(ResultSet rs) throws SQLException {
        int idFromBd = rs.getInt("ID_LOGIN");
        String user = rs.getString("USER_LOGIN");
        String senha = rs.getString("SENHA_LOGIN");
        String tipo = rs.getString("TP_LOGIN");

        rs.close();

        return new Login(idFromBd, user, senha, tipo);
    }


}
