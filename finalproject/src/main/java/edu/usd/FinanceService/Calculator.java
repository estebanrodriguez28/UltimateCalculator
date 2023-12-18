package edu.usd.FinanceService;

import java.util.List;
// Abstract class for general calculations
public abstract class Calculator {

    public abstract List<Double> calculate();

    public void saveResult(String filename, String data) {
    }

    public void printResult(String data) {
    }
}
