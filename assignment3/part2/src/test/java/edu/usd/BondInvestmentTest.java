package edu.usd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BondInvestmentTest {

    private BondInvestment bond;

    @BeforeEach
    public void setUp() {
        bond = new BondInvestment("treasury", 0.05, 1000.0);
    }

    @Test
    void getValue() {
        double expectedValue = (1000 * Math.pow((1 + 0.05), 3));
        assertEquals(bond.getValue(1095), expectedValue);
    }
}