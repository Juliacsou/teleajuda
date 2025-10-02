package br.com.fiap.teleajuda.domain.model;

import br.com.fiap.teleajuda.domain.model.pessoa.Funcionario;
import br.com.fiap.teleajuda.domain.model.pessoa.Paciente;

public class Ticket {
    private int codigo;
    private String assunto;
    private String descricao;
    private String resposta;
    private boolean status;
    private Paciente paciente;
    private Funcionario funcionario;
    private String data;

    //contrutor
    public Ticket(int codigo, String assunto, String descricao, String resposta, boolean status, Paciente paciente, Funcionario funcionario, String data) {
        this.codigo = codigo;
        this.assunto = assunto;
        this.descricao = descricao;
        this.resposta = resposta;
        this.status = status;
        this.paciente = paciente;
        this.funcionario = funcionario;
        this.data = data;
    }
    public Ticket(int codigo, String assunto, String descricao, boolean status, Paciente paciente, String data) {
        this.codigo = codigo;
        this.assunto = assunto;
        this.descricao = descricao;
        this.status = status;
        this.paciente = paciente;
        this.data = data;
    }

    //Getters e Setters

    public String getResposta() {return resposta;}
    public void setResposta(String resposta) {this.resposta = resposta;}
    public int getCodigo() {return codigo;}
    public void setCodigo(int codigo) {this.codigo = codigo;}
    public String getAssunto() {return assunto;}
    public void setAssunto(String tema) {this.assunto = tema;}
    public String getDescricao() {return descricao;}
    public void setDescricao(String problema) {this.descricao = problema;}
    public boolean getStatus() {return status;}
    public void setStatus(boolean solucionado) {this.status = solucionado;}
    public Paciente getPaciente() {return paciente;}
    public void setPaciente(Paciente paciente) {this.paciente = paciente;}
    public Funcionario getFuncionario() {return funcionario;}
    public void setFuncionario(Funcionario funcionario) {this.funcionario = funcionario;}
    public String getData() {return data;}
    public void setData(String data) {this.data = data;}

    //Metodos
    public void fecharTicket() {
        this.status = false;
    }
    public void abrirTicket() {
        this.status = true;
    }
    public char getStatusChar() {
        if (this.status) {
            return 'A';
        }else{
            return 'F';
        }}

}
