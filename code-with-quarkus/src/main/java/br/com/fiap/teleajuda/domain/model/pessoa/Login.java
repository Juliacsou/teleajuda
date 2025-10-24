package br.com.fiap.teleajuda.domain.model.pessoa;

public class Login {
    private int id_login;
    private String user_login;
    private String senha_login;
    private String tp_login;

    //Contrutor
    public Login(int id_login, String user_login, String senha_login, String tp_login) {
        this.id_login = id_login;
        this.user_login = user_login;
        this.senha_login = senha_login;
        this.tp_login = tp_login;
    }

    //Getters e Setters
    public int getId_login() {return id_login;}
    public void setId_login(int id_login) {this.id_login = id_login;}
    public String getUser_login() {return user_login;}
    public void setUser_login(String user_login) {this.user_login = user_login;}
    public String getSenha_login() {return senha_login;}
    public void setSenha_login(String senha_login) {this.senha_login = senha_login;}
    public String getTp_login() {return tp_login;}
    public void setTp_login(String tp_login) {this.tp_login = tp_login;}

    //metodos
    public boolean isValid(String user, String senha) {
        return this.user_login.equals(user) && this.senha_login.equals(senha);
    }


}

