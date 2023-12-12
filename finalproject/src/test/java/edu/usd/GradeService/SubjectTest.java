import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectTest {

    private Subject subject;
    private List<GradeComponent> components;

    @BeforeEach
    public void setup() {
        components = new ArrayList<>();
        components.add(new GradeComponent("quiz", 90, 40)); // (title, grade, weight)
        components.add(new GradeComponent("homework", 80, 10));
        components.add(new GradeComponent("final", 80, 50));
        subject = new Subject("Computer Science", components);
    }

    @Test
    public void testCalculateFinalGrade() {
        double expectedGrade = (90 * 0.4) + (80 * 0.1) + (0.8 * 0.5);
        assertEquals(expectedGrade, subject.calculateFinalGrade());
    }

    @Test
    public void testGetSetName() {
        subject.setName("Biology");
        assertEquals("Biology", subject.getName());
    }

    @Test
    public void testGetComponents() {
        assertEquals(components, subject.getComponents());
    }
}
