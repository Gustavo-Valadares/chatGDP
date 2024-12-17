package controllers;
import entities.User;
import structData.UserList;
import java.util.Objects;
import java.util.Scanner;

public class UserController {
    private static User userLogged;

    public static User getUserLogged() {
        return userLogged;
    }

    public static void setUserLogged(User userLogged) {
        UserController.userLogged = userLogged;
    }

    private static boolean createUser(String nome, String senha, String email) {
        System.out.println(nome + " " + email + " " + senha);
        User newUser = new User(nome, senha, email);
        UserList.add(newUser);
        return true;
    }

    public static void printUsers(){
        UserList.printUsers();
    }

    public static User findUserById(int id) {
        return UserList.findUserById(id);
    }

    public static User findUserByEmail(String email) {
        return UserList.findUserByEmail(email);
    }



    public static boolean signUp(String nome, String senha, String email) {
        User userExist = findUserByEmail(email);
        if(userExist != null) {
            return false;
        }
       return createUser(nome, senha, email);


    }

    public static boolean signIn(String email, String senha) {
        User userExist = findUserByEmail(email);
        if(userExist == null) {
            return false;
        }
        if(Objects.equals(userExist.getEmail(), email) && Objects.equals(userExist.getSenha(), senha) ) {
            setUserLogged(userExist);
            return true;
        } else {
            return false;
        }
    }
}
