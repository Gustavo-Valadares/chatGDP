package server;

import entities.User;
import mediator.ConcreteMediator;
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    public ConcreteMediator concreteMediator = new ConcreteMediator();

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            String message;
            while ((message = input.readLine()) != null) {
                System.out.println("Mensagem recebida: " + message);
                String response = handleRequest(message);
                output.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeConnections(); // Assegura que as conexões são fechadas após a execução
        }
    }

    private String handleRequest(String request) {
        String[] parts = request.split(":");
        String command = parts[0];
        String[] params = parts.length > 1 ? parts[1].split(",") : new String[0];

        switch (command) {
            case "signin":
                if (params.length >= 2) {
                    return concreteMediator.signIn(params[0], params[1]) ? "Login bem-sucedido" : "Falha no login";
                } else {
                    return "Parâmetros inválidos para login";
                }
            case "signup":
                if (params.length >= 3) {
                    return concreteMediator.signUp(params[0], params[1], params[2]) ? "Cadastro bem-sucedido" : "Falha no cadastro";
                } else {
                    return "Parâmetros inválidos para cadastro";
                }
            case "sendMessage":
                if (params.length >= 3) {
                    return concreteMediator.sendMessage(params[0], params[1], params[2]) ? "Mensagem enviada" : "Falha ao enviar mensagem";
                } else {
                    return "Parâmetros inválidos para envio de mensagem";
                }
            case "findEmail":
                System.out.println("testando print");
                if (params.length >= 1) {
                    User user = concreteMediator.findByEmail(params[0]);
                    return user != null ? user.getNome() + "," + user.getEmail() : "Usuário não encontrado";
                } else {
                    return "Parâmetros inválidos para buscar usuário";
                }
            case "removeUser":
                if (params.length >= 1) {
                    return concreteMediator.remove(params[0]) ? "Usuário removido com sucesso" : "Falha ao remover usuário";
                } else {
                    return "Parâmetros inválidos para remoção de usuário";
                }
            default:
                return "Comando desconhecido";
        }
    }

    private void closeConnections() {
        try {
            if (input != null) input.close();
            if (output != null) output.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
