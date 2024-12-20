package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado na porta " + PORT);

            while (true) {
                // Aceita as conexões de clientes
                Socket clientSocket = serverSocket.accept();
                System.out.println("Novo cliente conectado: " + clientSocket.getInetAddress());

                // Cria uma nova thread para cada cliente
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start(); // Inicia a thread manualmente
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
