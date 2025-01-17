package ui;

import controllers.UserController;
import server.Client;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class RegisterScreen extends JFrame {
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton backButton;

    public RegisterScreen() {
        setTitle("Tela de Cadastro");
        setSize(800, 600); // Tamanho da tela maior para melhor experiência
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializando os componentes
        usernameField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        registerButton = new JButton("Cadastrar");
        backButton = new JButton("Voltar");

        // Layout
        setLayout(new BorderLayout());

        // Título
        JLabel titleLabel = new JLabel("ChatGDP", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(new Color(70, 130, 180));
        add(titleLabel, BorderLayout.NORTH);

        // Subtítulo
        JLabel subtitleLabel = new JLabel("Realize o cadastro", JLabel.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitleLabel.setForeground(new Color(100, 100, 100));
        add(subtitleLabel, BorderLayout.SOUTH);

        // Painel para o conteúdo (campos de cadastro)
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Espaçamento entre os componentes

        // Adicionando os componentes ao painel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Nome de usuário:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Senha:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(passwordField, gbc);

        // Alinhando os botões nas mesmas colunas dos campos de entrada
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(registerButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        panel.add(backButton, gbc);

        // Colocando o painel de cadastro no centro da tela
        add(panel, BorderLayout.CENTER);

        // Ação dos botões
        Action registerAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = usernameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Conectar ao servidor e enviar os dados
                try  {
                    Client client = new Client("localhost", 12345, email);
                    String request = "signup:" + name + "," + password + "," + email;
                    String response = client.sendMessage(request); // Envia a mensagem ao servidor e recebe a resposta

                    // Exibe a resposta do servidor
                    if (response.equals("Cadastro bem-sucedido")) {
                        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso.");
                        new ChatScreen(client).setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, response); // Exibe a mensagem de erro do servidor
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao se conectar ao servidor: " + ex.getMessage());
                }
            }
        };

        // Associando a ação ao clique do botão
        registerButton.addActionListener(registerAction);

        //Associando a tecla Enter à mesma ação
        InputMap inputMap = registerButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = registerButton.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "registerAction");
        actionMap.put("registerAction", registerAction);

        
        //Botão Voltar
        Action backAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginScreen().setVisible(true);
                dispose();
            }
        };

        // Associando a ação ao clique do botão
        backButton.addActionListener(backAction);

        //Associando a tecla Enter à mesma ação
        InputMap inputMap2 = backButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap2 = backButton.getActionMap();
        inputMap2.put(KeyStroke.getKeyStroke("ESCAPE"), "backAction");
        actionMap2.put("backAction", backAction);


        // Personalização dos componentes
        customizeComponents();
    }

    // Personaliza a aparência dos componentes (botões, campos de texto, etc.)
    private void customizeComponents() {
        // Caixa de entrada de nome de usuário
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        usernameField.setBackground(new Color(245, 245, 245));
        usernameField.setForeground(Color.BLACK);
        usernameField.setCaretColor(Color.BLACK);
        usernameField.setPreferredSize(new Dimension(400, 50));
        usernameField.setFocusTraversalKeysEnabled(false);

        // Caixa de entrada de email
        emailField.setFont(new Font("Arial", Font.PLAIN, 16));
        emailField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        emailField.setBackground(new Color(245, 245, 245));
        emailField.setForeground(Color.BLACK);
        emailField.setCaretColor(Color.BLACK);
        emailField.setPreferredSize(new Dimension(400, 50));
        emailField.setFocusTraversalKeysEnabled(false);

        // Caixa de entrada de senha
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        passwordField.setBackground(new Color(245, 245, 245));
        passwordField.setForeground(Color.BLACK);
        passwordField.setCaretColor(Color.BLACK);
        passwordField.setPreferredSize(new Dimension(400, 50));
        passwordField.setFocusTraversalKeysEnabled(false);

        // Botão "Cadastrar"
        registerButton.setFont(new Font("Arial", Font.PLAIN, 18));
        registerButton.setBackground(new Color(70, 130, 180)); // Azul suave
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerButton.setOpaque(true);
        registerButton.setUI(new BasicButtonUI());

        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                registerButton.setBackground(new Color(100, 150, 200)); // Cor ao passar o mouse
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                registerButton.setBackground(new Color(70, 130, 180)); // Cor original
            }
        });

        // Botão "Voltar"
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setBackground(new Color(220, 20, 60)); // Vermelho suave
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setOpaque(true);
        backButton.setUI(new BasicButtonUI());

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                backButton.setBackground(new Color(255, 99, 71)); // Cor ao passar o mouse
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                backButton.setBackground(new Color(220, 20, 60)); // Cor original
            }
        });
    }


}
