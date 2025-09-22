package br.com.fiap.teleajuda.domain.model.pessoa;

public class Pessoa {
    private String nome;
    private String email;
    private User user;

    //Contrutor
    public Pessoa(String nome, String email, User user) {
        this.nome = nome;
        this.email = email;
        this.user = user;
    }

    //Getters e Setters
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
}
