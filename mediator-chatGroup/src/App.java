import mediator.Mediator;
import menuScreen.MenuScreen;

import java.util.Scanner;

public class App {
    public static void run() {
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while(running) {
            MenuScreen.menuInit();
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    MenuScreen.menuSignUp();
                    break;
                case 2:
                    boolean logged = MenuScreen.menuSignIn();;
                    while (!logged) {
                       logged = MenuScreen.menuSignIn();
                    }

                    MenuScreen.menuMainOptions();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

          }
        }

    }


