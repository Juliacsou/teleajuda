package br.com.fiap.teleajuda.domain.model;

public class Paciente {
    private String cpf_paciente;
    private String nm_paciente;
    private String tel_paciente;
    private String mail_paciente;
    private String rghc;
    private String dt_nasc_paciente;
    private String senha_paciente;


    //CONSTRUTORES
    public Paciente(String cpf_paciente, String nm_paciente, String tel_paciente, String mail_paciente, String rghc, String dt_nasc_paciente) {
        this.cpf_paciente = cpf_paciente;
        this.nm_paciente = nm_paciente;
        this.tel_paciente = tel_paciente;
        this.mail_paciente = mail_paciente;
        this.rghc = rghc;
        this.dt_nasc_paciente = dt_nasc_paciente;
    }

    public Paciente(String cpf_paciente, String nm_paciente, String tel_paciente, String mail_paciente, String rghc, String dt_nasc_paciente, String senha_paciente) {
        this.cpf_paciente = cpf_paciente;
        this.nm_paciente = nm_paciente;
        this.tel_paciente = tel_paciente;
        this.mail_paciente = mail_paciente;
        this.rghc = rghc;
        this.dt_nasc_paciente = dt_nasc_paciente;
        this.senha_paciente = senha_paciente;
    }

    //GETTERS E SETTERS
    public String getCpf_paciente() {
        return cpf_paciente;
    }
    public void setCpf_paciente(String cpf_paciente) {
        this.cpf_paciente = cpf_paciente;
    }
    public String getNm_paciente() {
        return nm_paciente;
    }
    public void setNm_paciente(String nm_paciente) {
        this.nm_paciente = nm_paciente;
    }
    public String getTel_paciente() {
        return tel_paciente;
    }
    public void setTel_paciente(String tel_paciente) {
        this.tel_paciente = tel_paciente;
    }
    public String getMail_paciente() {
        return mail_paciente;
    }
    public void setMail_paciente(String mail_paciente) {
        this.mail_paciente = mail_paciente;
    }
    public String getRghc() {
        return rghc;
    }
    public void setRghc(String rghc) {
        this.rghc = rghc;
    }
    public String getDt_nasc_paciente() {
        return dt_nasc_paciente;
    }
    public void setDt_nasc_paciente(String dt_nasc_paciente) {
        this.dt_nasc_paciente = dt_nasc_paciente;
    }
    public String getSenha_paciente() {return senha_paciente;}
    public void setSenha_paciente(String senha_paciente) {this.senha_paciente = senha_paciente;}
}
