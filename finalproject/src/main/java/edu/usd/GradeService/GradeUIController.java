package edu.usd.GradeService;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.Node;
import java.util.*;

public class GradeUIController {

    @FXML
    private TabPane ClassesTabs;

    @FXML
    private TextField addClassText;

    @FXML
    protected void onAddTabClick() {
        Map<String, String> tabSpecificGroups = new LinkedHashMap<>();

        // Group management segment
        GridPane groupGrid = new GridPane();
        groupGrid.add(new Label("Component Type"), 0, 0);
        groupGrid.add(new Label("Component Weight %"), 1, 0);
        addGroupRow(groupGrid, 1, tabSpecificGroups);

        // Assignment grid
        GridPane assignmentsGrid = new GridPane();
        assignmentsGrid.add(new Label("Assignment Name"), 0, 0);
        assignmentsGrid.add(new Label("Grade %"), 1, 0);
        assignmentsGrid.add(new Label("Group"), 2, 0);
        addAssignmentRow(assignmentsGrid, 1, tabSpecificGroups);

        // Button to add new assignments
        Button addAssignmentButton = new Button("Add Assignment");
        addAssignmentButton.setOnAction(e -> addAssignmentRow(assignmentsGrid, assignmentsGrid.getRowCount() + 1, tabSpecificGroups));

        // Desired grade input and calculate button
        HBox hbox = new HBox(10);
        TextField desiredGradeInput = new TextField();
        desiredGradeInput.setPromptText("Desired Grade");
        Label calculatedGradeLabel = new Label();
        Button calculateButton = new Button("Calculate");
        calculateButton.setOnAction(e -> calculateNeededGrade(assignmentsGrid, groupGrid, desiredGradeInput, calculatedGradeLabel));
        hbox.getChildren().addAll(desiredGradeInput, calculateButton, calculatedGradeLabel);

        // Creating VBox to hold all elements and ScrollPane for the entire view
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(groupGrid, assignmentsGrid, addAssignmentButton, hbox);
        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true);

        // Creating and adding new tab
        Tab newTab = new Tab(addClassText.getText(), scrollPane);
        ClassesTabs.getTabs().add(newTab);
    }

    private void addGroupRow(GridPane groupGrid, int rowNum, Map<String, String> groups) {
        TextField groupNameField = new TextField();
        TextField groupWeightField = new TextField();
        Button addGroupButton = new Button("Add Component");
        addGroupButton.setOnAction(e -> {
            String groupName = groupNameField.getText();
            String groupWeight = groupWeightField.getText();
            groups.put(groupName, groupWeight);

            // Refreshing the assignment grid to update the dropdowns
            VBox vbox = (VBox) groupGrid.getParent();
            GridPane assignmentsGrid = (GridPane) vbox.getChildren().get(1);
            updateAssignmentGrid(assignmentsGrid, groups);

            // Adding new group row
            addGroupRow(groupGrid, groupGrid.getRowCount() + 1, groups);
        });

        groupGrid.add(groupNameField, 0, rowNum);
        groupGrid.add(groupWeightField, 1, rowNum);
        groupGrid.add(addGroupButton, 2, rowNum);
    }

    private void updateAssignmentGrid(GridPane assignmentsGrid, Map<String, String> groups) {
        // Clear and add new rows with updated group ComboBox
        assignmentsGrid.getChildren().clear();
        assignmentsGrid.add(new Label("Assignment Name"), 0, 0);
        assignmentsGrid.add(new Label("Grade %"), 1, 0);
        assignmentsGrid.add(new Label("Group"), 2, 0);
        addAssignmentRow(assignmentsGrid, 1, groups);
//        for (int i = 1; i <= groups.size(); i++) {
//            addAssignmentRow(assignmentsGrid, i, groups);
//        }
    }

    private void addAssignmentRow(GridPane grid, int rowNum, Map<String, String> groups) {
        grid.add(new TextField(), 0, rowNum);
        grid.add(new TextField(), 1, rowNum);

        ComboBox<String> groupComboBox = new ComboBox<>();
        groupComboBox.getItems().addAll(groups.keySet());
        grid.add(groupComboBox, 2, rowNum);
    }

    private String getTabName() {
        Tab selectedTab = ClassesTabs.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            return selectedTab.getText();
        }
        return null;
    }

    private void calculateNeededGrade(GridPane assignmentsGrid, GridPane groupGrid, TextField desiredGradeInput, Label calculatedGradeLabel) {

        // Create subject class for tab
        Subject subject = new Subject(getTabName());

        // Add each grade component to subject
        for (int i = 1; i < groupGrid.getRowCount(); i++) {
            TextField componentNameField = null;
            TextField componentWeightField = null;

            for (Node node : groupGrid.getChildren()) {
                Integer rowIndex = GridPane.getRowIndex(node);
                Integer colIndex = GridPane.getColumnIndex(node);

                if (rowIndex != null && rowIndex == i) {
                    if (colIndex != null) {
                        switch (colIndex) {
                            case 0:
                                componentNameField = (TextField) node;
                                break;
                            case 1:
                                componentWeightField = (TextField) node;
                                break;
                        }
                    }
                }
            }

            // If both fields have values
            if (componentNameField != null && componentWeightField != null && !componentNameField.getText().isEmpty()) {

                String componentName = componentNameField.getText();
                double componentWeight = Double.parseDouble(componentWeightField.getText());

                // Create component and add to subject
                GradeComponent component = new GradeComponent(componentName, componentWeight);
                System.out.println(component.getName());
                subject.addComponent(component);

            }
        }

        // Add each assignment to its component
        for (int i = 1; i < assignmentsGrid.getRowCount(); i++) { // Starting from 1 to skip header
            TextField assignmentNameField = null;
            TextField assignmentGradeField = null;
            ComboBox<String> groupComboBox = null;

            for (Node node : assignmentsGrid.getChildren()) {
                Integer rowIndex = GridPane.getRowIndex(node);
                Integer colIndex = GridPane.getColumnIndex(node);

                if (rowIndex != null && rowIndex == i) {
                    if (colIndex != null) {
                        switch (colIndex) {
                            case 0:
                                assignmentNameField = (TextField) node;
                                break;
                            case 1:
                                assignmentGradeField = (TextField) node;
                                break;
                            case 2:
                                groupComboBox = (ComboBox<String>) node;
                                break;
                        }
                    }
                }
            }

            // If all fields filled in
            if (assignmentNameField != null && assignmentGradeField != null && groupComboBox != null) {
                String selectedGroup = groupComboBox.getValue();
                double assignmentGrade = Double.parseDouble(assignmentGradeField.getText());

                // Add assignment to selected component group
                subject.addAssignment(selectedGroup, assignmentGrade);
            }
        }

        double desiredGrade = Double.parseDouble(desiredGradeInput.getText());

        String requiredGrade = subject.calculateFinalGrade(desiredGrade);

        calculatedGradeLabel.setText("Grade Needed On Final: " + requiredGrade);

    }
}
