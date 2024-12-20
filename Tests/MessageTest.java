package Test;

import entities.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MessageTest {
    @Test
    void testMessageToString() {
        Message message = new Message("Frank", "Hello!", "frank@example.com");
        String expected = "Frank&frank@example.com&Hello!";
        assertEquals(expected, message.toString(), "A saída do método toString deveria corresponder ao formato esperado.");
    }
}
