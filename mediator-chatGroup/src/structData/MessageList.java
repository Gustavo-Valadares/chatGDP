package structData;

import entities.Message;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class MessageList {

    private static final String FILE_NAME = "messages.txt";
    private static DefaultListModel<Message> listModel = new DefaultListModel<>();

    static {
        loadMessagesFromFile(); // Carrega mensagens do arquivo ao inicializar a classe
    }

    // Adiciona uma mensagem ao arquivo e ao listModel
    public static boolean add(Message m) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(m.toString());
            writer.newLine();
            listModel.addElement(m); // Atualiza o modelo diretamente
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    // Retorna o DefaultListModel para a interface
    public static DefaultListModel<Message> getListModel() {
        return listModel;
    }

    // Carrega mensagens do arquivo para o listModel
    private static void loadMessagesFromFile() {
        listModel.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("&");
                if (parts.length == 3) {
                    String nome = parts[0];
                    String email = parts[1];
                    String text = parts[2];
                    Message message = new Message(nome,text, email);
                    System.out.println(message);
                    listModel.addElement(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
