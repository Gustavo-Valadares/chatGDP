package controllers;

import entities.Message;
import structData.MessageList;

public class MessageController {
    public static boolean createMessage(String nome, String text, String email) {
        Message newMessage = new Message(nome,text, email);
        return MessageList.add(newMessage);
    }


}
