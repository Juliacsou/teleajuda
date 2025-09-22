// import filial.Endereco;
// import filial.Filial;
// import pessoa.Funcionario;
// import pessoa.Medico;
// import pessoa.Paciente;
// import pessoa.User;
// import java.util.Scanner;

// public class  Sistema {
//     static Endereco endereco = new Endereco("05638-070", "Avenida Paulista", 1564, "1 andar","Paulista", "São Paulo", "SP");
//     static Filial filial = new Filial(1, "Paulista", endereco, "(11)12345-6789", "paulista@hc.com.br");
//     static User userPaciente = new User("Paciente", "1234", "Paciente");
//     static User userFuncionario = new User("Funcionario", "123456", "Funcionario");
//     static User userMedico = new User("Medico", "12345678", "Medico");
//     static Paciente paciente = new Paciente("Julia de Altino","julia@gmail.com",userPaciente,15051, "(11)12345-6789", "17/02/2001",filial);
//     static Funcionario funcionario = new Funcionario("Joaquim", "suporte@hc.com.br", userFuncionario, 1,filial);
//     static Medico medico = new Medico("Dr. Alberto Rodriguez", "dralberto@hc.com.br", userMedico, "CRM/SP 123456");
//     static Consulta consulta = new Consulta(1, "12/06/2005", "13:30", paciente, medico);
//     static PesquisaSatisfacao pesquisaSatisfacao = new PesquisaSatisfacao(1, 10,10,10,paciente);
//     static Ticket ticket = new Ticket(1, "Problema ao logar", "Não estou conseguindo fazer login no aplicativo", "É necessário fazer o primeiro login antes de tentar logar normalmente",false, paciente, funcionario, "13/05/2025");

//     public static void main(String[] args) {
//         logar();
//     }

//     public static void logar(){
//         Scanner scanner = new Scanner(System.in);
//         System.out.println("==========================");
//         System.out.println("FAÇA SEU LOGIN");
//         System.out.println("==========================");
//         System.out.println("Digite o usuario");
//         String usuario = scanner.nextLine();
//         System.out.println("Digite o senha");
//         String senha = scanner.nextLine();

//         if (userMedico.isValid(usuario,senha)) {
//             medicoMenu();
//         } else if (userPaciente.isValid(usuario,senha)) {
//             pacienteMenu();
//         } else if (userFuncionario.isValid(usuario,senha)) {
//             funcionarioMenu();
//         } else{
//             System.out.println("USUARIO INVALIDO!");
//             logar();
//         }
//         scanner.close();
//     }

//     //AREA DO MEDICO
//     public static void medicoMenu(){
//         Scanner scanner = new Scanner(System.in);
//         System.out.println("====================================");
//         System.out.println("BEM VINDO(A)" + medico.getNome());
//         System.out.println("====================================");
//         System.out.println("1 - Consultas");
//         System.out.println("2 - Pacientes");
//         System.out.println("3 - Perfil");
//         System.out.println("4 - Deslogar");
//         System.out.println("--------------------------------");
//         System.out.println("Selecione a opção desejada");
//         int opcao = scanner.nextInt();
//         switch (opcao) {
//             case 1:
//                 medicoConsulta();
//             case 2:
//                 verPaciente();
//             case 3:
//                 perfilMedico();
//             case 4:
//                 logar();
//             default:
//                 System.out.println("Opção invalida!");
//                 medicoMenu();
//         }
//         scanner.close();
//     }
//     public static void medicoConsulta() {
//         Scanner scanner = new Scanner(System.in);
//         consulta.exibirConsulta();
//         System.out.println("---------------------------------------------------");
//         System.out.println("1 - Editar consulta/0 - Voltar");
//         int opcao = scanner.nextInt();
//         if (opcao == 1) {
//             scanner.nextLine();
//             System.out.println("----------------------------------");
//             System.out.println("EDITAR CONSULTA");
//             System.out.println("Digite a nova data: ");
//             String data = scanner.nextLine();
//             consulta.setData(data);
//             System.out.println("Digite a nova hora: ");
//             String hora = scanner.nextLine();
//             consulta.setHora(hora);
//             System.out.println("CONSULTA ATUALIZADA");
//             medicoConsulta();
//         }else{
//             medicoMenu();
//         }
//         scanner.close();
//     }

