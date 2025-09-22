package br.com.fiap.teleajuda.domain.model.pessoa;

public class Paciente extends Pessoa {
    private int rghc;
    private String telefone;
    private String data_nasc;
 
    //Construtor
    public Paciente(String nome, String email, User user, int rghc, String telefone, String data_nasc) {
        super(nome, email, user);
        this.rghc = rghc;
        this.telefone = telefone;
        this.data_nasc = data_nasc;
    }

    //Getter e Setters
    public int getRghc() {return rghc;}
    public void setRghc(int rghc) {this.rghc = rghc;}
    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}
    public String getData_nasc() {return data_nasc;}
    public void setData_nasc(String data_nasc) {this.data_nasc = data_nasc;}

    public void exibirPaciente() {
        System.out.println("Nome: " + getNome());
        System.out.println("RGHC: " + getRghc());
        System.out.println("Email: " + getEmail());
        System.out.println("Telefone: " + getTelefone());
        System.out.println("Data de Nascimento: " + getData_nasc());
    }
}
