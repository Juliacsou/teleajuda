package br.com.fiap.teleajuda.infrastructure.persistence;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.pessoa.Paciente;
import br.com.fiap.teleajuda.domain.repository.PacienteRepository;
import br.com.fiap.teleajuda.infrastructure.exceptions.InfraestruturaException;
import br.com.fiap.teleajuda.infrastructure.persistence.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcPacienteRepository implements PacienteRepository {

    private final DatabaseConnection databaseConnection;

    public JdbcPacienteRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }


    @Override
        public Paciente criar(Paciente paciente) {
            String sql = """
                INSERT INTO T_TAJ_PACIENTE (CPF_PACIENTE, NM_PACIENTE, TEL_PACIENTE, MAIL_PACIENTE, RGHC, DT_NASC_PACIENTE, LOGIN_ID_LOGIN)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

            try (Connection conn = this.databaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

                conn.setAutoCommit(false);

                stmt.setString(1, paciente.getCpf_paciente());
                stmt.setString(2, paciente.getNm_paciente());
                stmt.setString(3, paciente.getTel_paciente());
                stmt.setString(4, paciente.getMail_paciente());
                stmt.setString(5, paciente.getRghc());
                stmt.setString(6, paciente.getDt_nasc_paciente());
                stmt.setInt(7, paciente.getLogin().getId_login());

                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0){
                    throw new UnsupportedOperationException("Erro ao salvar pois nenhuma linha do banco foi alterada");
                }
                conn.commit();
                return paciente;

            }catch (SQLException e) {
            throw new UnsupportedOperationException("Erro ao salvar o paciente");
            }
        }

        @Override
        public Paciente buscarPorCpf(String cpf) throws EntidadeNaoLocalizada {
            String sqlFunc = """
                SELECT CPF_PACIENTE, NM_PACIENTE, TEL_PACIENTE, MAIL_PACIENTE, RGHC, DT_NASC_PACIENTE, LOGIN_ID_LOGIN
                FROM T_TAJ_PACIENTE
                WHERE CPF_PACIENTE = ?
                """;

            try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlFunc)) {

            stmt.setString(1, cpf);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return mapearPaciente(rs);
                    } else {
                        throw new EntidadeNaoLocalizada("Paciente não encontrado.");
                    }
                }
            } catch (SQLException e) {
                throw new EntidadeNaoLocalizada("Erro ao buscar paciente pelo CPF", e);
            }
        }

    @Override
    public List<Paciente> buscarPacientes() {
        String sql = """
            SELECT CPF_PACIENTE, NM_PACIENTE, TEL_PACIENTE, MAIL_PACIENTE, RGHC, DT_NASC_PACIENTE,
            FROM T_TAJ_PACIENTE
            ORDER BY NM_PACIENTE
        """;

        List<Paciente> pacientes = new ArrayList<>();

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Paciente paciente = mapearPaciente(rs);
                pacientes.add(paciente);
            }

            return pacientes;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pacientes.", e);
        }
    }


    @Override
    public Paciente buscarPorLoginId(int id_login) throws EntidadeNaoLocalizada {
        String sql = """
            SELECT CPF_PACIENTE, NM_PACIENTE, TEL_PACIENTE, MAIL_PACIENTE, RGHC, DT_NASC_PACIENTE, LOGIN_ID_LOGIN
            FROM T_TAJ_PACIENTE
            WHERE LOGIN_ID_LOGIN = ?
        """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id_login);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Paciente paciente = mapearPaciente(rs);
                    return paciente;
                } else {
                    throw new EntidadeNaoLocalizada("Não foi possível encontrar o paciente.");
                }
            }

        } catch (SQLException e) {
            throw new EntidadeNaoLocalizada("Erro ao buscar paciente pelo ID do login.", e);
        }
    }


    @Override
        public void editar(Paciente paciente) {
            String sql = """
                    UPDATE T_TAJ_PACIENTE
                    SET NM_PACIENTE = ?, TEL_PACIENTE = ?, MAIL_PACIENTE = ?, RGHC = ?, DT_NASC_PACIENTE = ?
                    WHERE CPF_PACIENTE = ?
                """;

            try (Connection conn = databaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, paciente.getNm_paciente());
                stmt.setString(2, paciente.getTel_paciente());
                stmt.setString(3, paciente.getMail_paciente());
                stmt.setString(4, paciente.getRghc());
                stmt.setString(5, paciente.getDt_nasc_paciente());
                stmt.setString(6, paciente.getCpf_paciente());

                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new InfraestruturaException("Erro ao editar paciente pois nenhuma linha foi afetada");
                }

            } catch (SQLException e) {
                throw new InfraestruturaException("Erro ao editar paciente", e);
            }
        }

    @Override
    public void excluirPaciente(Paciente paciente) {
        String sql = """
            DELETE FROM T_TAJ_PACIENTE
            WHERE CPF_PACIENTE = ?
        """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paciente.getCpf_paciente());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new InfraestruturaException("Nenhum paciente encontrado com o CPF informado.");
            }

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao excluir paciente", e);
        }
    }


    private Paciente mapearPaciente(ResultSet rs) throws SQLException {
        String cpf     = rs.getString("CPF_PACIENTE");
        String nome    = rs.getString("NM_PACIENTE");
        String tel     = rs.getString("TEL_PACIENTE");
        String email   = rs.getString("MAIL_PACIENTE");
        String rghc    = rs.getString("RGHC");
        String dt_nasc = rs.getString("DT_NASC_PACIENTE");

        return new Paciente(cpf, nome, tel, email, rghc, dt_nasc);
    }



}
