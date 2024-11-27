package entities;
import controllers.UserController;

public class Message {
    private final String text;
    private final User remetente;

    public Message(String text){
        this.text = text;
        this.remetente = UserController.getUserLogged();
    }

    @Override
    public String toString() {
        return remetente.getNome() + ": " + this.text;
    }
}
