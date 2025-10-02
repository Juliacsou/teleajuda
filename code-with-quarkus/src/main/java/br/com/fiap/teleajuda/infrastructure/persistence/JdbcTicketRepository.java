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

public class JdbcTicketRepository implements TicketRepository {

    private final DatabaseConnection databaseConnection;

    public JdbcTicketRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }


    @Override
        public Ticket criar(Ticket ticket) {
            String sql = """
                INSERT INTO T_TAJ_TICKET (ID_TICKET, ASSUNTO, DESCRICAO, STATUS, DT_ABERTURA, CPF_PACIENTE, ID_FUNCIONARIO) VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ticket.getCodigo());
            stmt.setString(2, ticket.getAssunto());
            stmt.setString(3, ticket.getDescricao());
            stmt.setString(4, "A");
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            stmt.setTimestamp(5, currentTimestamp);
            stmt.setString(6, ticket.getPaciente().getCpf());
            stmt.setNull(7, java.sql.Types.INTEGER);

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
                SELECT ID_TICKET, ASSUNTO, DESCRICAO, RESPOSTA, STATUS, DATA FROM TICKET WHERE ID_TICKET = ?
                """;

        try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                int idFromBd = resultSet.getInt("ID_TICKET");
                String assunto = resultSet.getString("ASSUNTO");
                String descricao = resultSet.getString("DESCRICAO");
                String resposta = resultSet.getString("RESPOSTA");
                boolean status = resultSet.getBoolean("STATUS");
                String data = resultSet.getString("DT_ABERTURA");
                resultSet.close();


                return new Ticket(idFromBd, assunto, descricao, status, null, data);
            }

        } catch (SQLException e) {
            throw new EntidadeNaoLocalizada("Erro ao buscar ticket por id", e);
        }
        throw new EntidadeNaoLocalizada("Ticket nao encontrado");
        }

        @Override
        public void editarDescricao(String descricao, int id) {
            String sql = """
                UPDATE TICKET SET DESCRICAO = ?
                WHERE ID_TICKET = ?
                """;

            try (Connection conn = databaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, descricao);
                stmt.setInt(2, id);

                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new InfraestruturaException("Erro ao editar descrição pois nenhuma linha foi afetada");
                }

            } catch (SQLException e) {
                throw new InfraestruturaException("Erro ao editar descrição", e);
            }
        }

        @Override
        public void responder(int idFuncionario, String resposta, int idTicket) {
            String sql = """
                UPDATE TICKET SET RESPOSTA = ?, ID_FUNCIONARIO = ?
                WHERE ID_TICKET = ?
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
                UPDATE TICKET SET STATUS = FALSE
                WHERE ID_TICKET = ?
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
                UPDATE TICKET SET STATUS = TRUE
                WHERE ID_TICKET = ?
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
                SELECT ID_TICKET, ASSUNTO, DESCRICAO, RESPOSTA, STATUS, DT_ABERTURA
                FROM TICKET ORDER BY ID_TICKET
                """;

            try (Connection conn = this.databaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                List<Ticket> tickets = new ArrayList<>();

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String assunto = rs.getString("ASSUNTO");
                    String descricao = rs.getString("DESCRICAO");
                    String resposta = rs.getString("RESPOSTA");
                    boolean status = rs.getBoolean("STATUS");
                    String data = rs.getString("DT_ABERTURA");

                    Ticket ticket = new Ticket(id, assunto, descricao, resposta, status, null, null, data);
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
                SELECT ID_TICKET, ASSUNTO, DESCRICAO, RESPOSTA, STATUS, DT_ABERTURA
                FROM TICKET WHERE CPF_PACIENTE = ?
                """;

            try (Connection conn = this.databaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                List<Ticket> tickets = new ArrayList<>();

                stmt.setString(1, paciente.getCpf());

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID_TICKET");
                    String assunto = rs.getString("ASSUNTO");
                    String descricao = rs.getString("DESCRICAO");
                    String resposta = rs.getString("RESPOSTA");
                    Boolean status = rs.getBoolean("STATUS");
                    String data = rs.getString("DT_ABERTURA");

                    Ticket ticket = new Ticket(id, assunto, descricao, resposta, status, paciente, null, data);
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
                SELECT ID_TICKET, ASSUNTO, DESCRICAO, RESPOSTA, STATUS, DT_ABERTURA
                FROM TICKET WHERE ID_FUNCIONARIO = ?
                """;

            try (Connection conn = this.databaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                List<Ticket> tickets = new ArrayList<>();

                stmt.setInt(1, funcionario.getCodigo());

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID_TICKET");
                    String assunto = rs.getString("ASSUNTO");
                    String descricao = rs.getString("DESCRICAO");
                    String resposta = rs.getString("RESPOSTA");
                    Boolean status = rs.getBoolean("STATUS");
                    String data = rs.getString("DT_ABERTURA");

                    Ticket ticket = new Ticket(id, assunto, descricao, resposta, status, null, funcionario, data);
                    tickets.add(ticket);
                }

                return tickets;
            } catch (SQLException e) {
                throw new InfraestruturaException("Erro ao buscar todos os tickets", e);
            }
        }

    
}
