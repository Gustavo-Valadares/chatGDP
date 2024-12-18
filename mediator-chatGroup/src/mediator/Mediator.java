package mediator;

import controllers.MessageController;
import controllers.UserController;
import entities.User;

public class Mediator {
    public static boolean sendMessage(String nome, String email, String text) {
        return MessageController.createMessage(nome, text, email);
    }

    public static boolean signIn(String email, String senha){
        return UserController.signIn(email, senha);
    }

    public static boolean signUp(String nome, String senha, String email){
        return UserController.signUp(nome, senha, email);
    }

    public static boolean remove(String email){
        return UserController.remove(email);
    }

    public static User findByEmail(String email) {
        return UserController.findUserByEmail(email);
    }

}
