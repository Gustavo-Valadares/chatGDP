package mediator;

import controllers.MessageController;

public class Mediator {
    public static void sendMessage(String text) {
        MessageController.createMessage(text);
    }

}
