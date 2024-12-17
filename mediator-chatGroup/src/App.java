import ui.ChatScreen;
import ui.LoginScreen;
import ui.RegisterScreen;
import javax.swing.*;

public class App {
    public static void run() {
        // Configurações básicas para garantir que a UI seja thread-safe
        // Inicia a tela de login
        SwingUtilities.invokeLater(App::showLoginScreen);
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