//     public static void verPaciente() {
//         Scanner scanner = new Scanner(System.in);
//         paciente.exibirPaciente();
//         System.out.println("--------------------------------------------------");
//         System.out.println("Aperte Enter para voltar...");
//         scanner.nextLine();
//         medicoMenu();
//         scanner.close();
//     }

//     public static void perfilMedico() {
//         Scanner scanner = new Scanner(System.in);
//         medico.exibirMedico();
//         userMedico.exibirUsuario();
//         System.out.println("---------------------------------------------------");
//         System.out.println("1 - Editar cadastro/0 - Voltar");
//         int opcao = scanner.nextInt();
//         if (opcao == 1) {
//             scanner.nextLine();
//             System.out.println("--------------------------------");
//             System.out.println("EDITAR CADASTRO");
//             System.out.println("--------------------------------");
//             System.out.println("1 - Nome");
//             System.out.println("2 - CRM");
//             System.out.println("3 - Email");
//             System.out.println("4 - Usuario");
//             System.out.println("5 - Senha");
//             System.out.println("-------------------------------------------------");
//             System.out.println("Selecione a informação que gostaria de atualizar");
//             int opcaoEdit = scanner.nextInt();
//             scanner.nextLine();
//             switch (opcaoEdit) {
//                 case 1:
//                     System.out.println("Digite novo nome: ");
//                     String nome = scanner.nextLine();
//                     medico.setNome(nome);
//                     System.out.println("CADASTRO ATUALIZADO");
//                     perfilMedico();
//                     break;
//                 case 2:
//                     System.out.println("Digite novo CRM: ");
//                     String crm = scanner.nextLine();
//                     medico.setCrm(crm);
//                     System.out.println("CADASTRO ATUALIZADO");
//                     perfilMedico();
//                     break;
//                 case 3:
//                     System.out.println("Digite novo email: ");
//                     String email = scanner.nextLine();
//                     medico.setEmail(email);
//                     System.out.println("CADASTRO ATUALIZADO");
//                     perfilMedico();
//                     break;
//                 case 4:
//                     System.out.println("Digite novo usuario: ");
//                     String usuario = scanner.nextLine();
//                     medico.getUser().setUser(usuario);
//                     System.out.println("CADASTRO ATUALIZADO");
//                     perfilMedico();
//                     break;
//                 case 5:
//                     System.out.println("Digite nova senha: ");
//                     String senha = scanner.nextLine();
//                     medico.getUser().setSenha(senha);
//                     System.out.println("CADASTRO ATUALIZADO");
//                     perfilMedico();
//                     break;
//                 default:
//                     System.out.println("Opção invalida!");
//                     perfilMedico();
//             }
//         }else{
//             medicoMenu();
//         }
//         scanner.close();
//     }

//     //AREA DO PACIENTE
//     public static void pacienteMenu(){
//         Scanner scanner = new Scanner(System.in);
//         System.out.println("====================================");
//         System.out.println("BEM VINDO(A)" + paciente.getNome());
//         System.out.println("====================================");
//         System.out.println("1 - Consultas");
//         System.out.println("2 - Tickets");
//         System.out.println("3 - Pesquisa de Satisfação");
//         System.out.println("4 - Perfil");
//         System.out.println("5 - Deslogar");
//         System.out.println("-------------------------------");
//         System.out.println("Selecione a opção desejada");
//         int opcao = scanner.nextInt();
//         switch (opcao) {
//             case 1:
//                 pacienteConsulta();
//             case 2:
//                 ticketPaciente();
//             case 3:
//                 pesquisaStistacao();
//             case 4:
//                 perfilPaciente();
//             case 5:
//                 logar();
//             default:
//                 System.out.println("Opção invalida!");
//                 pacienteMenu();
//         }
//         scanner.close();
//     }
//     public static void pacienteConsulta(){
//         Scanner scanner = new Scanner(System.in);
//         consulta.exibirConsulta();
//         System.out.println("--------------------------------------------------");
//         System.out.println("Aperte Enter para voltar...");
//         scanner.nextLine();
//         pacienteMenu();
//         scanner.close();
//     }
//     public static void ticketPaciente(){
//         Scanner scanner = new Scanner(System.in);
//         System.out.println("1- Visualizar Tickets");
//         System.out.println("2- Abrir novo tickets");
//         System.out.println("3- Voltar para menu");
//         int opcao = scanner.nextInt();
//         scanner.nextLine();
//         switch (opcao) {
//             case 1:
//                 ticket.exibirTicket();
//                 System.out.println("--------------------------------------------------");
//                 System.out.println("Aperte Enter para voltar...");
//                 scanner.nextLine();
//                 ticketPaciente();
//             case 2:
//                 System.out.println("Escreva o titulo do problema:");
//                 String assunto = scanner.nextLine();
//                 ticket.setTema(assunto);
//                 System.out.println("Descreva seu problema: ");
//                 String problema = scanner.nextLine();
//                 ticket.setProblema(problema);
//                 ticket.abrirTicket();
//                 System.out.println("---------------------");
//                 System.out.println("TICKET ABERTO!");
//                 System.out.println("O suporte entrara em contato em breve.");
//                 System.out.println("--------------------------------------------------");
//                 System.out.println("Aperte Enter para voltar...");
//                 scanner.nextLine();
//                 ticketPaciente();
//             case 3:
//                 pacienteMenu();
//             default:
//                 System.out.println("Opção invalida!");
//                 ticketPaciente();
//         }
//         scanner.close();
//     }

