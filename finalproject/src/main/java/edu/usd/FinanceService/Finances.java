package edu.usd.FinanceService;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class Finances {
    private List<Double> allCalculations;
    private FinancialComponent component = new FinancialComponent(0, 0, 0, 0);
    private double currentBalance;
    public Finances(FinancialComponent component, List<Double> allCalculations) {
        this.component = component;
        this.allCalculations = new ArrayList<>();
        currentBalance = component.getStartingAmount();
    }

    //Calculate the Total Contribution
    public double calculateContribution() {
        double monthlyContribution = component.getMonthlyContribution();
        double years = component.getYearsToGrow();
        double totalContribution = (years * 12 * monthlyContribution);
        allCalculations.add(totalContribution);
        return totalContribution;
    }
    // Calculate the Interest Earned
    public double calculateInterest() {
        double principal = currentBalance;
        double rate = component.getRateOfReturn();
        double time = component.getYearsToGrow();

        double interestEarned = principal * rate * time;

        allCalculations.add(interestEarned);
        return interestEarned;
    }

    //Calculate the Final Balance
    public double calculateEndingBalance(double year) {

        double compoundingFrequency = 1;
        double monthlyContribution = component.getMonthlyContribution();
        double principal = component.getStartingAmount();
        double rate = component.getRateOfReturn();
        double frequency = compoundingFrequency;
        double time = year;

        double finalBalance = principal * Math.pow(1 + rate / frequency, frequency * time) + monthlyContribution
                * ((Math.pow(1 + rate / frequency, frequency * time) - 1) / (rate / frequency));

        currentBalance = finalBalance;

        allCalculations.add(finalBalance);
        return finalBalance;
    }

    public List<Double> getCalculations() {
        return allCalculations;
    }
    public FinancialComponent getComponent() {
        return this.component;
    }

}
