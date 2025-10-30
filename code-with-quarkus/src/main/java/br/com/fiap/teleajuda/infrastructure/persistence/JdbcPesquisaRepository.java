package br.com.fiap.teleajuda.infrastructure.persistence;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.PesquisaSatisfacao;
import br.com.fiap.teleajuda.domain.model.Paciente;
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
             PreparedStatement stmt = conn.prepareStatement(sql, new String[] { "ID_PESQUISA_SATIS" })) {

            stmt.setInt(1, pesquisa.getNt_app());
            stmt.setInt(2, pesquisa.getNt_site());
            stmt.setInt(3, pesquisa.getNt_suporte());
            stmt.setString(4, pesquisa.getPaciente().getCpf_paciente());

            int affected = stmt.executeUpdate();
            if (affected == 0) {
                throw new InfraestruturaException("Erro ao salvar pesquisa: nenhuma linha afetada.");
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    pesquisa.setId_pesquisa_satis(rs.getInt(1));
                }
            }

            return pesquisa;

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new InfraestruturaException("Erro ao salvar pesquisa " + e.getMessage(), e);
        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao salvar pesquisa: " + e.getMessage(), e);
        }
        }

    @Override
    public PesquisaSatisfacao buscarPorId(int id) throws EntidadeNaoLocalizada {
        final String sql = """
            SELECT p.ID_PESQUISA_SATIS, p.NT_APP, p.NT_SITE, p.NT_SUPORTE, p.DT_PESQUISA, p.PACIENTE_CPF_PACIENTE,
                pa.CPF_PACIENTE, pa.NM_PACIENTE, pa.TEL_PACIENTE, pa.MAIL_PACIENTE, pa.RGHC, pa.DT_NASC_PACIENTE
            FROM T_TAJ_PESQUISA_SATIS p
            JOIN T_TAJ_PACIENTE pa ON pa.CPF_PACIENTE = p.PACIENTE_CPF_PACIENTE
            WHERE p.ID_PESQUISA_SATIS = ?
        """;

        try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearPesquisa(rs);
            }

        } catch (SQLException e) {
            throw new EntidadeNaoLocalizada("Erro ao buscar pesquisa por paciente", e);
        }
        throw new EntidadeNaoLocalizada("Cliente nao encontrado");
    }

    @Override
    public List<PesquisaSatisfacao> exibirPesquisasPaciente(Paciente paciente) {
        final String sql = """
            SELECT ID_PESQUISA_SATIS, NT_APP, NT_SITE, NT_SUPORTE, DT_PESQUISA
            FROM T_TAJ_PESQUISA_SATIS
            WHERE PACIENTE_CPF_PACIENTE = ?
            ORDER BY ID_PESQUISA_SATIS DESC
        """;

        List<PesquisaSatisfacao> pesquisas = new ArrayList<>();

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paciente.getCpf_paciente());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("ID_PESQUISA_SATIS");
                    int ntApp = rs.getInt("NT_APP");
                    int ntSite = rs.getInt("NT_SITE");
                    int ntSuporte = rs.getInt("NT_SUPORTE");
                    String dtPesq = rs.getString("DT_PESQUISA");

                    PesquisaSatisfacao pesquisa = new PesquisaSatisfacao(id, ntApp, ntSite, ntSuporte, dtPesq, paciente);
                    pesquisas.add(pesquisa);
                }
            }

            return pesquisas;

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao buscar pesquisas do paciente: " + e.getMessage(), e);
        }
    }



    @Override
    public List<PesquisaSatisfacao> exibirTodasPesquisas() {
        final String sql = """
            SELECT p.ID_PESQUISA_SATIS, p.NT_APP, p.NT_SITE, p.NT_SUPORTE, p.DT_PESQUISA, p.PACIENTE_CPF_PACIENTE,
                pa.CPF_PACIENTE, pa.NM_PACIENTE, pa.TEL_PACIENTE, pa.MAIL_PACIENTE, pa.RGHC, pa.DT_NASC_PACIENTE
            FROM T_TAJ_PESQUISA_SATIS p
            JOIN T_TAJ_PACIENTE pa ON pa.CPF_PACIENTE = p.PACIENTE_CPF_PACIENTE
            ORDER BY p.ID_PESQUISA_SATIS DESC
        """;

        List<PesquisaSatisfacao> pesquisas = new ArrayList<>();

        try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PesquisaSatisfacao pesquisa = mapearPesquisa(rs);
                pesquisas.add(pesquisa);
            }

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao buscar todas as pesquisas", e);
        }

        return pesquisas;
    }


    @Override
    public void editar(PesquisaSatisfacao pesquisa) {
        final String sql = """
            UPDATE T_TAJ_PESQUISA_SATIS
            SET NT_APP = ?, NT_SITE = ?, NT_SUPORTE = ?
            WHERE ID_PESQUISA_SATIS = ?
        """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pesquisa.getNt_app());
            stmt.setInt(2, pesquisa.getNt_site());
            stmt.setInt(3, pesquisa.getNt_suporte());
            stmt.setInt(4, pesquisa.getId_pesquisa_satis());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new InfraestruturaException("Pesquisa não encontrada para edição");
            }

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao editar pesquisa", e);
        }
    }

    @Override
    public void excluirPesquisa(int id) {
        final String sql = """
            DELETE FROM T_TAJ_PESQUISA_SATIS
            WHERE ID_PESQUISA_SATIS = ?
        """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new InfraestruturaException("Nenhuma pesquisa encontrada com o ID informado: " + id);
            }


        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao excluir pesquisa: " + e.getMessage(), e);
        }
    }


    private PesquisaSatisfacao mapearPesquisa(ResultSet rs) throws SQLException {
        int idPesquisa  = rs.getInt("ID_PESQUISA_SATIS");
        int ntApp = rs.getInt("NT_APP");
        int ntSite = rs.getInt("NT_SITE");
        int ntSuporte = rs.getInt("NT_SUPORTE");
        String dtPesquisa = rs.getString("DT_PESQUISA");

        String cpf = rs.getString("CPF_PACIENTE");
        String nome = rs.getString("NM_PACIENTE");
        String telefone = rs.getString("TEL_PACIENTE");
        String email = rs.getString("MAIL_PACIENTE");
        String rghc = rs.getString("RGHC");
        String dtNasc = rs.getString("DT_NASC_PACIENTE");

        Paciente paciente = new Paciente(cpf, nome, telefone, email, rghc, dtNasc);

        return new PesquisaSatisfacao(idPesquisa, ntApp, ntSite, ntSuporte, dtPesquisa, paciente);
    }





}
