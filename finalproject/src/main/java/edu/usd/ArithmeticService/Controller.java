package edu.usd.ArithmeticService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Stack;
public class Controller {

    // Reference to the input TextField for the current equation
    public TextField currentEquation;

    // Reference to the input TextField for the old equation
    public TextField oldEquation;

    // Stack to store previous answers
    public static Stack<Double> old_Answers = new Stack<>();

    // Instance of the Calculate class for performing calculations
    public Calculate calculate = new Calculate();

    // Event handler for button clicks
    @FXML
    public void processClick(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        String prevVal = null;

        // Switch statement to handle different button clicks
        switch (value) {
            case "=":
                handleEquals(currentEquation, oldEquation);
                break;
            case "prev":
                // Display the previous answer or clear if stack is empty
                if (!old_Answers.isEmpty()) {
                    prevVal = old_Answers.peek().toString();
                    currentEquation.setText(Double.toString(old_Answers.pop()));
                } else {
                    currentEquation.setText(prevVal);
                }
                break;
            case "clear":
                // Clear the current equation TextField
                currentEquation.clear();
                break;
            default:
                // Append the button value to the current equation TextField
                currentEquation.setText(currentEquation.getText() + value);
                break;
        }
    }

    // Method to handle the equals button click
    public static void handleEquals(TextField currentEquation, TextField oldEquation) {
        try {
            // Get the expression from the current equation TextField
            String expression = currentEquation.getText();

            // Display the current equation in the old equation TextField
            oldEquation.setText(expression);

            // Calculate the result and display it in the current equation TextField
            double finalValue = Calculate.calculate(expression);
            old_Answers.push(finalValue);
            currentEquation.setText(Double.toString(finalValue));
        } catch (Exception e) {
            // Handle exceptions and display "Error" in case of an error
            System.out.println(e);
            currentEquation.setText("Error: " + '"' +currentEquation.getText() + '"' + " is not a valid equation");
        }
    }
}