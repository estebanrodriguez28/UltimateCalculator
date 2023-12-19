
package edu.usd.FinanceService;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField startingAmountTextField;
    @FXML
    private TextField rateOfReturnTextField;
    @FXML
    private TextField yearsToGrowTextField;
    @FXML
    private TextField monthlyContributionTextField;

    @FXML
    private GridPane CalculatedGrid;

    private Finances finances; // Instance of Finances class
    private FinancialComponent component; // Instance of FinancialComponent class
    private List<Double> allCalculations = new ArrayList<>();

    @FXML
    protected void onCalculateButtonClick() {
        try {
            // Initialize component with input values
            component = new FinancialComponent(
                    Double.parseDouble(startingAmountTextField.getText()),
                    Double.parseDouble(rateOfReturnTextField.getText()),
                    Double.parseDouble(yearsToGrowTextField.getText()),
                    Double.parseDouble(monthlyContributionTextField.getText())
            );

            finances = new Finances(component, allCalculations); // Initialize Finances with the component

            validateInput(component.getYearsToGrow());

            // Calculate and display financial information
            displayFinancialInformation();
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid numbers.");
        } catch (IllegalArgumentException e) {
            showAlert("Invalid Years", e.getMessage());
        }
    }

    private void validateInput(double yearsToGrow) throws IllegalArgumentException {
        if (yearsToGrow < 0) {
            throw new IllegalArgumentException("Years to grow cannot be negative.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void displayFinancialInformation() {
        CalculatedGrid.getChildren().clear(); // Clearing existing data in the GridPane

        // Headers for the GridPane
        String[] headers = {"Year", "Starting Amount", "Total Contribution", "Total Interest", "Ending Balance"};
            for (int i = 0; i < headers.length; i++) {
                CalculatedGrid.add(new Label(headers[i]), i, 0);
            }

        double startingAmount = component.getStartingAmount();
        double endingBalance = startingAmount;

        // Calculating and displaying data for each year
        for (int year = 1; year <= component.getYearsToGrow(); year++) {
            double totalContribution = finances.calculateContribution();
            double interestEarned = finances.calculateInterest();
            endingBalance = finances.calculateEndingBalance(year);

            System.out.println(startingAmount);
            System.out.println(totalContribution);
            System.out.println(interestEarned);
            System.out.println(endingBalance);

            CalculatedGrid.add(new Label(String.valueOf(year)), 0, year);
            CalculatedGrid.add(new Label(String.format("%.2f", startingAmount)), 1, year);
            CalculatedGrid.add(new Label(String.format("%.2f", totalContribution)), 2, year);
            CalculatedGrid.add(new Label(String.format("%.2f", interestEarned)), 3, year);
            CalculatedGrid.add(new Label(String.format("%.2f", endingBalance)), 4, year);

            startingAmount = endingBalance; // Update starting amount for next year
        }
    }
}