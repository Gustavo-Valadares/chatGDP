package ui;

import controllers.UserController;
import entities.Message;
import mediator.Mediator;
import server.Client;
import structData.MessageList;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Objects;

public class ChatScreen extends JFrame {
    private JTextArea chatArea; // Exibe as mensagens do chat
    private JTextField inputField; // Campo de entrada para novas mensagens
    private JButton sendButton; // Botão de enviar
    private JButton exitButton; // Botão de sair
    private JButton deleteUserButton; // Botão para deletar usuário
    private JList<Message> messageList; // Lista de mensagens
    private DefaultListModel<Message> messageListModel; // Modelo da lista de mensagens

    public ChatScreen(Client client) {
        setTitle("Chat em Grupo");
        setSize(800, 600); // Tamanho maior para a tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Componentes
        inputField = new JTextField();
        sendButton = new JButton("Enviar");
        exitButton = new JButton("Sair");
        deleteUserButton = new JButton("Excluir Usuário");

        // Modelo e lista de mensagens
        messageListModel = MessageList.getListModel();
        messageList = new JList<>(messageListModel);
        messageList.setCellRenderer(new MessageRenderer(client)); // Passando o cliente

        // Layout
        setLayout(new BorderLayout());
        add(new JScrollPane(messageList), BorderLayout.CENTER); // Exibir mensagens como lista
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        bottomPanel.add(exitButton, BorderLayout.WEST); // Ajustado para sair no canto esquerdo
        bottomPanel.add(deleteUserButton, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);

        // Configuração para enviar mensagem ao pressionar "Enter" no campo de entrada
        Action sendAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = inputField.getText().trim(); // Obtém o texto do campo de entrada
                if (!text.isEmpty()) {
                    try {
                        // Obtém nome e email a partir da resposta do servidor
                        String response = client.sendMessage("findEmail:" + client.email);
                        String[] userDetails = response.split(",");

                        if (userDetails.length < 2) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Erro ao obter detalhes do usuário. Resposta inválida do servidor.",
                                    "Erro",
                                    JOptionPane.ERROR_MESSAGE
                            );
                            return;
                        }

                        String nome = userDetails[0].trim();
                        String email = userDetails[1].trim();

                        // Prepara a requisição para enviar a mensagem
                        String request = "sendMessage:" + nome + "," + email + "," + text;

                        // Envia a mensagem ao servidor
                        try {
                            String serverResponse = client.sendMessage(request);
                            if (serverResponse.equalsIgnoreCase("Mensagem enviada")) {
                                // Adiciona a mensagem ao modelo local
                                Message newMessage = new Message(nome, text, email);
                                messageListModel.addElement(newMessage);

                                // Rola para a última mensagem
                                messageList.ensureIndexIsVisible(messageListModel.getSize() - 1);
                            } else {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Erro ao enviar mensagem. Tente novamente.",
                                        "Erro",
                                        JOptionPane.ERROR_MESSAGE
                                );
                            }
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Erro ao enviar mensagem: " + ex.getMessage(),
                                    "Erro",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Erro ao buscar detalhes do usuário: " + ex.getMessage(),
                                "Erro",
                                JOptionPane.ERROR_MESSAGE
                        );
                    } finally {
                        inputField.setText(""); // Limpa o campo de entrada, independentemente do resultado
                    }
                }
            }
        };

        // Associando a ação ao clique do botão
        sendButton.addActionListener(sendAction);

        // Associando a tecla Enter à mesma ação
        InputMap inputMap = sendButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = sendButton.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "sendAction");
        actionMap.put("sendAction", sendAction);

        // Ação do botão "Sair"
        Action exitAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    client.closeConnection(); // Desloga o usuário
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                new LoginScreen().setVisible(true); // Abre a tela de login
                dispose(); // Fecha a tela atual
            }
        };

        // Associando a ação ao clique do botão
        exitButton.addActionListener(exitAction);

        // Associando a tecla ESC ao sair
        InputMap inputMap2 = exitButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap2 = exitButton.getActionMap();
        inputMap2.put(KeyStroke.getKeyStroke("ESCAPE"), "exitAction");
        actionMap2.put("exitAction", exitAction);

        // Botão Excluir
        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "removeUser:" + client.email;
                try {
                    client.sendMessage(message);
                    client.closeConnection();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                new LoginScreen().setVisible(true);
                dispose();
            }
        });

        // Personalização dos componentes
        customizeComponents();
    }

    // Personaliza a aparência dos componentes (botões, campo de entrada, etc.)
    private void customizeComponents() {
        // Botão "Enviar"
        sendButton.setFont(new Font("Arial", Font.PLAIN, 14));
        sendButton.setBackground(new Color(70, 130, 180)); // Azul suave
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        sendButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sendButton.setOpaque(true);
        sendButton.setUI(new BasicButtonUI()); // Remover borda do botão padrão

        sendButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                sendButton.setBackground(new Color(100, 150, 200)); // Cor ao passar o mouse
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                sendButton.setBackground(new Color(70, 130, 180)); // Cor original
            }
        });

        // Botão "Sair"
        exitButton.setFont(new Font("Arial", Font.PLAIN, 14));
        exitButton.setBackground(new Color(220, 20, 60)); // Vermelho suave
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitButton.setOpaque(true);
        exitButton.setUI(new BasicButtonUI()); // Remover borda do botão padrão

        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                exitButton.setBackground(new Color(255, 99, 71)); // Cor ao passar o mouse
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                exitButton.setBackground(new Color(220, 20, 60)); // Cor original
            }
        });

        // Botão "Excluir"
        deleteUserButton.setFont(new Font("Arial", Font.PLAIN, 14));
        deleteUserButton.setBackground(new Color(220, 20, 60));
        deleteUserButton.setForeground(Color.WHITE);
        deleteUserButton.setFocusPainted(false);
        deleteUserButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        deleteUserButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteUserButton.setOpaque(true);
        deleteUserButton.setUI(new BasicButtonUI()); // Remover borda do botão padrão

        // Caixa de entrada (input)
        inputField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        inputField.setBackground(new Color(245, 245, 245)); // Cor de fundo suave
        inputField.setForeground(Color.BLACK);
        inputField.setCaretColor(Color.BLACK);
        inputField.setPreferredSize(new Dimension(350, 30));
        inputField.setFocusTraversalKeysEnabled(false);
    }

    // Renderer para personalizar a exibição das mensagens na lista
    private static class MessageRenderer extends JPanel implements ListCellRenderer<Message> {
        private JLabel messageText;  // Texto da mensagem
        private JLabel userLabel; // Nome do usuário
        private Client client; // Referência ao Client

        public MessageRenderer(Client client) {
            this.client = client; // Inicializa o cliente
            setLayout(new BorderLayout(10, 5)); // Layout para organizar os componentes

            // Inicializa os componentes
            userLabel = new JLabel();
            userLabel.setFont(new Font("Arial", Font.BOLD, 12)); // Nome do usuário em negrito

            messageText = new JLabel();
            messageText.setFont(new Font("Arial", Font.PLAIN, 14)); // Texto da mensagem

            // Adiciona os componentes ao painel
            add(userLabel, BorderLayout.NORTH);  // Nome do remetente no topo
            add(messageText, BorderLayout.CENTER); // Mensagem no centro

            // Define a borda sutil
            setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Borda interna suave
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Message> list, Message message, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            try {
                // Configura o texto do usuário e da mensagem
                userLabel.setText(message.toString().split("&")[0]);
                messageText.setText(message.toString().split("&")[2]); // Permite formatação HTML

                // Alinha e define as cores de fundo dependendo do remetente
                String currentUserEmail = client.email;
                if (Objects.equals(message.toString().split("&")[1], currentUserEmail)) {
                    setBackground(new Color(230, 245, 255)); // Mensagens do usuário logado com fundo suave
                    setLayout(new FlowLayout(FlowLayout.LEFT)); // Alinha à esquerda
                } else {
                    setBackground(Color.WHITE); // Mensagens de outros usuários
                    setLayout(new FlowLayout(FlowLayout.RIGHT)); // Alinha à direita
                }

                // Se a célula estiver selecionada, altere a cor de fundo
                if (isSelected) {
                    setBackground(new Color(200, 220, 240)); // Cor de fundo ao selecionar
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return this;
        }
    }
}
