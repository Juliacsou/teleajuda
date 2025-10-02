package br.com.fiap.teleajuda.infrastructure.persistence;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.pessoa.Paciente;
import br.com.fiap.teleajuda.domain.repository.PacienteRepository;
import br.com.fiap.teleajuda.infrastructure.exceptions.InfraestruturaException;
import br.com.fiap.teleajuda.infrastructure.persistence.DatabaseConnection;

import java.sql.*;

public class JdbcPacienteRepository implements PacienteRepository {

    private final DatabaseConnection databaseConnection;

    public JdbcPacienteRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }


    @Override
        public Paciente criar(Paciente paciente) {
            String sql = """
                INSERT INTO T_TAJ_PACIENTE (CPF_PACIENTE, NM_PACIENTE, TEL_PACIENTE, MAIL_PACIENTE, RGHC, DT_NASC_PACIENTE, T_TAJ_LOGIN_ID_LOGIN)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

            try (Connection conn = this.databaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                conn.setAutoCommit(false);

                stmt.setString(1, paciente.getCpf());
                stmt.setString(2, paciente.getNome());
                stmt.setString(3, paciente.getTelefone());
                stmt.setString(4, paciente.getEmail());
                stmt.setString(5, paciente.getRghc());
                stmt.setDate(6, java.sql.Date.valueOf(paciente.getData_nasc()));
                stmt.setInt(7, paciente.getUser().getId());

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
                SELECT CPF_PACIENTE, NM_PACIENTE, TEL_PACIENTE, MAIL_PACIENTE, RGHC, DT_NASC_PACIENTE FROM T_TAJ_PACIENTE WHERE CPF_PACIENTE = ?
                """;

            try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlFunc)) {

            stmt.setString(1, cpf);
            ResultSet resultSet = stmt.executeQuery();

                if (resultSet.next()) {
                    String cpfFromBd = resultSet.getString("CPF_PACIENTE");
                    String nome = resultSet.getString("NM_PACIENTE");
                    String tel = resultSet.getString("TEL_PACIENTE");
                    String email = resultSet.getString("MAIL_PACIENTE");
                    String rghc = resultSet.getString("RGHC");
                    String dt_nasc = resultSet.getString("DT_NASC_PACIENTE");

                    resultSet.close();

                    return new Paciente(nome, email, cpfFromBd, tel, dt_nasc, rghc);
                }
            } catch (SQLException e) {
                throw new EntidadeNaoLocalizada("Erro ao buscar paciente pelo CPF", e);
            }

            throw new EntidadeNaoLocalizada("Paciente nao encontrado");
        }

        @Override
        public void editar(Paciente paciente) {
            String sql = """
                    UPDATE T_TAJ_PACIENTE SET NM_PACIENTE = ?, TEL_PACIENTE = ?, MAIL_PACIENTE = ?, RGHC = ?, DT_NASC_PACIENTE = ? WHERE CPF_PACIENTE = ?
                """;

            try (Connection conn = databaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, paciente.getNome());
                stmt.setString(2, paciente.getTelefone());
                stmt.setString(3, paciente.getEmail());
                stmt.setString(4, paciente.getRghc());
                stmt.setDate(5, java.sql.Date.valueOf(paciente.getData_nasc()));
                stmt.setString(6, paciente.getCpf());

                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new InfraestruturaException("Erro ao editar paciente pois nenhuma linha foi afetada");
                }

            } catch (SQLException e) {
                throw new InfraestruturaException("Erro ao editar paciente", e);
            }
        }


    
}
