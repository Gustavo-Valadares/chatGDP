package mediator;

import controllers.MessageController;
import controllers.UserController;
import entities.User;

public class ConcreteMediator implements Mediator {
    @Override
    public boolean sendMessage(String nome, String email, String text) {
        return MessageController.createMessage(nome, text, email);
    }

    @Override
    public boolean signIn(String email, String senha) {
        return UserController.signIn(email, senha);
    }

    @Override
    public boolean signUp(String nome, String senha, String email) {
        return UserController.signUp(nome, senha, email);
    }

    @Override
    public boolean remove(String email) {
        return UserController.remove(email);
    }

    @Override
    public User findByEmail(String email) {
        return UserController.findUserByEmail(email);
    }
}
