package com.example.financial;

public class FinancialComponent {
    private double startingAmount;
    private double rateOfReturn;
    private double yearsToGrow;
    private double monthlyContribution;

    public FinancialComponent(double startingAmount, double rateOfReturn, double yearsToGrow, double monthlyContribution) {
        setStartingAmount(startingAmount);
        setRateOfReturn(rateOfReturn);
        setYearsToGrow(yearsToGrow);
        setMonthlyContribution(monthlyContribution);
    }

    // Getter Methods
    public double getStartingAmount(){
        return startingAmount;
    }

    public double getRateOfReturn(){
        return rateOfReturn;
    }

    public double getYearsToGrow(){
        return yearsToGrow;
    }

    public double getMonthlyContribution(){
        return monthlyContribution;
    }

    // Setter Methods
    public void setStartingAmount(double startingAmount) {
        this.startingAmount = startingAmount;
    }

    public void setRateOfReturn(double rateOfReturn) {
        this.rateOfReturn = rateOfReturn;
    }

    public void setYearsToGrow(double yearsToGrow) {
        this.yearsToGrow = yearsToGrow;
    }

    public void setMonthlyContribution(double monthlyContribution) {
        this.monthlyContribution = monthlyContribution;
    }


}
