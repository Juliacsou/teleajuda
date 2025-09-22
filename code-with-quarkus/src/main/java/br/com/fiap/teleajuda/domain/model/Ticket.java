package br.com.fiap.teleajuda.domain.model;

import br.com.fiap.teleajuda.domain.model.pessoa.Funcionario;
import br.com.fiap.teleajuda.domain.model.pessoa.Paciente;

public class Ticket {
    private int codigo;
    private String tema;
    private String problema;
    private String resposta;
    private boolean solucionado;
    private Paciente paciente;
    private Funcionario funcionario;
    private String data;

    //contrutor


    public Ticket(int codigo, String tema, String problema, String resposta, boolean solucionado, Paciente paciente, Funcionario funcionario, String data) {
        this.codigo = codigo;
        this.tema = tema;
        this.problema = problema;
        this.resposta = resposta;
        this.solucionado = solucionado;
        this.paciente = paciente;
        this.funcionario = funcionario;
        this.data = data;
    }

    //Getters e Setters

    public String getResposta() {return resposta;}
    public void setResposta(String resposta) {this.resposta = resposta;}
    public int getCodigo() {return codigo;}
    public void setCodigo(int codigo) {this.codigo = codigo;}
    public String getTema() {return tema;}
    public void setTema(String tema) {this.tema = tema;}
    public String getProblema() {return problema;}
    public void setProblema(String problema) {this.problema = problema;}
    public boolean isSolucionado() {return solucionado;}
    public void setSolucionado(boolean solucionado) {this.solucionado = solucionado;}
    public Paciente getPaciente() {return paciente;}
    public void setPaciente(Paciente paciente) {this.paciente = paciente;}
    public Funcionario getFuncionario() {return funcionario;}
    public void setFuncionario(Funcionario funcionario) {this.funcionario = funcionario;}
    public String getData() {return data;}
    public void setData(String data) {this.data = data;}

    //Metodos
    public void fecharTicket() {
        this.solucionado = true;
    }
    public void abrirTicket() {
        this.solucionado = false;
    }
    public void exibirTicket() {
        System.out.println("Paciente: " + paciente.getNome());
        System.out.println("Assunto: " + tema);
        System.out.println("Descrição do problema: " + problema);
        if (isSolucionado()){
            System.out.println("Resposta: " + resposta);
            System.out.println("Status: Fechado");
            System.out.println("Suporte responsavel: " + funcionario.getNome());
        }else{
            System.out.println("Status: Aberto");
            System.out.println("Resposta: Sem resposta");
        }

    }
}
