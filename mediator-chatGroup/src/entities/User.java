package entities;

public class User {
    private String nome;
    private String senha;
    private String email;
    private static int qtddUsuarios = 1;
    private final int id;


    public User(String nome, String senha, String email) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.id = qtddUsuarios++;
    }

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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Id: " + this.getId() + "\nNome: " + this.getNome() + "\n";
    }
}
