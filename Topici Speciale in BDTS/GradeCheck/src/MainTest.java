import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    void testResponse_y() {
        assertTrue(Main.shouldContinue('y'));
    }

    @Test
    void testResponse_Y() {
        assertTrue(Main.shouldContinue('Y'));
    }

    @Test
    void testResponse_n() {
        assertFalse(Main.shouldContinue('n'));
    }

    @Test
    void testResponse_other() {
        assertFalse(Main.shouldContinue('x'));
    }
}