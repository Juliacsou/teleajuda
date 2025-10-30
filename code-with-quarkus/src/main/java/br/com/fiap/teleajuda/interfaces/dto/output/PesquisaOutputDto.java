package br.com.fiap.teleajuda.interfaces.dto.output;

import br.com.fiap.teleajuda.domain.model.Paciente;

public class PesquisaOutputDto {
    private int id_pesquisa_satis;
    private int nt_app;
    private int nt_site;
    private int nt_suporte;
    private String dt_pesquisa;
    private Paciente paciente;


    public int getId_pesquisa_satis() {return id_pesquisa_satis;}
    public void setId_pesquisa_satis(int id_pesquisa_satis) {this.id_pesquisa_satis = id_pesquisa_satis;}
    public int getNt_app() {return nt_app;}
    public void setNt_app(int nt_app) {this.nt_app = nt_app;}
    public int getNt_site() {return nt_site;}
    public void setNt_site(int nt_site) {this.nt_site = nt_site;}
    public int getNt_suporte() {return nt_suporte;}
    public void setNt_suporte(int nt_suporte) {this.nt_suporte = nt_suporte;}
    public String getDt_pesquisa() {return dt_pesquisa;}
    public void setDt_pesquisa(String dt_pesquisa) {this.dt_pesquisa = dt_pesquisa;}
    public Paciente getPaciente() {return paciente;}
    public void setPaciente(Paciente paciente) {this.paciente = paciente;}

}
