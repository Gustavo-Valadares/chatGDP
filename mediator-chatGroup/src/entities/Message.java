package entities;
import controllers.UserController;

public class Message {
    private final String text;
    private final User remetente;

    public Message(String text){
        this.text = text;
        this.remetente = UserController.getUserLogged();
    }

    public User getRemetente() {
        return remetente;
    }

    @Override
    public String toString() {
        return  this.text;
    }
}
