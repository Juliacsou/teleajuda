package pessoa;

public class User {
    private String user;
    private String senha;
    private String tipo;

    //Contrutor
    public User(String user, String senha, String tipo) {
        this.user = user;
        this.senha = senha;
        this.tipo = tipo;
    }

    //Getters e Setters
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

    public void exibirUsuario() {
        System.out.println("Usuario: " + user);
        System.out.println("Senha: " + senha);
    }
}

