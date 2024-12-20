package Test;

import controllers.MessageController;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MessageControllerTest {
    @Test
    void testCreateMessage() {
        boolean result = MessageController.createMessage("John Doe", "Hello World", "john.doe@example.com");
        assertTrue(result, "A mensagem deveria ser criada com sucesso.");
    }
}