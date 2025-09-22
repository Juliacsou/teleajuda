package pessoa;
import filial.Filial;
public class Funcionario extends Pessoa {
    private int codigo;
    private Filial filial;

    //Construtor
    public Funcionario(String nome, String email, User user, int codigo, Filial filial) {
        super(nome, email, user);
        this.codigo = codigo;
        this.filial = filial;
    }

    //Getters e Setters
    public int getCodigo() {return codigo;}
    public void setCodigo(int codigo) {this.codigo = codigo;}
    public Filial getFilial() {return filial;}
    public void setFilial(Filial filial) {this.filial = filial;}

    //Metodos
    public void exibirFuncionario(){
        System.out.println("Nome: " + getNome());
        System.out.println("Email: " + getEmail());
        System.out.println("Codigo: " + getCodigo());
        System.out.println("Filial: " + getFilial().getNome());
    }
}
