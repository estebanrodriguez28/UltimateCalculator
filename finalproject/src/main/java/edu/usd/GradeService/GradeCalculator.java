package edu.usd.GradeService;
import edu.usd.Calculator;

// Extend calculator to calculate expected grade
abstract public class GradeCalculator extends Calculator {

    private Subject subject;

    public GradeCalculator(Subject subject) {
        this.subject = subject;
    }

    public String calculate(double desiredGrade) {
        // Implementation of grade calculation
        return subject.calculateFinalGrade(desiredGrade);
    }

}
