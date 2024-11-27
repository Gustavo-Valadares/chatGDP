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

    public static void createUser(String nome, String senha, String email) {
        User newUser = new User(nome, senha, email);
        UserList.add(newUser);
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



    public static void signUp(String nome, String senha, String email) {
        Scanner sc = new Scanner(System.in);
        User userExist = findUserByEmail(email);
        if(userExist != null) {
            System.out.println("\n\nUsuário já cadastrado");
            return;
        }
        createUser(nome, senha, email);
        System.out.println("\nUsuário cadastrado com sucesso");
        System.out.println("Aperte enter para retornar e faça o login");
        sc.nextLine();

    }

    public static boolean signIn(String email, String senha) {
        Scanner sc = new Scanner(System.in);
        User userExist = findUserByEmail(email);
        if(userExist == null) {
            System.out.println("\nUsuário não encontrado\n\n");
            System.out.println("Aperte Enter para tentar novamente\n");
            sc.nextLine();
            return false;
        }


        if(Objects.equals(userExist.getEmail(), email) && Objects.equals(userExist.getSenha(), senha) ) {
            setUserLogged(userExist);
            return true;
        } else {
            System.out.println("\n\nCredenciais inválidas\n");
            System.out.println("\n\nAperte Enter para tentar novamente\n");
            sc.nextLine();
            return false;
        }
    }
}
