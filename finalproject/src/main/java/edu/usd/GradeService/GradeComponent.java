package edu.usd.GradeService;

import java.util.ArrayList;
import java.util.List;

public class GradeComponent {
    private String name;
    private double weight;
    //    private ArrayList assignments;
    private List<Double> assignments; // all assignments in component


    public GradeComponent(String name, double weight) {
        this.name = name;
        this.weight = weight;
        this.assignments = new ArrayList<>();
    }

    // Add grade within component
    public void addAssignment(double assignment) {
        this.assignments.add(assignment);
    }

    public double calculateComponentGrade() {
        double sum = 0;
        for (Double assignment: assignments) {
            sum += assignment;
        }
        // component average * weight
        return (sum / assignments.size()) * this.weight;
    }

    public double getWeight() {
        return this.weight;
    }

    public String getName() {
        return this.name;
    }

    public int getAssignmentLength() {
        return this.assignments.size();
    }
}
