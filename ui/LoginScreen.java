package ui;

import controllers.UserController;
import server.Client;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class LoginScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginScreen() {
        setTitle("Tela de Login");
        setSize(800, 600); // Aumentando o tamanho da tela para ocupar mais espaço
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializando os componentes
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        registerButton = new JButton("Cadastrar");


        // Layout
        setLayout(new BorderLayout());

        // Título
        JLabel titleLabel = new JLabel("ChatGDP", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(new Color(70, 130, 180));
        add(titleLabel, BorderLayout.NORTH);  // Alinhado no topo


        // Subtítulo
        JLabel subtitleLabel = new JLabel("Realize o login", JLabel.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitleLabel.setForeground(new Color(100, 100, 100));
        add(subtitleLabel, BorderLayout.CENTER);  // Subtítulo agora vai para o rodapé, logo abaixo do título

        // Painel para o conteúdo (campos de login)
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Espaçamento entre os componentes

        // Adicionando os componentes ao painel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // Alinhamento das labels à esquerda
        panel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Para garantir que o campo de texto ocupe mais largura
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Senha:"), gbc);  // Alinhado na mesma coluna do "Email"

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        // Alinhando os botões nas mesmas colunas dos campos de entrada
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1; // Redefinir o gridwidth para o botão ocupar uma coluna
        panel.add(loginButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(registerButton, gbc);

        // Colocando o painel de login no centro da tela
        add(panel, BorderLayout.CENTER);

// Ação do botão "Login"
        Action loginAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Campos Vazios", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Tenta realizar o login
                try {
                    Client client = new Client("localhost", 12345, email);
                    String request = "signin:" + email + "," + password;
                    String response = client.sendMessage(request); // Envia a mensagem ao servidor e recebe a resposta

                    if ("Login bem-sucedido".equals(response)) {
                        // Abre uma nova janela de chat para o usuário logado
                        SwingUtilities.invokeLater(() -> new ChatScreen(client).setVisible(true));
                        // Não fechar a tela de login
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro no login: " + response, "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao se conectar ao servidor: " + ex.getMessage(), "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        // Associando a ação ao clique do botão
        loginButton.addActionListener(loginAction);

        //Associando a tecla Enter à mesma ação
        InputMap inputMap = loginButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = loginButton.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "loginAction");
        actionMap.put("loginAction", loginAction);


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterScreen().setVisible(true);
                dispose();
            }
        });

        // Personalização dos componentes
        customizeComponents();
    }

    // Personaliza a aparência dos componentes (botões, campos de texto, etc.)
    private void customizeComponents() {
        // Caixa de entrada de email
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        usernameField.setBackground(new Color(245, 245, 245)); // Cor de fundo suave
        usernameField.setForeground(Color.BLACK);
        usernameField.setCaretColor(Color.BLACK);
        usernameField.setPreferredSize(new Dimension(400, 50)); // Aumentando o tamanho do campo
        usernameField.setFocusTraversalKeysEnabled(false);

        // Caixa de entrada de senha
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        passwordField.setBackground(new Color(245, 245, 245)); // Cor de fundo suave
        passwordField.setForeground(Color.BLACK);
        passwordField.setCaretColor(Color.BLACK);
        passwordField.setPreferredSize(new Dimension(400, 50)); // Aumentando o tamanho do campo
        passwordField.setFocusTraversalKeysEnabled(false);

        // Botão "Login"
        loginButton.setFont(new Font("Arial", Font.PLAIN, 18));
        loginButton.setBackground(new Color(70, 130, 180)); // Azul suave
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setOpaque(true);
        loginButton.setUI(new BasicButtonUI()); // Remover borda do botão padrão

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                loginButton.setBackground(new Color(100, 150, 200)); // Cor ao passar o mouse
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                loginButton.setBackground(new Color(70, 130, 180)); // Cor original
            }
        });

        // Botão "Cadastrar"
        registerButton.setFont(new Font("Arial", Font.PLAIN, 18));
        registerButton.setBackground(new Color(220, 20, 60)); // Vermelho suave
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerButton.setOpaque(true);
        registerButton.setUI(new BasicButtonUI()); // Remover borda do botão padrão

        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                registerButton.setBackground(new Color(255, 99, 71)); // Cor ao passar o mouse
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                registerButton.setBackground(new Color(220, 20, 60)); // Cor original
            }
        });
    }
}
