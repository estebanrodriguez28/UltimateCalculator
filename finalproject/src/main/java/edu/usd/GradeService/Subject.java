package edu.usd.GradeService;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private String name;
    private List<GradeComponent> components; // homework, essays, midterm, etc.
    private double totalWeight;

    public Subject(String name) {
        this.name = name;
        this.components = new ArrayList<>();
        this.totalWeight = 0;
    }

    // Add grade components to subject
    public void addComponent(GradeComponent component) {
        System.out.println("Adding component: " + component.getName());
        this.totalWeight += component.getWeight();
        if (!components.contains(component)) {
            components.add(component);
        }
    }

    // Add assignments to components within subject
    public void addAssignment(String componentName, double grade) {
        System.out.println("Adding assignment for: " + componentName + "Grade: " + grade);
        for (GradeComponent component : components) {
            if (component.getName().equals(componentName)) {
                component.addAssignment(grade);
                return;
            }
        }
        throw new IllegalArgumentException("Component not found: " + componentName);
    }

    public String calculateFinalGrade(double desiredGrade) {

        if (this.totalWeight > 100) {
            return "Total weight over 100";
        }

        System.out.println("Remaining weight: " + (100-this.totalWeight));

        double sum = 0;
        for (GradeComponent component : components) {
            System.out.println("Component: " + component.getName() + "Total: " + component.calculateComponentGrade());
            sum += component.calculateComponentGrade();
        }
        double currentGrade = sum / this.totalWeight;

        System.out.println("Current Grade: " + currentGrade);

        double requiredGrade = (desiredGrade - currentGrade * (this.totalWeight / 100)) / ((100-this.totalWeight) / 100);

        return String.format("%.2f%%", requiredGrade);

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
