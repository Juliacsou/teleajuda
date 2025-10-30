package br.com.fiap.teleajuda.interfaces.mappers;

import br.com.fiap.teleajuda.domain.model.PesquisaSatisfacao;
import br.com.fiap.teleajuda.interfaces.dto.output.PesquisaOutputDto;

public class PesquisaMapper {
    private PesquisaMapper(){}

    public static PesquisaOutputDto toDto (PesquisaSatisfacao pesquisa){
        PesquisaOutputDto pesquisaOutputDto = new PesquisaOutputDto();
        pesquisaOutputDto.setId_pesquisa_satis(pesquisa.getId_pesquisa_satis());
        pesquisaOutputDto.setNt_app(pesquisa.getNt_app());
        pesquisaOutputDto.setNt_site(pesquisa.getNt_site());
        pesquisaOutputDto.setNt_suporte(pesquisa.getNt_suporte());
        pesquisaOutputDto.setDt_pesquisa(pesquisa.getDt_pesquisa());
        pesquisaOutputDto.setPaciente(pesquisa.getPaciente());

        return pesquisaOutputDto;
    }
}
