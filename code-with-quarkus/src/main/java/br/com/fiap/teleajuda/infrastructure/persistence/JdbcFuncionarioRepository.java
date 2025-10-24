package br.com.fiap.teleajuda.infrastructure.persistence;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.pessoa.Funcionario;
import br.com.fiap.teleajuda.domain.model.pessoa.Login;
import br.com.fiap.teleajuda.domain.repository.FuncionarioRepository;
import br.com.fiap.teleajuda.infrastructure.exceptions.InfraestruturaException;

import java.sql.*;

public class JdbcFuncionarioRepository implements FuncionarioRepository {

    private final DatabaseConnection databaseConnection;

    public JdbcFuncionarioRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
        public Funcionario criarFuncionario(Funcionario funcionario) {
            String sql = """
                INSERT INTO T_TAJ_FUNCIONARIO (NM_FUNCIONARIO, MAIL_FUNCIONARIO, LOGIN_ID_LOGIN)
                VALUES (?, ?, ?)
                """;

            try (Connection conn = this.databaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"id_funcionario"})) {
                
                stmt.setString(1, funcionario.getNm_funcionario());
                stmt.setString(2, funcionario.getMail_funcionario());
                stmt.setInt(3, funcionario.getLogin().getId_login());

                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0){
                    throw new UnsupportedOperationException("Erro ao criar funcionario, nenhuma linha do banco foi alterada");
                }
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        funcionario.setId_funcionario(rs.getInt(1));
                    }
                }

                return funcionario;

            }catch (SQLException e) {
            throw new UnsupportedOperationException("Erro ao criar o funcionario", e);
            } 
        }

        @Override
        public Funcionario buscarPorCodigo(int id) throws EntidadeNaoLocalizada {
            String sqlFunc = """
                SELECT ID_FUNCIONARIO, NM_FUNCIONARIO, MAIL_FUNCIONARIO, LOGIN_ID_LOGIN
                FROM T_TAJ_FUNCIONARIO
                WHERE ID_FUNCIONARIO = ?
                """;

            try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlFunc)) {

            stmt.setInt(1, id);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        Funcionario funcionario = mapearFuncionario(rs);
                        return funcionario;
                    } else {
                        throw new EntidadeNaoLocalizada("Não foi possível encontrar o funcionário.");
                    }
                }
            } catch (SQLException e) {
                throw new EntidadeNaoLocalizada("Erro ao buscar funcionário por id", e);
            }
        }

    @Override
    public Funcionario buscarPorLoginId(int id_login) throws EntidadeNaoLocalizada {
        final String sql = """
        SELECT ID_FUNCIONARIO,
               NM_FUNCIONARIO,
               MAIL_FUNCIONARIO,
               LOGIN_ID_LOGIN
          FROM T_TAJ_FUNCIONARIO
         WHERE LOGIN_ID_LOGIN = ?
        """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id_login);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Funcionario funcionario = mapearFuncionario(rs);
                    return funcionario;
                } else {
                    throw new EntidadeNaoLocalizada("Não foi possível encontrar o funcionário.");
                }
            }

        } catch (SQLException e) {
            throw new EntidadeNaoLocalizada("Erro ao buscar funcionário pelo ID do login.", e);
        }
    }


    @Override
        public void editarFuncionario(Funcionario funcionario) {
            String sql = """
                 UPDATE T_TAJ_FUNCIONARIO
                 SET NM_FUNCIONARIO = ?, MAIL_FUNCIONARIO = ?
                 WHERE ID_FUNCIONARIO = ?
                """;

            try (Connection conn = databaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, funcionario.getNm_funcionario());
                stmt.setString(2, funcionario.getMail_funcionario());
                stmt.setInt(3, funcionario.getId_funcionario());


                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new InfraestruturaException("Erro ao editar funcionario pois nenhuma linha foi afetada");
                }

            } catch (SQLException e) {
                throw new InfraestruturaException("Erro ao editar funcionario", e);
            }
        }

    @Override
    public void excluirFuncionario(int id) {
        final String sql = """
        DELETE FROM T_TAJ_FUNCIONARIO
         WHERE ID_FUNCIONARIO = ?
        """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int affected = stmt.executeUpdate();

            if (affected == 0) {
                throw new InfraestruturaException("Nenhum funcionário encontrado com o ID informado.");
            }

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao excluir funcionário", e);
        }
    }

    private Funcionario mapearFuncionario(ResultSet rs) throws SQLException {
        int idFunc = rs.getInt("ID_FUNCIONARIO");
        String nome = rs.getString("NM_FUNCIONARIO");
        String email = rs.getString("MAIL_FUNCIONARIO");

        rs.close();

        return new Funcionario(idFunc, nome, email);
    }



}
