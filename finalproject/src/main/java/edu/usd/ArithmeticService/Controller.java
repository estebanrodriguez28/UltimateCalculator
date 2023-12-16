package edu.usd.ArithmeticService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Stack;

public class Controller {
    public TextField display;
    public TextField display1;
    Stack<Double> old_Answers = new Stack<>();

    public  Calculate calculate = new Calculate();
    @FXML

    public Button one, two, three,
            four, five, six,
            seven, eight, nine;
    public Label result;

    @FXML
    public void processClick(ActionEvent event)
    {
        String value = ((Button)event.getSource()).getText();
        System.out.println(value);
        switch(value) {
            case "=":
                try {
                    String expression = display.getText();
                    display1.setText(expression);
                    double finalValue = Calculate.calculate(expression);
                    old_Answers.push(finalValue);
                    System.out.println(finalValue);
                    display.setText(Double.toString(finalValue));
                } catch (Exception e) {
                    System.out.println(e);
                    display.setText("Error");
                }
                break;
            case "↑":
                String last_val = null;
                if (!old_Answers.isEmpty()) {
                    last_val = old_Answers.peek().toString();
                    System.out.println("last value: " + last_val);
                    display.setText(Double.toString(old_Answers.pop()));
                } else {
                    display.setText(last_val);
                }
                break;
            case "clear":
                display.clear();
                break;
            default:
                display.setText(display.getText() + value);
                break;

        }
    }
}