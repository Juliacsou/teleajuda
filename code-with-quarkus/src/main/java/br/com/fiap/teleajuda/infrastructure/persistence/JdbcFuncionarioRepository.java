package br.com.fiap.teleajuda.infrastructure.persistence;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Funcionario;
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
        final String sql = """
            INSERT INTO T_TAJ_FUNCIONARIO (cpf_funcionario, nm_funcionario, mail_funcionario, senha_funcionario)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getCpf_funcionario());
            stmt.setString(2, funcionario.getNm_funcionario());
            stmt.setString(3, funcionario.getMail_funcionario());
            stmt.setString(3, funcionario.getSenha());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new InfraestruturaException("Falha ao criar cliente, nenhuma linha afetada.");
            }

            return funcionario;

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao salvar cliente: " + e.getMessage(), e);
        }

    }

    @Override
    public Funcionario buscarPorCpf(String cpf) throws EntidadeNaoLocalizada {
        final String sql = """
            SELECT cpf_funcionario, nm_funcionario, mail_funcionario, senha_funcionario
            FROM T_TAJ_FUNCIONARIO
            WHERE cpf_funcionario = ?
        """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String cpfBd = rs.getString("cpf_funcionario");
                    String nome = rs.getString("nm_funcionario");
                    String email = rs.getString("mail_funcionario");
                    String senha = rs.getString("senha_funcionario");

                    Funcionario funcionario = new Funcionario(cpfBd, nome, email, senha);

                    return funcionario;

                } else {
                    throw new EntidadeNaoLocalizada("Funcionário não encontrado para o CPF: " + cpf);
                }
            }

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao buscar funcionário por CPF: " + e.getMessage(), e);
        }


    }

    @Override
    public Funcionario validarFuncionario(String email, String senha) {
        final String sql = """
            SELECT cpf_funcionario, nm_funcionario, mail_funcionario, senha_funcionario
            FROM T_TAJ_FUNCIONARIO
            WHERE mail_funcionario = ? AND senha_funcionario = ?
        """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String cpfBd = rs.getString("cpf_funcionario");
                    String nome = rs.getString("nm_funcionario");
                    String emailBd = rs.getString("mail_funcionario");
                    String senhaBd = rs.getString("senha_funcionario");

                    Funcionario funcionario = new Funcionario(cpfBd, nome, email, senha);

                    return funcionario;

                } else {
                    throw new EntidadeNaoLocalizada ("Email ou senha incorretos");
                }
            }

        } catch (SQLException | EntidadeNaoLocalizada e) {
            throw new InfraestruturaException("Email ou senha incorretos " + e.getMessage(), e);
        }
    }

    @Override
    public void editarFuncionario(Funcionario funcionario) {
        final String sql = """
            UPDATE T_TAJ_FUNCIONARIO
            SET nm_funcionario = ?, mail_funcionario = ?, senha_funcionario = ?
            WHERE cpf_funcionario = ?
        """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNm_funcionario());
            stmt.setString(2, funcionario.getMail_funcionario());
            stmt.setString(3, funcionario.getSenha());
            stmt.setString(4, funcionario.getCpf_funcionario());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new InfraestruturaException("Erro ao editar funcionario");
            }

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao editar funcionário: " + e.getMessage(), e);
        }
    }

    @Override
    public void excluirFuncionario(String cpf) {
        final String sql = """
        DELETE FROM T_TAJ_FUNCIONARIO
        WHERE cpf_funcionario = ?
        """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new InfraestruturaException("Erro ao excluir funcionario");
            }

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao excluir funcionário: " + e.getMessage(), e);
        }

    }

}
