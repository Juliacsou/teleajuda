package br.com.fiap.teleajuda.interfaces;

import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.Paciente;
import br.com.fiap.teleajuda.domain.model.PesquisaSatisfacao;
import br.com.fiap.teleajuda.domain.service.PesquisaService;
import br.com.fiap.teleajuda.interfaces.dto.output.PesquisaOutputDto;
import br.com.fiap.teleajuda.interfaces.mappers.PesquisaMapper;

import java.util.List;

public class PesquisaControllerImpl implements PesquisaController{

    private final PesquisaService pesquisaService;

    public PesquisaControllerImpl(PesquisaService pesquisaService) {
        this.pesquisaService = pesquisaService;
    }

    @Override
    public PesquisaSatisfacao criar(PesquisaSatisfacao pesquisa) {
        return pesquisaService.criar(pesquisa);
    }

    @Override
    public List<PesquisaSatisfacao> exibirPesquisasPaciente(String cpf) {
        return pesquisaService.exibirPesquisasPaciente(cpf);
    }

    @Override
    public PesquisaOutputDto buscarPorId(int id) throws EntidadeNaoLocalizada {
        PesquisaSatisfacao novaPesquisa = pesquisaService.buscarPorId(id);
        return PesquisaMapper.toDto(novaPesquisa);
    }

    @Override
    public List<PesquisaSatisfacao> exibirTodasPesquisas() {
        return pesquisaService.exibirTodasPesquisas();
    }

    @Override
    public void editar(PesquisaSatisfacao pesquisa) {
        pesquisaService.editar(pesquisa);
    }

    @Override
    public void excluirPesquisa(int id) {
        pesquisaService.excluirPesquisa(id);
    }
}
