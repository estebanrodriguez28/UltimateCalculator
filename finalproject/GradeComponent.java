public class GradeComponent {
    private String type;
    private double weight;
    private double grade;

    public GradeComponent(String type, double weight, double grade) {
        this.type = type;
        this.weight = weight;
        this.grade = grade;
    }

    public String getWeight() {
        return this.weight;
    }

    public double getGrade() {
        return this.grade;
    }

}
