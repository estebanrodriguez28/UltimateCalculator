import java.util.List;

public class Subject {
    private String name;
    private List<GradeComponent> components; // homework, essays, midterm, etc.

    public Subject(String name, List<GradeComponent> components) {
        this.name = name;
        this.components = components;
    }

    public double calculateFinalGrade() {
        double finalGrade = 0.0;

        for (GradeComponent component : components) {

            for (Double assignment: component.)

            double totalGrade = component.getGrade(); // return total
            double weight = component.getWeight(); // double represents a percent
            finalGrade += totalGrade * (weight / 100);
        }

        return finalGrade;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GradeComponent> getComponents() {
        return this.components;
    }

}
