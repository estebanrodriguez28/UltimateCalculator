// Extend calculator to calculate expected grade
public class GradeCalculator extends Calculator {

    private Subject subject;

    public GradeCalculator(Subject subject) {
        this.subject = subject;
    }

    @Override
    public double calculate() {
        // Implementation of grade calculation
        return subject.calculateFinalGrade();
    }

    // Additional methods related to grade calculation
}
