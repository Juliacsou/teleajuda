package br.com.fiap.teleajuda.domain.model;

import br.com.fiap.teleajuda.domain.model.pessoa.Paciente;

public class PesquisaSatisfacao {
    private int codigo;
    private int notaApp;
    private int notaSite;
    private int notaSuporte;
    private Paciente paciente;

    //Contrutor
    public PesquisaSatisfacao(int codigo, int notaApp, int notaSite, int notaSuporte, Paciente paciente) {
        this.codigo = codigo;
        this.notaApp = notaApp;
        this.notaSite = notaSite;
        this.notaSuporte = notaSuporte;
        this.paciente = paciente;
    }

    //Getters e Setters
    public int getCodigo() {return codigo;}
    public void setCodigo(int codigo) {this.codigo = codigo;}
    public int getNotaApp() {return notaApp;}
    public void setNotaApp(int notaApp) {this.notaApp = notaApp;}
    public int getNotaSite() {return notaSite;}
    public void setNotaSite(int notaSite) {this.notaSite = notaSite;}
    public int getNotaSuporte() {return notaSuporte;}
    public void setNotaSuporte(int notaSuporte) {this.notaSuporte = notaSuporte;}
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
        System.out.println("Nota do App: " + notaApp);
        System.out.println("Nota do Site: " + notaSite);
        System.out.println("Nota do Suporte: " + notaSuporte);
        System.out.println("Sua nota média foi " + calcularMedia(notaApp, notaSite, notaSuporte));

    }
}
