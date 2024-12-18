package server;

import java.io.*;
import java.net.*;

public class Client implements AutoCloseable {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    public String email;

    public Client(String host, int port, String email) throws IOException {
        socket = new Socket(host, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.email = email;
    }

    public String sendMessage(String message) throws IOException {
        out.println(message); // Envia a mensagem para o servidor
        return in.readLine(); // Lê a resposta do servidor
    }

    // Método para fechar a conexão manualmente quando o botão de sair for clicado
    public void closeConnection() throws IOException {
        if (out != null) out.close();
        if (in != null) in.close();
        if (socket != null) socket.close();
    }

    @Override
    public void close() throws IOException {
        closeConnection(); // Implementa o método AutoCloseable, mas não o chama automaticamente
    }
}
