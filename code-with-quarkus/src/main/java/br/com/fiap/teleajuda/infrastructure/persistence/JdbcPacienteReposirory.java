package br.com.fiap.teleajuda.infrastructure.persistence;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.pessoa.Paciente;
import br.com.fiap.teleajuda.domain.repository.PacienteRepository;
import br.com.fiap.teleajuda.infrastructure.exceptions.InfraestruturaException;

import java.sql.*;

public class JdbcPacienteReposirory implements PacienteRepository {

        private DatabaseConnection databaseConnection;

        public void JdbcClienteRepository(DatabaseConnection databaseConnection) {
            this.databaseConnection = databaseConnection;
        }

        @Override
        public Paciente criar(Paciente paciente) {
            String sql = """
                INSERT INTO PACIENTE (NOME, EMAIL, RGHC, TEL, DATA_NASC)
                VALUES (?, ?, ?, ?, ?)
                """;

            try (Connection conn = this.databaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setString(1, paciente.getNome());
                stmt.setString(2, paciente.getEmail());
                stmt.setInt(3, paciente.getRghc());
                stmt.setString(4, paciente.getTelefone());
                stmt.setString(5, paciente.getData_nasc());

                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0){
                    throw new UnsupportedOperationException("Erro ao salvar pois nenhuma linha do banco foi alterada");
                }

                return paciente;

            }catch (SQLException e) {
            throw new UnsupportedOperationException("Erro ao salvar o paciente");
            }
        }

        @Override
        public Paciente buscarPorRGHC(int rghc) throws EntidadeNaoLocalizada {
            String sqlFunc = """
                SELECT NOME, EMAIL, RGHC, TEL, DATA_NASC FROM PACIENTE WHERE RGHC = ?
                """;

            try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlFunc)) {

            stmt.setInt(1, rghc);
            ResultSet resultSet = stmt.executeQuery();

                if (resultSet.next()) {
                    String nome = resultSet.getString("NOME");
                    String email = resultSet.getString("EMAIL");
                    int rghcFromBd = resultSet.getInt("RGHC");
                    String tel = resultSet.getString("TEL");
                    String data_nasc = resultSet.getString("DATA_NASC");

                    resultSet.close();

                    return new Paciente(nome, email, null, rghcFromBd, tel, data_nasc);
                }
            } catch (SQLException e) {
                throw new EntidadeNaoLocalizada("Erro ao buscar paciente pelo RGHC", e);
            }

            throw new EntidadeNaoLocalizada("Paciente nao encontrado");
        }

        @Override
        public void editar(Paciente paciente) {
            String sql = """
                UPDATE PACIENTE SET NOME = ?, EMAIL = ?, TEL = ?, DATA_NASC = ?
                WHERE RGHC = ?
                """;

            try (Connection conn = databaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, paciente.getNome());
                stmt.setString(2, paciente.getEmail());
                stmt.setString(3, paciente.getTelefone());
                stmt.setString(4, paciente.getData_nasc());
                stmt.setInt(5, paciente.getRghc());

                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new InfraestruturaException("Erro ao editar paciente pois nenhuma linha foi afetada");
                }

            } catch (SQLException e) {
                throw new InfraestruturaException("Erro ao editar paciente", e);
            }
        }


    
}
