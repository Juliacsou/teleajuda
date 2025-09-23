package br.com.fiap.teleajuda.infrastructure.persistence;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Ticket;
import br.com.fiap.teleajuda.domain.model.pessoa.Funcionario;
import br.com.fiap.teleajuda.domain.model.pessoa.Paciente;
import br.com.fiap.teleajuda.domain.repository.TicketRepository;
import br.com.fiap.teleajuda.infrastructure.exceptions.InfraestruturaException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTicketReposirory implements TicketRepository {

        private DatabaseConnection databaseConnection;

        public void JdbcClienteRepository(DatabaseConnection databaseConnection) {
            this.databaseConnection = databaseConnection;
        }

        @Override
        public Ticket criar(Ticket ticket) {
            String sql = """
                INSERT INTO TICKET (ID, TEMA, PROBLEMA, SOLUCIONADO, DATA)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ticket.getCodigo());
            stmt.setString(2, ticket.getTema());
            stmt.setString(3, ticket.getProblema());
            stmt.setBoolean(4, ticket.isSolucionado());
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            stmt.setTimestamp(5, currentTimestamp);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new InfraestruturaException("Erro ao salvar, nenhuma linha da banco foi afetada");
            }

            return ticket;

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao criar ticket", e);
        }
        }

        @Override
        public Ticket buscarPorId(int id) throws EntidadeNaoLocalizada {
            String sql = """
                SELECT ID, TEMA, PROBLEMA, RESPOSTA, SOLUCIONADO, DATA FROM TICKET WHERE ID = ?
                """;

        try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                int idFromBd = resultSet.getInt("ID");
                String tema = resultSet.getString("TEMA");
                String problema = resultSet.getString("PROBLEMA");
                String resposta = resultSet.getString("RESPOSTA");
                Boolean solucionado = resultSet.getBoolean("SOLUCIONADO");
                String data = resultSet.getString("DATA");

                resultSet.close();

                return new Ticket(idFromBd, tema, problema, resposta, solucionado, null, null, data);
            }

        } catch (SQLException e) {
            throw new EntidadeNaoLocalizada("Erro ao buscar ticket por id", e);
        }
        throw new EntidadeNaoLocalizada("Ticket nao encontrado");
        }

        @Override
        public void editarProblema(String problema, int id) {
            String sql = """
                UPDATE TICKET SET PROBLEMA = ?
                WHERE ID = ?
                """;

            try (Connection conn = databaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, problema);
                stmt.setInt(2, id);

                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new InfraestruturaException("Erro ao desativar cliente, nenhuma linha foi afetada");
                }

            } catch (SQLException e) {
                throw new InfraestruturaException("Erro ao desativar cliente", e);
            }
        }

        @Override
        public void responder(int idFuncionario, String resposta, int idTicket) {
            String sql = """
                UPDATE TICKET SET RESPOSTA = ?, FK_FUNCIONARIO_TICKET = ?
                WHERE ID = ?
                """;

            try (Connection conn = databaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, resposta);
                stmt.setInt(2, idFuncionario);
                stmt.setInt(2, idTicket);

                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new InfraestruturaException("Erro ao responder ticket");
                }

            } catch (SQLException e) {
                throw new InfraestruturaException("Erro ao responder ticket", e);
            }
        }

        @Override
        public void fecharTicket(int id) {
            String sql = """
                UPDATE TICKET SET SOLUCIONADO = TRUE
                WHERE ID = ?
                """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new InfraestruturaException("Erro ao fechar ticket");
            }

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao fechar ticket", e);
        }
        }

        @Override
        public void abrirTicket(int id) {
            String sql = """
                UPDATE TICKET SET SOLUCIONADO = FALSE
                WHERE ID = ?
                """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new InfraestruturaException("Erro ao abrir ticket");
            }

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao abrir ticket", e);
        }
        }

        @Override
        public List<Ticket> exibirTodosTickets() {
            String sql = """
                SELECT ID, TEMA, PROBLEMA, RESPOSTA, SOLUCIONADO, DATA
                FROM TICKET ORDER BY ID
                """;

            try (Connection conn = this.databaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                List<Ticket> tickets = new ArrayList<>();

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String tema = rs.getString("TEMA");
                    String problema = rs.getString("PROBLEMA");
                    String resposta = rs.getString("RESPOSTA");
                    Boolean solucionado = rs.getBoolean("SOLUCIONADO");
                    String data = rs.getString("DATA");

                    Ticket ticket = new Ticket(id, tema, problema, resposta, solucionado, null, null, data);
                    tickets.add(ticket);
                }

                return tickets;
            } catch (SQLException e) {
                throw new InfraestruturaException("Erro ao buscar todos os tickets", e);
            }
        }

        @Override
        public List<Ticket> exibitTicketsPaciente(Paciente paciente) {
            String sql = """
                SELECT ID, TEMA, PROBLEMA, RESPOSTA, SOLUCIONADO, DATA
                FROM TICKET WHERE FK_PACIENTE = ?
                """;

            try (Connection conn = this.databaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                List<Ticket> tickets = new ArrayList<>();

                stmt.setInt(1, paciente.getRghc());

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String tema = rs.getString("TEMA");
                    String problema = rs.getString("PROBLEMA");
                    String resposta = rs.getString("RESPOSTA");
                    Boolean solucionado = rs.getBoolean("SOLUCIONADO");
                    String data = rs.getString("DATA");

                    Ticket ticket = new Ticket(id, tema, problema, resposta, solucionado, paciente, null, data);
                    tickets.add(ticket);
                }

                return tickets;
            } catch (SQLException e) {
                throw new InfraestruturaException("Erro ao buscar todos os tickets", e);
            }
        }

        @Override
        public List<Ticket> exibitTicketsFuncionario(Funcionario funcionario) {
            String sql = """
                SELECT ID, TEMA, PROBLEMA, RESPOSTA, SOLUCIONADO, DATA
                FROM TICKET WHERE FK_FUNCIONARIO = ?
                """;

            try (Connection conn = this.databaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                List<Ticket> tickets = new ArrayList<>();

                stmt.setInt(1, funcionario.getCodigo());

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String tema = rs.getString("TEMA");
                    String problema = rs.getString("PROBLEMA");
                    String resposta = rs.getString("RESPOSTA");
                    Boolean solucionado = rs.getBoolean("SOLUCIONADO");
                    String data = rs.getString("DATA");

                    Ticket ticket = new Ticket(id, tema, problema, resposta, solucionado, null, funcionario, data);
                    tickets.add(ticket);
                }

                return tickets;
            } catch (SQLException e) {
                throw new InfraestruturaException("Erro ao buscar todos os tickets", e);
            }
        }

    
}
