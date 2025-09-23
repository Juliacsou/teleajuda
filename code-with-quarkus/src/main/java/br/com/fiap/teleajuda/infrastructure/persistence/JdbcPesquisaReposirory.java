package br.com.fiap.teleajuda.infrastructure.persistence;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.PesquisaSatisfacao;
import br.com.fiap.teleajuda.domain.model.pessoa.Paciente;
import br.com.fiap.teleajuda.domain.repository.PesquisaRepository;
import br.com.fiap.teleajuda.infrastructure.exceptions.InfraestruturaException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcPesquisaReposirory implements PesquisaRepository {

        private DatabaseConnection databaseConnection;

        public void JdbcClienteRepository(DatabaseConnection databaseConnection) {
            this.databaseConnection = databaseConnection;
        }

        @Override
        public PesquisaSatisfacao criar(PesquisaSatisfacao pesquisa) {
            String sql = """
                INSERT INTO PESQUISA (NOTAAPP, NOTASITE, NOTASUPORTE, FK_PACIENTE)
                VALUES (?, ?, ?, ?)
                """;

            try (Connection conn = this.databaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, pesquisa.getNotaApp());
                stmt.setInt(2, pesquisa.getNotaSite());
                stmt.setInt(3, pesquisa.getNotaSuporte());
                stmt.setInt(4, pesquisa.getPaciente().getRghc());

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
        public List<PesquisaSatisfacao> exibirPesquisasPaciente(Paciente paciente) {
            String sql = """
                SELECT ID, NOTA_APP, NOTA_SITE, NOTA_SUPORTE
                FROM PESQUISA WHERE FK_PACIENTE = ?
                """;

            try (Connection conn = this.databaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                List<PesquisaSatisfacao> pesquisas = new ArrayList<>();

                stmt.setInt(1, paciente.getRghc());

                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    int codigo = resultSet.getInt("ID");
                    int notaApp = resultSet.getInt("NOTA_APP");
                    int notaSite = resultSet.getInt("NOTA_SITE");
                    int notaSuporte = resultSet.getInt("NOTA_SUPORTE");

                    resultSet.close();

                    PesquisaSatisfacao pesquisa = new PesquisaSatisfacao(codigo, notaApp, notaSite, notaSuporte, paciente);
                    pesquisas.add(pesquisa);
                }
                return pesquisas;

            } catch (SQLException e) {
                throw new InfraestruturaException("Erro ao buscar todos as pesquisas", e);
            }
        }

        @Override
        public PesquisaSatisfacao buscarPorId(int id) throws EntidadeNaoLocalizada {
            String sql = """
                SELECT ID, NOTA_APP, NOTA_SITE, NOTA_SUPORTE
                FROM PESQUISA WHERE ID = ?
                """;

            try (Connection conn = this.databaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id);

                ResultSet resultSet = stmt.executeQuery();
                if (resultSet.next()) {
                    int codigo = resultSet.getInt("ID");
                    int notaApp = resultSet.getInt("NOTA_APP");
                    int notaSite = resultSet.getInt("NOTA_SITE");
                    int notaSuporte = resultSet.getInt("NOTA_SUPORTE");

                    resultSet.close();

                    return new PesquisaSatisfacao(codigo, notaApp, notaSite, notaSuporte, null);
                }

            } catch (SQLException e) {
                throw new EntidadeNaoLocalizada("Erro ao buscar pesquisa por paciente", e);
            }
            throw new EntidadeNaoLocalizada("Cliente nao encontrado");
        }

        @Override
        public void editar(PesquisaSatisfacao pesquisa) {
           String sql = """
                UPDATE PESQUISA SET NOTA_APP = ?, NOTA_SITE = ?, NOTA_SUPORTE = ?
                WHERE ID = ?
                """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pesquisa.getNotaApp());
            stmt.setInt(2, pesquisa.getNotaSite());
            stmt.setInt(3, pesquisa.getNotaSuporte());
            stmt.setInt(4, pesquisa.getCodigo());
            

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new InfraestruturaException("Erro editar pesquisa pois nenhuma linha foi afetada");
            }

        } catch (SQLException e) {
            throw new InfraestruturaException("Erro ao editar pesquisa", e);
        };
        }

        @Override
        public List<PesquisaSatisfacao> exibitTodasPesquisas() {
             String sql = """
                SELECT ID, NOTA_APP, NOTA_SITE, NOTA_SUPORTE
                FROM PESQUISA
                """;

            try (Connection conn = this.databaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                List<PesquisaSatisfacao> pesquisas = new ArrayList<>();

                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    int codigo = resultSet.getInt("ID");
                    int notaApp = resultSet.getInt("NOTA_APP");
                    int notaSite = resultSet.getInt("NOTA_SITE");
                    int notaSuporte = resultSet.getInt("NOTA_SUPORTE");

                    resultSet.close();

                    PesquisaSatisfacao pesquisa = new PesquisaSatisfacao(codigo, notaApp, notaSite, notaSuporte, null);
                    pesquisas.add(pesquisa);
                }
                return pesquisas;

            } catch (SQLException e) {
                throw new InfraestruturaException("Erro ao buscar todos as pesquisas", e);
            }
        }

        

    
}
