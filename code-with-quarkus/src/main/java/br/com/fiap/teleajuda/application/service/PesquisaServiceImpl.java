package br.com.fiap.teleajuda.application.service;

import br.com.fiap.teleajuda.application.exceptions.PesquisaUnsupportedOperation;
import br.com.fiap.teleajuda.domain.exceptions.EntidadeNaoLocalizada;
import br.com.fiap.teleajuda.domain.model.PesquisaSatisfacao;
import br.com.fiap.teleajuda.domain.model.Paciente;
import br.com.fiap.teleajuda.domain.repository.PesquisaRepository;
import br.com.fiap.teleajuda.domain.service.PesquisaService;

import java.util.List;

public class PesquisaServiceImpl implements PesquisaService {

    private PesquisaRepository pesquisaRepository;

    public PesquisaServiceImpl(PesquisaRepository pesquisaRepository) {
        this.pesquisaRepository = pesquisaRepository;
    }

    @Override
    public PesquisaSatisfacao criar(PesquisaSatisfacao pesquisa) {
        try{
            return pesquisaRepository.criar(pesquisa);
        } catch (Exception e) {
            throw new PesquisaUnsupportedOperation("Erro ao criar pesquisa de satisfação");
        }
    }

    @Override
    public List<PesquisaSatisfacao> exibirPesquisasPaciente(Paciente paciente) {
        return pesquisaRepository.exibirPesquisasPaciente(paciente);
    }

    @Override
    public PesquisaSatisfacao buscarPorId(int id) throws EntidadeNaoLocalizada {
        return pesquisaRepository.buscarPorId(id);
    }

    @Override
    public List<PesquisaSatisfacao> exibirTodasPesquisas() {
        return pesquisaRepository.exibirTodasPesquisas();
    }

    @Override
    public void editar(PesquisaSatisfacao pesquisa) {
        try {
            PesquisaSatisfacao pesquisaExistente = pesquisaRepository.buscarPorId(pesquisa.getId_pesquisa_satis());
            pesquisaRepository.criar(pesquisa);
        } catch (EntidadeNaoLocalizada e) {
            throw new PesquisaUnsupportedOperation("Erro ao editar pesquisa de satisfação");
        }
    }

    @Override
    public void excluirPesquisa(int id) {
        try {
            PesquisaSatisfacao pesquisaExistente = pesquisaRepository.buscarPorId(id);
            pesquisaRepository.excluirPesquisa(id);
        } catch (EntidadeNaoLocalizada e) {
            throw new PesquisaUnsupportedOperation("Erro ao deletar pesquisa de satisfação");
        }
    }
}
