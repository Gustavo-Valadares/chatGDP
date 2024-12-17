package structData;

import entities.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UserList {

    private static final String FILE_NAME = "users.txt";

    // Adiciona usuário ao arquivo
    public static void add(User u) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(u.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean remove(String email) {
        User userRemove = findUserByEmail(email);
        List<User> users = readUsersFromFile();
        users.remove(userRemove);
        System.out.println(users);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
                for (User user : users) {
                    writer.write(user.toString());
                    writer.newLine();
                }
                return true;

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

    }

    // Imprime todos os usuários do arquivo
    public static void printUsers() {
        List<User> users = readUsersFromFile();
        users.forEach(user -> System.out.println(user.toString()));
    }


    // Busca usuário pelo Email
    public static User findUserByEmail(String email) {
        List<User> users = readUsersFromFile();
        for (User user : users) {
            if (Objects.equals(user.getEmail(), email)) {
                return user;
            }
        }
        return null;
    }

    // Lê todos os usuários do arquivo e os retorna como uma lista
    private static List<User> readUsersFromFile() {
        List<User> userList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) { // Certifica-se de que a linha tem 4 partes (ID, Nome, Email, Senha)
                    String name = parts[0];
                    String email = parts[1];
                    String password = parts[2];
                    userList.add(new User(name, password, email));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
