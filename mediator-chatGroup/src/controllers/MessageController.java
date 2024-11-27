package controllers;

import entities.Message;
import structData.MessageList;

public class MessageController {
    public static void createMessage(String text) {
        Message newMessage = new Message(text);
        MessageList.add(newMessage);
    }

    public static void printMessages() {
        MessageList.showMessages();
    }
}
