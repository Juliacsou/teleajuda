package br.com.fiap.teleajuda.domain.model.pessoa;
public class Funcionario {

    private int id_funcionario;
    private String nm_funcionario;
    private String mail_funcionario;
    private Login login;

    //CONTRUTOR
    public Funcionario(int id_funcionario, String nm_funcionario, String mail_funcionario, Login login) {
        this.id_funcionario = id_funcionario;
        this.nm_funcionario = nm_funcionario;
        this.mail_funcionario = mail_funcionario;
        this.login = login;
    }

    public Funcionario(int id_funcionario, String nm_funcionario, String mail_funcionario) {
        this.id_funcionario = id_funcionario;
        this.nm_funcionario = nm_funcionario;
        this.mail_funcionario = mail_funcionario;
    }

    //GETTERS E SETTERS

    public int getId_funcionario() {return id_funcionario;}
    public void setId_funcionario(int id_funcionario) {this.id_funcionario = id_funcionario;}
    public String getNm_funcionario() {return nm_funcionario;}
    public void setNm_funcionario(String nm_funcionario) {this.nm_funcionario = nm_funcionario;}
    public String getMail_funcionario() {return mail_funcionario;}
    public void setMail_funcionario(String mail_funcionario) {this.mail_funcionario = mail_funcionario;}
    public Login getLogin() {return login;}
    public void setLogin(Login login) {this.login = login;}

}
