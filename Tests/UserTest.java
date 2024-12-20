package Test;

import entities.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserEquality() {
        User user1 = new User("George", "pass123", "george@example.com");
        User user2 = new User("George", "pass123", "george@example.com");
        assertEquals(user1, user2, "Dois usuários com os mesmos atributos deveriam ser iguais.");
    }

    @Test
    void testUserToString() {
        User user = new User("Hannah", "secure123", "hannah@example.com");
        String expected = "Hannah,hannah@example.com,secure123";
        assertEquals(expected, user.toString(), "A saída do método toString deveria corresponder ao formato esperado.");
    }
}
