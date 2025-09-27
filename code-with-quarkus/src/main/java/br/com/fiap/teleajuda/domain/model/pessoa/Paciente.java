package br.com.fiap.teleajuda.domain.model.pessoa;

public class Paciente extends Pessoa {
    private String cpf;
    private String telefone;
    private String data_nasc;
    private String rghc;

    //Construtor
    public Paciente(String nome, String email, String cpf, String telefone, String data_nasc, String rghc) {
        super(nome, email);
        this.cpf = cpf;
        this.telefone = telefone;
        this.data_nasc = data_nasc;
        this.rghc = rghc;
    }
    public Paciente(String nome, String email, User user, String cpf, String telefone, String data_nasc, String rghc) {
        super(nome, email, user);
        this.cpf = cpf;
        this.telefone = telefone;
        this.data_nasc = data_nasc;
        this.rghc = rghc;
    }

    //Getter e Setters
    public String getCpf() {return cpf;}
    public void setCpf(String cpf) {this.cpf = cpf;}
    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}
    public String getData_nasc() {return data_nasc;}
    public void setData_nasc(String data_nasc) {this.data_nasc = data_nasc;}
    public String getRghc() {return rghc;}
    public void setRghc(String rghc) {this.rghc = rghc;}
}