//     public static void pesquisaStistacao(){
//         Scanner scanner = new Scanner(System.in);
//         System.out.println("Digite uma nota de 0 a 10 para nosso App: ");
//         int notaApp = scanner.nextInt();
//         while (!pesquisaSatisfacao.isValidNotaApp(notaApp)) {
//             System.out.println("NOTA INVALIDA!");
//             System.out.println("Digite uma nota de 0 a 10 para nosso App: ");
//             notaApp = scanner.nextInt();
//         }
//         pesquisaSatisfacao.setNotaApp(notaApp);

//         System.out.println("Digite uma nota de 0 a 10 para nosso Site: ");
//         int notaSite = scanner.nextInt();
//         while (!pesquisaSatisfacao.isValidNotaSite(notaSite)) {
//             System.out.println("NOTA INVALIDA!");
//             System.out.println("Digite uma nota de 0 a 10 para nosso Site: ");
//             notaSite = scanner.nextInt();
//         }
//         pesquisaSatisfacao.setNotaSite(notaSite);

//         System.out.println("Digite uma nota de 0 a 10 para nosso Suporte: ");
//         int notaSuporte = scanner.nextInt();
//         while (!pesquisaSatisfacao.isValidNotaSuporte(notaSuporte)) {
//             System.out.println("NOTA INVALIDA!");
//             System.out.println("Digite uma nota de 0 a 10 para nosso Suporte: ");
//             notaSuporte = scanner.nextInt();
//         }
//         pesquisaSatisfacao.setNotaSuporte(notaSuporte);
//         scanner.nextLine();
//         System.out.println("---------------------");
//         System.out.println("PESQUISA REALIZADA COM SUCESSO!");
//         pesquisaSatisfacao.exibirPesquisaSatisfacao();
//         System.out.println("Obrigada por sua participação!");
//         System.out.println("--------------------------------------------------");
//         System.out.println("Aperte Enter para voltar...");
//         scanner.nextLine();
//         pacienteMenu();
//         scanner.close();
//     }


