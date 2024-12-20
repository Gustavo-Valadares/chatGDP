package Test;

import controllers.UserController;
import entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @BeforeEach
    void setup() {
        UserController.userLogged = null; // Limpa o usuário logado antes de cada teste
    }

    @Test
    void testSignUp() {
        boolean result = UserController.signUp("Alice", "password123", "alice@example.com");
        assertTrue(result, "O cadastro deveria ser bem-sucedido.");
    }

    @Test
    void testSignUpWithExistingEmail() {
        UserController.signUp("Bob", "password123", "bob@example.com");
        boolean result = UserController.signUp("Bob2", "password456", "bob@example.com");
        assertFalse(result, "O cadastro deveria falhar devido a email já existente.");
    }

    @Test
    void testSignIn() {
        UserController.signUp("Charlie", "pass123", "charlie@example.com");
        boolean result = UserController.signIn("charlie@example.com", "pass123");
        assertTrue(result, "O login deveria ser bem-sucedido.");
    }

    @Test
    void testSignInWithWrongPassword() {
        UserController.signUp("Dave", "pass123", "dave@example.com");
        boolean result = UserController.signIn("dave@example.com", "wrongpass");
        assertFalse(result, "O login deveria falhar devido à senha incorreta.");
    }

    @Test
    void testRemoveUser() {
        UserController.signUp("Eve", "pass123", "eve@example.com");
        boolean result = UserController.remove("eve@example.com");
        assertTrue(result, "A remoção deveria ser bem-sucedida.");
    }
}
