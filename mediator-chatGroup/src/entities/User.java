package entities;

import java.util.Objects;

public class User {
    private String nome;
    private String senha;
    private String email;
    private static int qtddUsuarios = 1;
    private final int id;

    // Construtor
    public User(String nome, String senha, String email) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.id = qtddUsuarios++;
    }

    // Getters
    public static int getQtddUsuarios() {
        return qtddUsuarios;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public int getId() {
        return this.id;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Método toString para salvar no arquivo
    @Override
    public String toString() {
        return  this.getNome() + "," + this.getEmail() + "," + this.getSenha();
    }

    // Sobrescrevendo equals para comparar usuários corretamente
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Mesmo objeto na memória
        if (o == null || getClass() != o.getClass()) return false; // Classes diferentes
        User user = (User) o;
        // Compara os atributos email e senha (ou outros atributos relevantes)
        return Objects.equals(email, user.email) && Objects.equals(senha, user.senha);
    }

    // Sobrescrevendo hashCode para garantir consistência com equals
    @Override
    public int hashCode() {
        return Objects.hash(email, senha);
    }
}
