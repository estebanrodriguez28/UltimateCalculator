package edu.usd.FinanceService;

import java.util.List;
public class FinancialCalculator extends Calculator {

    private Finances finances;

    public FinancialCalculator(Finances finances) {
        this.finances = finances;
    }

    public List<Double> calculate() {
        return finances.getCalculations();
    }


}