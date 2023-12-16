package edu.usd.ArithmeticService;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static com.example.arthmetccalculator.Calculate.calculate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateTest {
    private Calculate calculate;

    @BeforeEach
    void setUp()
    {
        Calculate caclulate = new Calculate();
    }

    @Test
    public void testAdd()
    {
        double actualValue = 7.0;
        double expectedValue = calculate("4+3");
        assertEquals(actualValue, expectedValue);
    }
    @Test
    public void testSub()
    {
        double actualValue = 1.0;
        double expectedValue = calculate("4-3");
        assertEquals(actualValue, expectedValue);
    }
    @Test
    public void testMultiply()
    {
        double actualValue = 12.0;
        double expectedValue = calculate("4*3");
        assertEquals(actualValue, expectedValue);
    }
    @Test
    public void testDivide()
    {
        double actualValue = 2.0;
        double expectedValue = calculate("4/2");
        assertEquals(actualValue, expectedValue);
    }
    @Test
    public void testSin()
    {
        double actualValue = Math.sin(2.0);
        double expectedValue = calculate("sin(2.0)");
        assertEquals(actualValue, expectedValue);
    }
    @Test
    public void testCos()
    {
        double actualValue = Math.cos(2.0);
        double expectedValue = calculate("cos(2.0)");
        assertEquals(actualValue, expectedValue);
    }
    @Test
    public void testTan()
    {
        double actualValue = Math.tan(2.0);
        double expectedValue = calculate("tan(2.0)");
        assertEquals(actualValue, expectedValue);
    }
    @Test
    public void testPower()
    {
        double actualValue = Math.pow(2.0, 2);
        double expectedValue = calculate("2^2");
        assertEquals(actualValue, expectedValue);
    }

}