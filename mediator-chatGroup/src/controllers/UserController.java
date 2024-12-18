package controllers;
import entities.User;
import structData.UserList;
import java.util.Objects;

public class UserController {
    public static User userLogged;

    public static User getUserLogged() {
        return userLogged;
    }

    public static void setUserLogged(User userLogged) { UserController.userLogged = userLogged;
        System.out.println("setou userLogged");
    }

    private static void createUser(String nome, String senha, String email) {
        User newUser = new User(nome, senha, email);
        UserList.add(newUser);
        setUserLogged(newUser);

    }

    public static void printUsers(){
        UserList.printUsers();
    }



    public static User findUserByEmail(String email) {
        return UserList.findUserByEmail(email);
    }



    public static boolean signUp(String nome, String senha, String email) {
        User userExist = findUserByEmail(email);
        if(userExist != null) {
            return false;
        }
        createUser(nome, senha, email);
        System.out.println(getUserLogged().getNome());
        return true;



    }

    public static boolean signIn(String email, String senha) {
        User userExist = findUserByEmail(email);
        if(userExist == null) {
            return false;
        }
        if(Objects.equals(userExist.getEmail(), email) && Objects.equals(userExist.getSenha(), senha) ) {
            setUserLogged(userExist);
            System.out.println("setou usuario na hora de logar com email: " + " " + getUserLogged().getEmail() + " senha: " + getUserLogged().getSenha());
            return true;
        } else {
            return false;
        }
    }

    public static boolean remove(String email) {
        return UserList.remove(email);

    }

}
