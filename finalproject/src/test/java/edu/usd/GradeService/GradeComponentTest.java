import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class GradeComponentTest {

    private GradeComponent gradeComponent;

    @BeforeEach
    public void setup() {
        gradeComponent = new GradeComponent("quiz", 90, 40)
    }

    @Test
    public void getTypeTest() {
        assertEquals("quiz", subject.getType());
    }

    @Test
    public void getGradeTest() {
        assertEquals(90, subject.getGrade());
    }

    @Test
    public void getWeightTest() {
        assertEquals(40, subject.getWeight());
    }

}