//     public static void perfilPaciente() {
//         Scanner scanner = new Scanner(System.in);
//         paciente.exibirPaciente();
//         userPaciente.exibirUsuario();
//         System.out.println("---------------------------------------------------");
//         System.out.println("1 - Editar cadastro/0 - Voltar");
//         int opcao = scanner.nextInt();
//         if (opcao == 1) {
//             scanner.nextLine();
//             System.out.println("--------------------------------");
//             System.out.println("EDITAR CADASTRO");
//             System.out.println("--------------------------------");
//             System.out.println("1 - Nome");
//             System.out.println("2 - Email");
//             System.out.println("3 - Telefone");
//             System.out.println("4 - Data de Nascimento");
//             System.out.println("5 - Usuario");
//             System.out.println("6 - Senha");
//             System.out.println("-------------------------------------------------");
//             System.out.println("Selecione a informação que gostaria de atualizar");
//             int opcaoEdit = scanner.nextInt();
//             scanner.nextLine();
//             switch (opcaoEdit) {
//                 case 1:
//                     System.out.println("Digite novo nome: ");
//                     String nome = scanner.nextLine();
//                     paciente.setNome(nome);
//                     System.out.println("CADASTRO ATUALIZADO");
//                     perfilPaciente();
//                     break;
//                 case 2:
//                     System.out.println("Digite novo email: ");
//                     String email = scanner.nextLine();
//                     paciente.setEmail(email);
//                     System.out.println("CADASTRO ATUALIZADO");
//                     perfilPaciente();
//                     break;
//                 case 3:
//                     System.out.println("Digite novo telefone: ");
//                     String tel = scanner.nextLine();
//                     paciente.setTelefone(tel);
//                     System.out.println("CADASTRO ATUALIZADO");
//                     perfilPaciente();
//                     break;
//                 case 4:
//                     System.out.println("Digite nova data de nascimento: ");
//                     String data = scanner.nextLine();
//                     paciente.setData_nasc(data);
//                     System.out.println("CADASTRO ATUALIZADO");
//                     perfilPaciente();
//                     break;
//                 case 5:
//                     System.out.println("Digite novo usuario: ");
//                     String user = scanner.nextLine();
//                     paciente.getUser().setUser(user);
//                     System.out.println("CADASTRO ATUALIZADO");
//                     perfilPaciente();
//                     break;
//                 case 6:
//                     System.out.println("Digite novo senha: ");
//                     String senha = scanner.nextLine();
//                     paciente.getUser().setSenha(senha);
//                     System.out.println("CADASTRO ATUALIZADO");
//                     perfilPaciente();
//                 default:
//                     System.out.println("Opção invalida!");
//                     perfilPaciente();
//              }
//         }else{
//             pacienteMenu();
//         }
//         scanner.close();
//     }

//     //AREA DO FUNCIONARIO
//     public static void funcionarioMenu(){
//         Scanner scanner = new Scanner(System.in);
//         System.out.println("====================================");
//         System.out.println("BEM VINDO(A)" + funcionario.getNome());
//         System.out.println("====================================");
//         System.out.println("1 - Filiais");
//         System.out.println("2 - Tickets");
//         System.out.println("3 - Perfil");
//         System.out.println("4 - Deslogar");
//         System.out.println("-------------------------------");
//         System.out.println("Selecione a opção desejada");
//         int opcao = scanner.nextInt();
//         switch (opcao) {
//             case 1:
//                 filial();
//             case 2:
//                 ticketFuncionario();
//             case 3:
//                 perfilFuncionario();
//             case 4:
//                 logar();
//             default:
//                 System.out.println("Opção invalida!");
//                 funcionarioMenu();

//         }
//         scanner.close();
//     }

