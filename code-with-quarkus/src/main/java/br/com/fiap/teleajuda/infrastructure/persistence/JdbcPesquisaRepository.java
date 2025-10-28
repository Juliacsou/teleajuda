package br.com.fiap.teleajuda.infrastructure.persistence;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.PesquisaSatisfacao;
import br.com.fiap.teleajuda.domain.model.pessoa.Paciente;
import br.com.fiap.teleajuda.domain.repository.PesquisaRepository;
import br.com.fiap.teleajuda.infrastructure.exceptions.InfraestruturaException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcPesquisaRepository implements PesquisaRepository {

    private final DatabaseConnection databaseConnection;

    public JdbcPesquisaRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
        public PesquisaSatisfacao criar(PesquisaSatisfacao pesquisa) {
            String sql = """
                INSERT INTO T_TAJ_PESQUISA_SATIS (NT_APP, NT_SITE, NT_SUPORTE, DT_PESQUISA, PACIENTE_CPF_PACIENTE)
                VALUES (?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS', ?)
                """;

            try (Connection conn = this.databaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, pesquisa.getNt_app());
                stmt.setInt(2, pesquisa.getNt_site());
                stmt.setInt(3, pesquisa.getNt_suporte());
                stmt.setString(4, pesquisa.getPaciente().getCpf_paciente());

                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new InfraestruturaException("Erro ao salvar pesquisa pois nenhuma linha da banco foi afetada");
                }

                return pesquisa;

            } catch (SQLException e) {
                throw new InfraestruturaException("Erro ao salvar pesquisa", e);
            }
        }

    @Override
    public PesquisaSatisfacao buscarPorId(int id) throws EntidadeNaoLocalizada {
        String sql = """
               SELECT p.ID_PESQUISA_SATIS, p.NT_APP, p.NT_SITE, p.NT_SUPORTE, p.DT_PESQUISA, pa.CPF_PACIENTE, pa.NOME, pa.EMAIL
               FROM T_TAJ_PESQUISA_SATIS p
               JOIN T_TAJ_PACIENTE pa ON pa.CPF_PACIENTE = p.PACIENTE_CPF_PACIENTE
               WHERE p.ID_PESQUISA_SATIS = ?
        """;
        try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return mapearPesquisa(resultSet);
            }
        } catch (SQLException e) {
            throw new EntidadeNaoLocalizada("Erro ao buscar pesquisa por paciente", e);
        }
        throw new EntidadeNaoLocalizada("Cliente nao encontrado");
    }

    @Override
    public List<PesquisaSatisfacao> exibirPesquisasPaciente(Paciente paciente) {
        String sql = """
                SELECT p.ID_PESQUISA_SATIS, p.NT_APP, p.NT_SITE, p.NT_SUPORTE, p.DT_PESQUISA
                FROM T_TAJ_PESQUISA_SATIS p
                WHERE p.PACIENTE_CPF_PACIENTE = ?
        """;

        List<PesquisaSatisfacao> pesquisas = new ArrayList<>();

        try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paciente.getCpf_paciente());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID_PESQUISA_SATIS");
                int ntApp = rs.getInt("NT_APP");
                int ntSite = rs.getInt("NT_SITE");
                int ntSuporte = rs.getInt("NT_SUPORTE");
                String dtPesq = rs.getString("DT_PESQUISA");
                rs.close();
                PesquisaSatisfacao pesquisa = new PesquisaSatisfacao(id, ntApp, ntSite, ntSuporte, dtPesq, paciente);
                pesquisas.add(pesquisa);
            }
            return pesquisas;
        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao buscar todos as pesquisas", e);
        }
    }



    @Override
    public List<PesquisaSatisfacao> exibitTodasPesquisas() {
        String sql = """
               SELECT p.ID_PESQUISA_SATIS, p.NT_APP, p.NT_SITE, p.NT_SUPORTE, p.DT_PESQUISA, pa.CPF_PACIENTE, pa.NOME, pa.EMAIL
               FROM T_TAJ_PESQUISA_SATIS p
               JOIN T_TAJ_PACIENTE pa ON pa.CPF_PACIENTE = p.PACIENTE_CPF_PACIENTE
        """;

        List<PesquisaSatisfacao> pesquisas = new ArrayList<>();

        try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PesquisaSatisfacao pesquisa = mapearPesquisa(rs);
                pesquisas.add(pesquisa);
            }
            return pesquisas;
        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao buscar todas as pesquisas", e);
        }
    }


    @Override
    public void editar(PesquisaSatisfacao pesquisa) {
        String sql = """
               UPDATE T_TAJ_PESQUISA_SATIS SET NT_APP = ?, NT_SITE = ?, NT_SUPORTE = ?
               WHERE ID_PESQUISA_SATIS = ?
               """;
        try (Connection conn = databaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pesquisa.getNt_app());
            stmt.setInt(2, pesquisa.getNt_site());
            stmt.setInt(3, pesquisa.getNt_suporte());
            stmt.setInt(4, pesquisa.getId_pesquisa_satis());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new InfraestruturaException("Erro editar pesquisa pois nenhuma linha foi afetada");
            }
        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao editar pesquisa", e);
        }
    }

    @Override
    public void excluirPesquisa(int id) {
        String sql = """
        DELETE FROM T_TAJ_PESQUISA_SATIS
        WHERE ID_PESQUISA_SATIS = ?
        """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new InfraestruturaException("Erro ao excluir pesquisa: nenhuma linha foi afetada");
            }

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao excluir pesquisa", e);
        }
    }


    private PesquisaSatisfacao mapearPesquisa(ResultSet rs) throws SQLException {
        int id = rs.getInt("ID_PESQUISA_SATIS");
        int ntApp = rs.getInt("NT_APP");
        int ntSite = rs.getInt("NT_SITE");
        int ntSuporte = rs.getInt("NT_SUPORTE");
        String dtPesquisa = rs.getString("DT_PESQUISA");

        String cpfPaciente = rs.getString("CPF_PACIENTE");
        String nomePaciente = rs.getString("NOME");
        String emailPaciente = rs.getString("EMAIL");

        Paciente paciente = new Paciente(cpfPaciente, nomePaciente, emailPaciente);

        return new PesquisaSatisfacao(id, ntApp, ntSite, ntSuporte, dtPesquisa, paciente);
    }





}
