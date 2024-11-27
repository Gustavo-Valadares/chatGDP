package structData;
import entities.Message;
import java.util.ArrayList;

public class MessageList {
    protected static ArrayList<Message> messageList = new ArrayList<>();

    public static void add(Message m){
        messageList.add(m);
    }

    public static void showMessages(){
        if(messageList.isEmpty()) {
            System.out.println("Nenhuma mensagem encontrada até o momento...");
        } else {
            messageList.forEach(message -> {
                System.out.println(message.toString());
            });
        }

    }
}

