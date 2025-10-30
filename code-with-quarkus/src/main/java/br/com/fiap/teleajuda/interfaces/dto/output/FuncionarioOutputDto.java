package br.com.fiap.teleajuda.interfaces.dto.output;
public class FuncionarioOutputDto {

    private String cpf_funcionario;
    private String nm_funcionario;
    private String mail_funcionario;
    private String senha;

    public String getCpf_funcionario() {return cpf_funcionario;}
    public void setCpf_funcionario(String cpf_funcionario) {this.cpf_funcionario = cpf_funcionario;}
    public String getNm_funcionario() {return nm_funcionario;}
    public void setNm_funcionario(String nm_funcionario) {this.nm_funcionario = nm_funcionario;}
    public String getMail_funcionario() {return mail_funcionario;}
    public void setMail_funcionario(String mail_funcionario) {this.mail_funcionario = mail_funcionario;}
    public String getSenha() {return senha;}
    public void setSenha(String senha) {this.senha = senha;}

}
