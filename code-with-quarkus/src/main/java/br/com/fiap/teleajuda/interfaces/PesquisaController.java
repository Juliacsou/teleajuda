package br.com.fiap.teleajuda.interfaces;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Paciente;
import br.com.fiap.teleajuda.domain.model.PesquisaSatisfacao;
import br.com.fiap.teleajuda.interfaces.dto.output.PesquisaOutputDto;

import java.util.List;

public interface PesquisaController {
    PesquisaSatisfacao criar(PesquisaSatisfacao pesquisa);

    List<PesquisaSatisfacao> exibirPesquisasPaciente (Paciente paciente);
    PesquisaOutputDto buscarPorId(int id) throws EntidadeNaoLocalizada;
    List<PesquisaSatisfacao> exibirTodasPesquisas ();

    void editar(PesquisaSatisfacao pesquisa);

    void excluirPesquisa(int id);
}
