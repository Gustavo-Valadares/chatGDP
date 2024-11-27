package structData;
import entities.User;
import java.util.ArrayList;
import java.util.Objects;

public class UserList {

    //protected static UserList instance;// padrão sigleton "instance" para instaciamento unico da lista
    protected static ArrayList<User> userList = new ArrayList<>();

    public static void add(User u) {
        userList.add(u);
    }

    public static void printUsers(){
        userList.forEach(user -> {
            System.out.println(user.toString());
        });
    }

    public static User findUserById(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public static User findUserByEmail(String email) {
        for (User user : userList) {
            if (Objects.equals(user.getEmail(), email)) {
                return user;
            }
        }
        return null;
    }

}