//     public static void filial(){
//         Scanner scanner = new Scanner(System.in);
//         filial.exibirFilial();
//         System.out.println("-------------------------------------------------------");
//         System.out.println("1 - Editar cadastro/0 - Voltar");
//         int opcao = scanner.nextInt();
//         if (opcao == 1) {
//             scanner.nextLine();
//             System.out.println("--------------------------------");
//             System.out.println("EDITAR CADASTRO");
//             System.out.println("-------------------------------");
//             System.out.println("1 - Nome da Filial");
//             System.out.println("2 - Telefone de contato");
//             System.out.println("3 - Email de contato");
//             System.out.println("4 - Endereço");
//             System.out.println("-------------------------------------------------");
//             System.out.println("Selecione a informação que gostaria de atualizar");
//             int opcaoEdit = scanner.nextInt();
//             scanner.nextLine();
//             switch (opcaoEdit) {
//                 case 1:
//                     System.out.println("Digite novo nome: ");
//                     String nome = scanner.nextLine();
//                     filial.setNome(nome);
//                     System.out.println("CADASTRO ATUALIZADO");
//                     filial();
//                     break;
//                 case 2:
//                     System.out.println("Digite novo telefone: ");
//                     String tel = scanner.nextLine();
//                     filial.setTelefone(tel);
//                     System.out.println("CADASTRO ATUALIZADO");
//                     filial();
//                     break;
//                 case 3:
//                     System.out.println("Digite novo email: ");
//                     String email = scanner.nextLine();
//                     filial.setEmail(email);
//                     System.out.println("CADASTRO ATUALIZADO");
//                     filial();
//                     break;
//                 case 4:
//                     System.out.println("Digite o CEP: ");
//                     String cep = scanner.nextLine();
//                     System.out.println("Digite o logradouro: ");
//                     String log = scanner.nextLine();
//                     System.out.println("Digite o numero: ");
//                     int num = scanner.nextInt();
//                     scanner.nextLine();
//                     System.out.println("Digite o complemento");
//                     String comp = scanner.nextLine();
//                     System.out.println("Digite o bairro");
//                     String bairro = scanner.nextLine();
//                     System.out.println("Digite a cidade");
//                     String cidade = scanner.nextLine();
//                     System.out.println("Digite o estado");
//                     String estado = scanner.nextLine();
//                     Endereco novoEnd = new Endereco(cep, log, num, comp, bairro, cidade, estado);
//                     filial.setEndereco(novoEnd);
//                     System.out.println("CADASTRO ATUALIZADO");
//                     filial();
//                     break;
//                 default:
//                     System.out.println("Escolha uma opção valida!");
//                     filial();
//             }
//         }else{
//             funcionarioMenu();
//         }
//     }
//         public static void ticketFuncionario(){
//             Scanner scanner = new Scanner(System.in);
//             ticket.exibirTicket();
//             System.out.println("--------------------------------------------------");
//             System.out.println("Responder Ticket? (1- SIM/0- NÃO)");
//             int opcao = scanner.nextInt();
//             scanner.nextLine();
//             if (opcao == 1){
//                 System.out.println("Escreva a resposta:");
//                 String resp = scanner.nextLine();
//                 ticket.setResposta(resp);
//                 ticket.setSolucionado(true);
//                 System.out.println("---------------------");
//                 System.out.println("TICKET SOLUCIONADO!");
//                 System.out.println("Aperte Enter para voltar...");
//                 scanner.nextLine();
//                 ticketFuncionario();
//             }else{
//                 System.out.println("Retornando ao menu!");
//                 funcionarioMenu();
//             }
//             scanner.close();
//         }

//         public static void perfilFuncionario(){
//             Scanner scanner = new Scanner(System.in);
//             funcionario.exibirFuncionario();
//             userFuncionario.exibirUsuario();
//             System.out.println("---------------------------------------------------");
//             System.out.println("1 - Editar cadastro/0 - Voltar");
//             int opcao = scanner.nextInt();
//             if (opcao == 1) {
//                 scanner.nextLine();
//                 System.out.println("--------------------------------");
//                 System.out.println("EDITAR CADASTRO");
//                 System.out.println("1 - Nome");
//                 System.out.println("2 - Email");
//                 System.out.println("3 - Usuario");
//                 System.out.println("4 - Senha");
//                 System.out.println("-------------------------------------------------");
//                 System.out.println("Selecione a informação que gostaria de atualizar");
//                 int opcaoEdit = scanner.nextInt();
//                 scanner.nextLine();
//                 switch (opcaoEdit) {
//                     case 1:
//                         System.out.println("Digite novo nome: ");
//                         String nome = scanner.nextLine();
//                         funcionario.setNome(nome);
//                         System.out.println("CADASTRO ATUALIZADO");
//                         perfilFuncionario();
//                         break;
//                     case 2:
//                         System.out.println("Digite novo email: ");
//                         String email = scanner.nextLine();
//                         funcionario.setEmail(email);
//                         System.out.println("CADASTRO ATUALIZADO");
//                         perfilFuncionario();
//                         break;
//                     case 3:
//                         System.out.println("Digite novo usuario: ");
//                         String user = scanner.nextLine();
//                         funcionario.getUser().setUser(user);
//                         System.out.println("CADASTRO ATUALIZADO");
//                         perfilFuncionario();
//                         break;
//                     case 4:
//                         System.out.println("Digite nova senha: ");
//                         String senha = scanner.nextLine();
//                         funcionario.getUser().setSenha(senha);
//                         System.out.println("CADASTRO ATUALIZADO");
//                         perfilFuncionario();
//                     default:
//                         System.out.println("Opção invalida!");
//                         perfilFuncionario();
//                 }
//             }else{
//                 funcionarioMenu();
//             }
//             scanner.close();
//         }
// }
