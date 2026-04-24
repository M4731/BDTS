import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GradeCheckTest {

    // T1 - PICAT (average < 5 si attendance < 50)
    @Test
    void T1_picat() {
        int[] grades = {3, 4, 5};
        assertEquals("PICAT", GradeCheck.evaluateStudent(grades, 40, false));
    }

    // T2 - BURSA (toate condițiile indeplinite)
    @Test
    void T2_bursa() {
        int[] grades = {10, 10, 9};
        assertEquals("BURSA", GradeCheck.evaluateStudent(grades, 95, true));
    }

    // T3 - TRECUT (caz normal)
    @Test
    void T3_trecut() {
        int[] grades = {6, 7, 8};
        assertEquals("TRECUT", GradeCheck.evaluateStudent(grades, 70, false));
    }

    // T4 - TRECUT (identic logic, difera doar response in PPT)
    @Test
    void T4_trecut() {
        int[] grades = {6, 7, 8};
        assertEquals("TRECUT", GradeCheck.evaluateStudent(grades, 70, false));
    }

    // T5 - TRECUT (identic logic, difera doar response in PPT)
    @Test
    void T5_trecut() {
        int[] grades = {5, 7, 8};
        assertEquals("TRECUT", GradeCheck.evaluateStudent(grades, 66, false));
    }

    // T6 - PICAT (attendance < 50)
    @Test
    void T6_picat_attendance() {
        int[] grades = {5, 6, 6};
        assertEquals("PICAT", GradeCheck.evaluateStudent(grades, 40, false));
    }

    // T7 - TRECUT (attendance < 90 → nu BURSA)
    @Test
    void T7_trecut_attendance() {
        int[] grades = {10, 10, 10};
        assertEquals("TRECUT", GradeCheck.evaluateStudent(grades, 80, true));
    }

    // T8 - TRECUT (bonus = false → nu BURSA)
    @Test
    void T8_trecut_bonus() {
        int[] grades = {10, 10, 10};
        assertEquals("TRECUT", GradeCheck.evaluateStudent(grades, 95, false));
    }

    @Test
    void n_0() {
        int[] grades = {};
        assertEquals("PICAT", GradeCheck.evaluateStudent(grades, 40, false));
    }
}