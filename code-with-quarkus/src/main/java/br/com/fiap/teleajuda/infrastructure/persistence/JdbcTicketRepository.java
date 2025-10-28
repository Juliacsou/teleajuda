package br.com.fiap.teleajuda.infrastructure.persistence;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Ticket;
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
            INSERT INTO T_TAJ_TICKET (ASSUNTO, DESCRICAO, RESPOSTA, DT_ABERTURA, DT_FECHAMENTO, STATUS, PACIENTE_CPF_PACIENTE)
            VALUES (?, ?, NULL, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), NULL, ?, ?)
    """;

        try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ticket.getAssunto());
            stmt.setString(2, ticket.getDescricao());
            stmt.setString(3, "A");
            stmt.setString(4, ticket.getPaciente().getCpf_paciente());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new InfraestruturaException("Erro ao criar ticket pois nenhuma linha foi afetada");
            }
            return ticket;
        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao criar ticket", e);
        }
    }


    @Override
        public Ticket buscarPorId(int id) throws EntidadeNaoLocalizada {
            final String sql = """
                SELECT t.ID_TICKET, t.ASSUNTO, t.DESCRICAO, t.RESPOSTA, t.DT_ABERTURA, t.DT_FECHAMENTO, t.STATUS, t.PACIENTE_CPF_PACIENTE,
                    p.CPF_PACIENTE AS CPF_PACIENTE, p.NM_PACIENTE AS NOME, p.MAIL_PACIENTE AS EMAIL
                FROM T_TAJ_TICKET t
                JOIN T_TAJ_PACIENTE p
                ON t.PACIENTE_CPF_PACIENTE = p.CPF_PACIENTE
                WHERE t.ID_TICKET = ?
            """;

            try (Connection conn = this.databaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return mapearTicket(rs);
                    }
                }
            } catch (SQLException e) {
                throw new EntidadeNaoLocalizada("Erro ao buscar ticket por id", e);
            }

            throw new EntidadeNaoLocalizada("Ticket nao encontrado");
        }


    @Override
    public List<Ticket> exibirTodosTickets() {
        String sql = """
            SELECT t.ID_TICKET, t.ASSUNTO, t.DESCRICAO, t.RESPOSTA, t.DT_ABERTURA, t.DT_FECHAMENTO, t.STATUS, t.PACIENTE_CPF_PACIENTE,
                    p.CPF_PACIENTE AS CPF_PACIENTE, p.NM_PACIENTE AS NOME, p.MAIL_PACIENTE AS EMAIL
            FROM T_TAJ_TICKET t
            JOIN T_TAJ_PACIENTE p
            ON t.PACIENTE_CPF_PACIENTE = p.CPF_PACIENTE
            """;
        List<Ticket> tickets = new ArrayList<>();

        try (Connection conn = this.databaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Ticket ticket = mapearTicket(rs);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao buscar todos os tickets", e);
    }
        return tickets;
    }

    @Override
    public List<Ticket> exibitTicketsPaciente(Paciente paciente) {
        String sql = """
                SELECT ID_TICKET, ASSUNTO, DESCRICAO, RESPOSTA, DT_ABERTURA, DT_FECHAMENTO, STATUS, PACIENTE_CPF_PACIENTE
                FROM T_TAJ_TICKET
                WHERE PACIENTE_CPF_PACIENTE = ?
                ORDER BY ID_TICKET
                """;

        List<Ticket> tickets = new ArrayList<>();

        try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paciente.getCpf_paciente());

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idTicket        = rs.getInt("ID_TICKET");
                String assunto      = rs.getString("ASSUNTO");
                String descricao    = rs.getString("DESCRICAO");
                String resposta     = rs.getString("RESPOSTA");
                String dtAbertura   = rs.getString("DT_ABERTURA");
                String dtFechamento = rs.getString("DT_FECHAMENTO");
                String status       = rs.getString("STATUS");
                String cpfTicket    = rs.getString("PACIENTE_CPF_PACIENTE");

                Ticket ticket = new Ticket(idTicket, assunto, descricao, resposta, dtAbertura, dtFechamento, status, paciente);
                tickets.add(ticket);
            }
            return tickets;
        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao buscar todos os tickets", e);
        }
    }

        @Override
        public void editarDescricaoTicket(String descricao, int id) {
            String sql = """
                UPDATE T_TAJ_TICKET
                SET DESCRICAO = ?
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
        public void responder(String resposta, int idTicket) {
            String sql = """
                UPDATE T_TAJ_TICKET
                SET RESPOSTA = ?
                WHERE ID_TICKET = ?
                """;

            try (Connection conn = databaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, resposta);
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
                UPDATE T_TAJ_TICKET
                SET STATUS = 'F', DT_FECHAMENTO = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS')
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
                UPDATE T_TAJ_TICKET
                SET STATUS = 'A'
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
    public void deletarTicket(int id) {
        String sql = """
            DELETE FROM T_TAJ_TICKET
            WHERE ID_TICKET = ?
            """;

        try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new InfraestruturaException("Nenhum ticket encontrado para exclusão");
            }

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao deletar ticket", e);
        }
    }


    private Ticket mapearTicket(ResultSet rs) throws SQLException {
        int    idTicket       = rs.getInt("ID_TICKET");
        String assunto        = rs.getString("ASSUNTO");
        String descricao      = rs.getString("DESCRICAO");
        String resposta       = rs.getString("RESPOSTA");
        String dtAbertura     = rs.getString("DT_ABERTURA");
        String dtFechamento   = rs.getString("DT_FECHAMENTO");
        String status         = rs.getString("STATUS");
        String cpfDoTicket    = rs.getString("PACIENTE_CPF_PACIENTE");

        String cpfPaciente    = rs.getString("CPF_PACIENTE");
        String nomePaciente   = rs.getString("NOME");
        String emailPaciente  = rs.getString("EMAIL");

        Paciente paciente = new Paciente(cpfPaciente, nomePaciente, emailPaciente);

        return new Ticket(idTicket, assunto, descricao, resposta, dtAbertura, dtFechamento, status, paciente);
    }




}
