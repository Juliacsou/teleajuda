package br.com.fiap.teleajuda.infrastructure.persistence;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Paciente;
import br.com.fiap.teleajuda.domain.repository.PacienteRepository;
import br.com.fiap.teleajuda.infrastructure.exceptions.InfraestruturaException;

import java.sql.*;

public class JdbcPacienteRepository implements PacienteRepository {

    private final DatabaseConnection databaseConnection;

    public JdbcPacienteRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }


    @Override
    public Paciente criar(Paciente paciente) {
        final String sql = """
            INSERT INTO T_TAJ_PACIENTE (cpf_paciente, nm_paciente, tel_paciente, mail_paciente, rghc, dt_nasc_paciente, senha_paciente)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paciente.getCpf_paciente());
            stmt.setString(2, paciente.getNm_paciente());
            stmt.setString(3, paciente.getTel_paciente());
            stmt.setString(4, paciente.getMail_paciente());
            stmt.setString(5, paciente.getRghc());
            stmt.setString(6, paciente.getDt_nasc_paciente());
            stmt.setString(7, paciente.getSenha_paciente());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new InfraestruturaException("Falha ao criar paciente pois nenhuma linha alterada.");
            }

            return paciente;

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao salvar paciente: " + e.getMessage(), e);
        }
    }

    @Override
    public Paciente buscarPorCpf(String cpf) throws EntidadeNaoLocalizada {
        final String sql = """
            SELECT cpf_paciente, nm_paciente, tel_paciente, mail_paciente, rghc, dt_nasc_paciente
            FROM T_TAJ_PACIENTE
            WHERE cpf_paciente = ?
        """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    String cpfBd = rs.getString("cpf_paciente");
                    String nome = rs.getString("nm_paciente");
                    String tel = rs.getString("tel_paciente");
                    String mail = rs.getString("mail_paciente");
                    String rghc = rs.getString("rghc");
                    String dtnasc = rs.getString("dt_nasc_paciente");

                    Paciente paciente = new Paciente(cpfBd, nome, tel, mail, rghc, dtnasc);

                    return paciente;
                } else {
                    throw new EntidadeNaoLocalizada("Paciente não encontrado para o CPF: " + cpf);
                }
            }

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao buscar paciente por CPF: " + e.getMessage(), e);
        }
    }

    @Override
    public Paciente validarPaciente(String email, String senha) throws EntidadeNaoLocalizada {
        final String sql = """
            SELECT cpf_paciente, nm_paciente, tel_paciente, mail_paciente, rghc, dt_nasc_paciente, senha_paciente
            FROM T_TAJ_PACIENTE
            WHERE mail_paciente = ? AND senha_paciente = ?
        """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String cpfBd = rs.getString("cpf_paciente");
                    String nome = rs.getString("nm_paciente");
                    String tel = rs.getString("tel_paciente");
                    String mail = rs.getString("mail_paciente");
                    String rghc = rs.getString("rghc");
                    String dtnasc = rs.getString("dt_nasc_paciente");
                    String senhaBd = rs.getString("senha_paciente");

                    Paciente paciente = new Paciente(cpfBd, nome, tel, mail, rghc, dtnasc, senhaBd);
                } else {
                    throw new EntidadeNaoLocalizada("Paciente não encontrado ou credenciais inválidas.");
                }
            }

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao validar paciente: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void editar(Paciente paciente) {
        final String sql = """
            UPDATE T_TAJ_PACIENTE
            SET nm_paciente = ?, tel_paciente = ?, mail_paciente = ?,rghc = ?, dt_nasc_paciente = ?, senha_paciente = ?
            WHERE cpf_paciente = ?
        """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paciente.getNm_paciente());
            stmt.setString(2, paciente.getTel_paciente());
            stmt.setString(3, paciente.getMail_paciente());
            stmt.setString(4, paciente.getRghc());
            stmt.setString(5, paciente.getDt_nasc_paciente());
            stmt.setString(6, paciente.getSenha_paciente());
            stmt.setString(7, paciente.getCpf_paciente());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new InfraestruturaException("Erro ao editar paciente");
            }

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao editar paciente: " + e.getMessage(), e);
        }
    }

    @Override
    public void excluirPaciente(String cpf) {
        final String sql = """
            DELETE FROM T_TAJ_PACIENTE
            WHERE cpf_paciente = ?
        """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new InfraestruturaException("Erro ao excluir paciente");
            }

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao excluir paciente: " + e.getMessage(), e);
        }
    }
}
