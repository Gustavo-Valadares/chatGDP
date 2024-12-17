package entities;
import controllers.UserController;

public class Message {
    private final String text;
    private final User remetente;
    private final String nomeRemetente;
    private final String emailRemetente;

    public Message(String nome, String text, String email){
        this.text = text;
        this.remetente = UserController.findUserByEmail(email);
        this.nomeRemetente = nome;
        this.emailRemetente = email;
    }

    public User getRemetente() {
        return remetente;
    }

    @Override
    public String toString() {
        String nome = (remetente == null  ? nomeRemetente: remetente.getNome());
        String email = (remetente == null  ? emailRemetente : remetente.getEmail());
        return  nome + "&" + email + "&" + this.text;
    }
}
