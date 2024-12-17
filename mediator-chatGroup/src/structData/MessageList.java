package structData;

import entities.Message;

import javax.swing.*;
import java.util.ArrayList;

public class MessageList {
    private static ArrayList<Message> messageList = new ArrayList<>();
    private static DefaultListModel<Message> listModel = new DefaultListModel<>();

    public static void add(Message m) {
        messageList.add(m);
        listModel.addElement(m); // Atualiza o modelo diretamente
    }

    public static DefaultListModel<Message> getListModel() {
        return listModel; // Retorna o modelo para a interface
    }
}
