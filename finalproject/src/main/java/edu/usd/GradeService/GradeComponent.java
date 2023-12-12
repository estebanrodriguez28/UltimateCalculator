public class GradeComponent {
    private String type;
    private double weight;
    private double grade;
    private ArrayList assignments;

    public GradeComponent(String type, double grade, double weight) {
        this.type = type;
        this.grade = grade;
        this.weight = weight;
        this.assignments = new ArrayList<Double>();
    }

    // Add grade within component
    public void addAssignment(double assignment) {
        this.assignments.add(assignment);
    }

    public String getType() {
        return this.type;
    }

    public double getGrade() {
        return this.grade;
    }

    public String getWeight() {
        return this.weight;
    }
}
