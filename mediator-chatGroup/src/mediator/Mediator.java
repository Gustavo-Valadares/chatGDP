package mediator;

import controllers.MessageController;
import controllers.UserController;

public class Mediator {
    public static boolean sendMessage(String nome, String text, String email) {
        return MessageController.createMessage(nome, text, email);
    }

    public boolean signIn(String email, String senha){
        return UserController.signIn(email, senha);
    }

    public boolean signUp(String nome, String senha, String email){
        return UserController.signUp(nome, senha, email);
    }

    public boolean remove(String email){
        return UserController.remove(email);
    }



}
