package br.com.fiap.teleajuda.domain.model.pessoa;
public class Funcionario extends Pessoa {
    
    private int codigo;
    

    //Construtor
    public Funcionario(String nome, String email, User user, int codigo) {
        super(nome, email, user);
        this.codigo = codigo;
    }
    public Funcionario(String nome, String email, int codigo) {
        super(nome, email);
        this.codigo = codigo;
    }

    //Getters e Setters
    public int getCodigo() {return codigo;}
    public void setCodigo(int codigo) {this.codigo = codigo;}

}
