import ui.ChatScreen;
import ui.LoginScreen;
import ui.RegisterScreen;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class App {

    private static final String USER_FILE = "users.txt";
    private static final String MESSAGE_FILE = "messages.txt";

    public static void run() {
        // Inicializa os arquivos necessários
        initializeFiles();

        // Configurações básicas para garantir que a UI seja thread-safe
        SwingUtilities.invokeLater(App::showLoginScreen);
    }

    private static void initializeFiles() {
        try {
            File userFile = new File(USER_FILE);
            File messageFile = new File(MESSAGE_FILE);

            // Cria o arquivo de usuários se ele não existir
            if (userFile.createNewFile()) {
                System.out.println("Arquivo de usuários criado: " + USER_FILE);
            }

            // Cria o arquivo de mensagens se ele não existir
            if (messageFile.createNewFile()) {
                System.out.println("Arquivo de mensagens criado: " + MESSAGE_FILE);
            }

        } catch (IOException e) {
            System.err.println("Erro ao inicializar os arquivos.");
            e.printStackTrace();
        }
    }

    public static void showLoginScreen() {
        // Cria e exibe a tela de login
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.setVisible(true);
    }

    public static void showRegisterScreen() {
        // Cria e exibe a tela de registro
        RegisterScreen registerScreen = new RegisterScreen();
        registerScreen.setVisible(true);
    }

    public static void showChatScreen() {
        // Cria e exibe a tela de chat
        ChatScreen chatScreen = new ChatScreen();
        chatScreen.setVisible(true);
    }
}
