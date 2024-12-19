package mediator;

import entities.User;

public interface Mediator {
    boolean sendMessage(String nome, String email, String text);

    boolean signIn(String email, String senha);

    boolean signUp(String nome, String senha, String email);

    boolean remove(String email);

    User findByEmail(String email);
}
