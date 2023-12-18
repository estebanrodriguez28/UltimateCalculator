package edu.usd.ConversionService;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainApp extends Application {

    Button clearButton;
    Button calculateButton;

    public ComboBox<String> categoryComboBox;
    public ComboBox<String> sourceUnitComboBox;
    public ComboBox<String> targetUnitComboBox;
    private GridPane root;

    private String[] lengthUnits = {"Meters", "Feet", "Inches", "Kilometers", "Miles", "Centimeter"};
    private String[] weightUnits = {"Kilograms", "Pounds", "Ounces", "Grams"};
    private String[] temperatureUnits = {"Celsius", "Fahrenheit", "Kelvin"};

    private TextField valueToConvert;
    private Text answerTextDisplay;

    public MainApp() {
        root = new GridPane();
        // Initialize your UI components and add them to 'root'
    }

    public GridPane getRootNode() {
        return root;
    }

    public static void main(String[] args) {
       launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Unit Converter");
        Text chooseCategoryText = new Text("Choose a category: ");
        Text textFieldTitle = new Text("Value to Convert");
        Text fromText = new Text("From: ");
        Text toText = new Text("To: ");
        Text answerText = new Text("Answer: ");
        answerTextDisplay = new Text();
        valueToConvert = new TextField();
  
        categoryComboBox = new ComboBox<>(FXCollections.observableArrayList("Length", "Weight", "Temperature"));
        sourceUnitComboBox = new ComboBox<>();
        targetUnitComboBox = new ComboBox<>();
  
        categoryComboBox.setOnAction(new CategorySelectionHandler());


        clearButton = new Button();
        clearButton.setText("Clear");
        clearButton.setOnAction(new ClearButtonHandler());

        calculateButton = new Button();
        calculateButton.setText("Calculate");
        calculateButton.setOnAction(new CalculationHandler());


        GridPane gridPane = new GridPane();
        gridPane.setMinSize(600, 400);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        

        gridPane.add(chooseCategoryText, 0, 0);
        gridPane.add(categoryComboBox, 1, 0);
        gridPane.add(fromText, 0, 2);
        gridPane.add(sourceUnitComboBox, 1, 2);
        gridPane.add(toText, 0, 3);
        gridPane.add(targetUnitComboBox, 1, 3);
        gridPane.add(textFieldTitle, 0, 5);
        gridPane.add(valueToConvert, 0, 6);
        gridPane.add(clearButton, 0, 8);
        gridPane.add(calculateButton, 1, 8);
        gridPane.add(answerText, 0, 10);
        gridPane.add(answerTextDisplay, 0, 11);




        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();




    }

    private class CategorySelectionHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            // Get the selected category
            String selectedCategory = categoryComboBox.getValue();

            // Update the target unit options based on the selected category
            if ("Length".equals(selectedCategory)) {
                sourceUnitComboBox.setItems(FXCollections.observableArrayList(lengthUnits));
                targetUnitComboBox.setItems(FXCollections.observableArrayList(lengthUnits));
            } else if ("Weight".equals(selectedCategory)) {
                sourceUnitComboBox.setItems(FXCollections.observableArrayList(weightUnits));
                targetUnitComboBox.setItems(FXCollections.observableArrayList(weightUnits));
            } else if ("Temperature".equals(selectedCategory)) {
                sourceUnitComboBox.setItems(FXCollections.observableArrayList(temperatureUnits));
                targetUnitComboBox.setItems(FXCollections.observableArrayList(temperatureUnits));
            }
        }
    }

    private class CalculationHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle (ActionEvent event) {

            

            String selectedCategory = categoryComboBox.getValue();
            String convertVal = valueToConvert.getText();
            double convertDoubleVal = Double.parseDouble(convertVal);
            String fromUnit = sourceUnitComboBox.getValue();
            String toUnit = targetUnitComboBox.getValue();

            try {
                if (fromUnit == null || toUnit == null || convertVal == null) {
                    throw new IllegalArgumentException("Please select an option from the dropdown menu or enter a number to convert");
                }
            }

            catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
            
            double convertedValue = 0.0;
            if ("Length".equals(selectedCategory)) {
                UnitConverter lengthConverter = new LengthConverter();
                convertedValue = lengthConverter.convert(convertDoubleVal, fromUnit, toUnit);
            } else if ("Weight".equals(selectedCategory)) {
                UnitConverter weightConverter = new WeightConverter();
                convertedValue = weightConverter.convert(convertDoubleVal, fromUnit, toUnit);
            } else if ("Temperature".equals(selectedCategory)) {
                UnitConverter tempConverter = new TemperatureConverter();
                convertedValue = tempConverter.convert(convertDoubleVal, fromUnit, toUnit);
            }
            String convertedValueString = String.valueOf(convertedValue);
            answerTextDisplay.setText(convertedValueString);
        }
    }

    private class ClearButtonHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
        // Clear the text field
        valueToConvert.clear();
    }
}


    public abstract class UnitConverter {
        public abstract double convert(double value, String sourceUnit, String targetUnit);
    }


    public class LengthConverter extends UnitConverter {
        @Override
        public double convert(double value, String sourceUnit, String targetUnit) {
            if (sourceUnit == "Meters") {
                if (targetUnit == "Feet") {
                    value *= 3.28084;
                }

                else if (targetUnit == "Inches") {
                    value *= 39.3701;
                }

                else if (targetUnit == "Kilometers") {
                    value *= 0.001;
                }

                else if (targetUnit == "Miles") {
                    value *= 0.000621371;
                }

                else if (targetUnit == "Centimeter") {
                    value *= 100;
                }
            }

            else if (sourceUnit == "Feet") {
                if (targetUnit == "Meters") {
                    value *= 0.3048;
                }

                else if (targetUnit == "Inches") {
                    value *= 12;
                }

                else if (targetUnit == "Kilometers") {
                    value *= 0.0003048;

                }

                else if (targetUnit == "Miles") {
                    value *= 0.000189394;
                }

                else if (targetUnit == "Centimeter") {
                    value *= 30.48;
                }

            }

            else if (sourceUnit == "Inches") {

                if (targetUnit == "Meters") {
                    value *= 0.0254;
                }

                else if (targetUnit == "Feet") {
                    value *= 0.08333;
                }

                else if (targetUnit == "Kilometers") {
                    value *= 0.0000254;
                }

                else if (targetUnit == "Miles") {
                    value *= 0.0000157828;
                }

                else if (targetUnit == "Centimeter") {
                    value *= 2.54;
                }
            }

            else if (sourceUnit == "Kilometers") {

                if (targetUnit == "Meters") {
                    value *= 1000;
                }

                else if (targetUnit == "Feet") {
                    value *= 3280.84;
                }

                else if (targetUnit == "Inches") {
                    value *= 39370.1;
                }

                else if (targetUnit == "Miles") {
                    value *= 0.621371;
                }

                else if (targetUnit == "Centimeter") {
                    value *= 100000;
                }
            }

            else if (sourceUnit == "Miles") {

                if (targetUnit == "Meters") {
                    value *= 1609.34;
                }

                else if (targetUnit == "Feet") {
                    value *= 5280;
                }

                else if (targetUnit == "Inches") {
                    value *= 63360;
                }

                else if (targetUnit == "Kilometers") {
                    value *= 1.609;
                }

                else if (targetUnit == "Centimeter") {
                    value *= 160900;
                }
            }

            else if (sourceUnit == "Centimeter") {

                if (targetUnit == "Meters") {
                    value *= 0.01;
                }

                else if (targetUnit == "Feet") {
                    value *= 0.0328084;
                }

                else if (targetUnit == "Inches") {
                    value *= 0.393701;
                }

                else if (targetUnit == "Kilometers") {
                    value *= 0.00001;
                }

                else if (targetUnit == "Miles") {
                    value *= 0.00000621371;
                }
            }

            return value;

        }
    }

    public class WeightConverter extends UnitConverter {
        @Override
        public double convert(double value, String sourceUnit, String targetUnit) {
           
            if (sourceUnit == "Kilograms") {

                if (targetUnit == "Pounds") {
                    value *= 2.205;
                }

                else if (targetUnit == "Ounces") {
                    value *= 35.274;
                }

                else if (targetUnit == "Grams") {
                    value *= 1000;
                }
            }

            else if (sourceUnit == "Pounds") {

                if (targetUnit == "Kilograms") {
                    value *= 0.453592;
                }

                else if (targetUnit == "Ounces") {
                    value *= 16;
                }

                else if (targetUnit == "Grams") {
                    value *= 453.6;
                }

            }

            else if (sourceUnit == "Ounces") {

                if (targetUnit == "Kilograms") {
                    value *= 0.0283495;
                }

                else if (targetUnit == "Pounds") {
                    value *= 0.0625;
                }

                else if (targetUnit == "Grams") {
                    value *= 28.35;
                }
            }

            else if (sourceUnit == "Grams") {

                if (targetUnit == "Kilograms") {
                    value *= 0.001;
                }

                else if (targetUnit == "Pounds") {
                    value *= 0.00220462;
                }

                else if (targetUnit == "Ounces") {
                    value *= 0.035274;
                }
            }


            return value;
        }
    }

    
    public class TemperatureConverter extends UnitConverter {
        @Override
        public double convert(double value, String sourceUnit, String targetUnit) {
            
            if (sourceUnit == "Celsius") {

                if (targetUnit == "Fahrenheit") {
                    value = (value * 9/5) + 32;
                }

                else if (targetUnit == "Kelvin") {
                    value = value + 273.15;
                }
            }

            else if (sourceUnit == "Fahrenheit") {

                if (targetUnit == "Celsius") {
                    value = (value - 32) * 5/9;
                }

                else if (targetUnit == "Kelvin") {
                    value = (value - 32) * 5/9 + 273.15;
                }
            }

            else if (sourceUnit == "Kelvin") {
                
                if (targetUnit == "Fahrenheit") {
                    value = (value - 273.15) * 9/5 + 32;
                }

                else if (targetUnit == "Celsius") {
                    value = value - 273.15;
                }
            }

            return value;
        }
    }

  



}
  