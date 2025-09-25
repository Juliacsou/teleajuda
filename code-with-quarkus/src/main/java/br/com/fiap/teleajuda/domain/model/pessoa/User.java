package br.com.fiap.teleajuda.domain.model.pessoa;

public class User {
    private int id;
    private String user;
    private String senha;
    private String tipo;

    //Contrutor
    public User(int id, String user, String senha, String tipo) {
        this.id = id;
        this.user = user;
        this.senha = senha;
        this.tipo = tipo;
    }

    //Getters e Setters

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getUser() {return user;}
    public void setUser(String user) {this.user = user;}
    public String getSenha() {return senha;}
    public void setSenha(String senha) {this.senha = senha;}
    public String getTipo() {return tipo;}
    public void setTipo(String tipo) {this.tipo = tipo;}

    //metodos
    public boolean isValid(String user, String senha) {
        return this.user.equals(user) && this.senha.equals(senha);
    }


}

