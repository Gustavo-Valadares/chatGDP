package menuScreen;
import controllers.UserController;
import mediator.Mediator;
import utils.ClearConsole;
import java.util.Scanner;


public class MenuScreen {

    public static void menuInit() {
        System.out.println("=================Bem vindo ao chatGDP=================\n");
        System.out.println("Selecione uma opção:\n");
        System.out.println("1- Cadastrar");
        System.out.println("2- Entrar");
        System.out.println("3- Encerrar");
    }

    public static void menuSignUp() {
        Scanner sc = new Scanner(System.in);
        ClearConsole.clearConsole();
        String nome, email, senha;
        System.out.println("\n=================Realize seu cadastro=================");
        System.out.println("1- Insira seu nome:");
        nome = sc.nextLine();
        System.out.println("2- Insira seu email:");
        email = sc.next();
        System.out.println("3- Insira sua senha:");
        senha = sc.nextLine();
        UserController.signUp(nome, senha, email);
    }

    public static boolean menuSignIn() {
        Scanner sc = new Scanner(System.in);
        ClearConsole.clearConsole();
        String email, senha;
        System.out.println("\n=================Realize o Login=================");
        System.out.println("1- Insira seu email:");
        email = sc.next();
        System.out.println("2- Insira sua senha:");
        senha = sc.nextLine();
        return UserController.signIn(email, senha);
    }

    public static void menuMainOptions() {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {

            System.out.println("\n=================chatGDP=================\n");
            System.out.println("Logado como: " + UserController.getUserLogged().getNome() + "\n");
            System.out.println("Mensagens atuais:");
            Mediator.showMessages();
            System.out.println("\n==========================================\n");
            System.out.println("Selecione uma opção:");
            System.out.println("1- Enviar nova mensagem");
            System.out.println("2- Finalizar minha sessão");
            System.out.print("Digite sua escolha: ");

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {

                case 1:
                    System.out.println("\n--- Enviar nova mensagem ---");
                    System.out.print("Digite sua mensagem: ");
                    String mensagem = sc.nextLine();
                    Mediator.sendMessage(mensagem);
                    System.out.println("Mensagem enviada: " + mensagem);
                    break;
                case 2:
                    System.out.println("Saindo do programa. Até logo!");
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }







}
