package br.com.fiap.teleajuda.domain.model;

import br.com.fiap.teleajuda.domain.model.pessoa.Paciente;

public class PesquisaSatisfacao {
    private int id_pesquisa_satis;
    private int nt_app;
    private int nt_site;
    private int nt_suporte;
    private String dt_pesquisa;
    private Paciente paciente;

    //Contrutor
    public PesquisaSatisfacao(int id_pesquisa_satis, int nt_app, int nt_site, int nt_suporte, String dt_pesquisa, Paciente paciente) {
        this.id_pesquisa_satis = id_pesquisa_satis;
        this.nt_app = nt_app;
        this.nt_site = nt_site;
        this.nt_suporte = nt_suporte;
        this.dt_pesquisa = dt_pesquisa;
        this.paciente = paciente;
    }


    //Getters e Setters
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

    //Metodos
    public boolean isValidNotaApp(int notaApp){
        if(notaApp < 0 || notaApp > 10){
            return false;
        }else{
            return true;
        }
    }

    public boolean isValidNotaSite(int notaSite){
        if(notaSite < 0 || notaSite > 10){
            return false;
        }else{
            return true;
        }
    }

    public boolean isValidNotaSuporte(int notaSuporte){
        if(notaSuporte < 0 || notaSuporte > 10){
            return false;
        }else{
            return true;
        }
    }
    public float calcularMedia(int notaApp, int notaSite, int notaSuporte) {
        return (float) (notaApp + notaSite + notaSuporte) / 3;
    }

    public void exibirPesquisaSatisfacao() {
        System.out.println("Nota do App: " + nt_app);
        System.out.println("Nota do Site: " + nt_site);
        System.out.println("Nota do Suporte: " + nt_suporte);
        System.out.println("Sua nota média foi " + calcularMedia(nt_app, nt_site, nt_suporte));

    }
}
