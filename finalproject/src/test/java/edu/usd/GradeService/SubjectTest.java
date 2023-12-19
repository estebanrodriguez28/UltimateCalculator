package edu.usd.GradeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectTest {

    private Subject subject;

    @BeforeEach
    public void setup() {
        subject = new Subject("Computer Science");
    }

    @Test
    public void addComponentTest() {
        subject.addComponent(new GradeComponent("quiz", 30));
        subject.addComponent(new GradeComponent("homework", 10));
        assertEquals(2, subject.getComponents().size());
    }

    @Test
    public void addAssignmentTest() {
        subject.addComponent(new GradeComponent("quiz", 30));
        assertThrows(IllegalArgumentException.class, () -> {
            subject.addAssignment("homework", 92);
        });
    }

    @Test
    public void calculateFinalGradeTest() {
        subject.addComponent(new GradeComponent("homework", 50));
        subject.addAssignment("homework", 100);
        subject.addAssignment("homework", 0);
        assertEquals("100.00%", subject.calculateFinalGrade(75));
    }

    @Test
    public void calculateFinalGradeFailTest() {
        subject.addComponent(new GradeComponent("homework", 50));
        subject.addComponent(new GradeComponent("quiz", 40));
        subject.addComponent(new GradeComponent("midterm", 30));
        subject.addAssignment("homework", 92);
        subject.addAssignment("quiz", 87);
        assertEquals("Total weight over 100", subject.calculateFinalGrade(100));
    }

    @Test
    public void getSetNameTest() {
        subject.setName("Math");
        assertEquals("Math", subject.getName());
    }

}
