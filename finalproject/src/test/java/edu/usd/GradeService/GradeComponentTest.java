package edu.usd.GradeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class GradeComponentTest {

    private GradeComponent gradeComponent;

    @BeforeEach
    public void setup() {
        gradeComponent = new GradeComponent("quiz", 50);
    }

    @Test
    public void addAssignmentTest() {
        gradeComponent.addAssignment(85);
        gradeComponent.addAssignment(92);
        assertEquals(2, gradeComponent.getAssignmentLength());
    }

    @Test
    public void calculateComponentGradeTest() {
        gradeComponent.addAssignment(80);
        gradeComponent.addAssignment(90);
        double expectedGrade = ((80 + 90) / 2) * 50;
        assertEquals(expectedGrade, gradeComponent.calculateComponentGrade());
    }

    @Test
    public void getWeightTest() {
        assertEquals(50, gradeComponent.getWeight());
    }

    @Test
    public void getNameTest() {
        assertEquals("quiz", gradeComponent.getName());
    }


}
