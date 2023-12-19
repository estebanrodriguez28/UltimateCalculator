package edu.usd.PhysicsService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PhysicsController {
    private PhysicsCalculatorHandler physicsCalculatorHandler = new PhysicsCalculatorHandler();

    @FXML
    private TextField accelKinematic;

    @FXML
    private Button calculateForce;

    @FXML
    private Button calculateKE;

    @FXML
    private Button calculateKinematic;

    @FXML
    private Button calculatePE;

    @FXML
    private TextField distanceKinematic;

    @FXML
    private TextField finalVKinematic;

    @FXML
    private TextField force;

    @FXML
    private TextField heightPE;

    @FXML
    private TextField initVKinematic;

    @FXML
    private TextField kineticEnergy;

    @FXML
    private TextField massForce;

    @FXML
    private TextField massKE;

    @FXML
    private TextField massPE;

    @FXML
    private TextField potentialEnergy;

    @FXML
    private TextField resultForce;

    @FXML
    private TextField resultKE;

    @FXML
    private TextField resultKinematic;

    @FXML
    private TextField resultPE;

    @FXML
    private TextField timeKinematic;

    @FXML
    private TextField accelForce;

    @FXML
    private TextField velocityKE;

    // Combo Box Initialization
    ObservableList<String> kinematicOptionList = FXCollections.observableArrayList(
            "Initial Velocity", "Final Velocity", "Time", "Acceleration", "Distance");

    ObservableList<String> forceOptionList = FXCollections.observableArrayList(
            "Force", "Mass", "Acceleration");

    ObservableList<String> optionKEList = FXCollections.observableArrayList(
            "Kinetic Energy", "Mass", "Velocity");

    ObservableList<String> optionPEList = FXCollections.observableArrayList(
            "Potential Energy", "Mass", "Height");


    @FXML
    private ComboBox<String> kinematicOption;

    @FXML
    private ComboBox<String> forceOption;

    @FXML
    private ComboBox<String> optionKE;

    @FXML
    private ComboBox<String> optionPE;

    @FXML
    private void initialize() {
        kinematicOption.setValue("What would you like to calculate?");
        kinematicOption.setItems(kinematicOptionList);

        forceOption.setValue("What would you like to calculate?");
        forceOption.setItems(forceOptionList);

        optionKE.setValue("What would you like to calculate?");
        optionKE.setItems(optionKEList);

        optionPE.setValue("What would you like to calculate?");
        optionPE.setItems(optionPEList);
    }

    @FXML
    void onBtnClickF(ActionEvent event) {
        // force, mass, acceleration
        String[] inputs = new String[3];

        inputs[0] = force.getText();
        inputs[1] = massForce.getText();
        inputs[2] = accelForce.getText();

        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i].equals("")) {
                inputs[i] = null;
            } else {
                continue;
            }
        }

        String option = forceOption.getValue();

        try{
            double result = physicsCalculatorHandler.handleCalculation("Force", inputs, option);
            resultForce.setText(Double.toString(result));
        } catch (IllegalArgumentException e) {
            resultForce.setText(e.getMessage());
        }
    }

    @FXML
    void onBtnClickK(ActionEvent event) {
        // initV, finalV, time, accel, dist
        String[] inputs = new String[5];

        inputs[0] = initVKinematic.getText();
        inputs[1] = finalVKinematic.getText();
        inputs[2] = timeKinematic.getText();
        inputs[3] = accelKinematic.getText();
        inputs[4] = distanceKinematic.getText();

        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i].equals("")) {
                inputs[i] = null;
            } else {
                continue;
            }
        }

        String option = kinematicOption.getValue();

        try{
            double result = physicsCalculatorHandler.handleCalculation("Kinematic", inputs, option);
            resultKinematic.setText(Double.toString(result));
        } catch (IllegalArgumentException e) {
            resultKinematic.setText(e.getMessage());
        }
    }

    @FXML
    void onBtnClickKE(ActionEvent event) {
        // Kinetic Energy, mass, velocity
        String[] inputs = new String[3];

        inputs[0] = kineticEnergy.getText();
        inputs[1] = massKE.getText();
        inputs[2] = velocityKE.getText();

        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i].equals("")) {
                inputs[i] = null;
            } else {
                continue;
            }
        }

        String option = optionKE.getValue();

        try{
            double result = physicsCalculatorHandler.handleCalculation("Kinetic Energy", inputs, option);
            resultKE.setText(Double.toString(result));
        } catch (IllegalArgumentException e) {
            resultKE.setText(e.getMessage());
        }
    }

    @FXML
    void onBtnClickPE(ActionEvent event) {
        String[] inputs = new String[3];

        inputs[0] = potentialEnergy.getText();
        inputs[1] = massPE.getText();
        inputs[2] = heightPE.getText();

        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i].equals("")) {
                inputs[i] = null;
            } else {
                continue;
            }
        }

        String option = optionPE.getValue();

        try{
            double result = physicsCalculatorHandler.handleCalculation("Potential Energy", inputs, option);
            resultPE.setText(Double.toString(result));
        } catch (IllegalArgumentException e) {
            resultPE.setText(e.getMessage());
        }
    }

}