package br.com.fiap.teleajuda.domain.model;

import br.com.fiap.teleajuda.domain.repository.FuncionarioRepository;

public class Ticket {
    private int id_ticket;
    private String assunto;
    private String descricao;
    private String resposta;
    private String dt_abertura;
    private String dt_fechamento;
    private String status;
    private Paciente paciente;
    private Funcionario funcionario;

    public Ticket(int id_ticket, String assunto, String descricao, String resposta, String dt_abertura, String dt_fechamento, String status, Paciente paciente) {
        this.id_ticket = id_ticket;
        this.assunto = assunto;
        this.descricao = descricao;
        this.resposta = resposta;
        this.dt_abertura = dt_abertura;
        this.dt_fechamento = dt_fechamento;
        this.status = status;
        this.paciente = paciente;
    }

    public Ticket(String assunto, String descricao, String dt_abertura, String status, Paciente paciente) {
        this.assunto = assunto;
        this.descricao = descricao;
        this.dt_abertura = dt_abertura;
        this.status = status;
        this.paciente = paciente;
    }


    public int getId_ticket() {return id_ticket;}
    public void setId_ticket(int id_ticket) {this.id_ticket = id_ticket;}
    public String getAssunto() {return assunto;}
    public void setAssunto(String assunto) {this.assunto = assunto;}
    public String getDescricao() {return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}
    public String getResposta() {return resposta;}
    public void setResposta(String resposta) {this.resposta = resposta;}
    public String getDt_abertura() {return dt_abertura;}
    public void setDt_abertura(String dt_abertura) {this.dt_abertura = dt_abertura;}
    public String getDt_fechamento() {return dt_fechamento;}
    public void setDt_fechamento(String dt_fechamento) {this.dt_fechamento = dt_fechamento;}
    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
    public Paciente getPaciente() {return paciente;}
    public void setPaciente(Paciente paciente) {this.paciente = paciente;}
    public Funcionario getFuncionario() {return funcionario;}
    public void setFuncionario(Funcionario funcionario) {this.funcionario = funcionario;}
}
