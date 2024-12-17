package mediator;

import controllers.MessageController;

public class Mediator {
    public static boolean sendMessage(String nome, String text, String email) {
        return MessageController.createMessage(nome, text, email);
    }

}
