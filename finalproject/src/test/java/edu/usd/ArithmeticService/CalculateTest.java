package edu.usd.ArithmeticService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateTest {
    private Calculate calculate;

    @BeforeEach
    void setUp()
    {
        calculate = new Calculate();
    }

    @Test
    public void testAdd()
    {
        double actualValue = 7.0;
        double expectedValue = Calculate.calculate("4+3");
        assertEquals(actualValue, expectedValue);
    }
    @Test
    public void testSub()
    {
        double actualValue = 1.0;
        double expectedValue = Calculate.calculate("4-3");
        assertEquals(actualValue, expectedValue);
    }
    @Test
    public void testMultiply()
    {
        double actualValue = 12.0;
        double expectedValue = Calculate.calculate("4*3");
        assertEquals(actualValue, expectedValue);
    }
    @Test
    public void testDivide()
    {
        double actualValue = 2.0;
        double expectedValue = Calculate.calculate("4/2");
        assertEquals(actualValue, expectedValue);
    }
    @Test
    public void testSin()
    {
        double actualValue = Math.sin(2.0);
        double expectedValue = Calculate.calculate("sin(2.0)");
        assertEquals(actualValue, expectedValue);
    }
    @Test
    public void testCos()
    {
        double actualValue = Math.cos(2.0);
        double expectedValue = Calculate.calculate("cos(2.0)");
        assertEquals(actualValue, expectedValue);
    }
    @Test
    public void testTan()
    {
        double actualValue = Math.tan(2.0);
        double expectedValue = Calculate.calculate("tan(2.0)");
        assertEquals(actualValue, expectedValue);
    }
    @Test
    public void testPower()
    {
        double actualValue = Math.pow(2.0, 2);
        double expectedValue = Calculate.calculate("2^2");
        assertEquals(actualValue, expectedValue);
    }
    @Test
    public void testPresedence()
    {
        double actualValue = 36.0;
        double expectedValue = Calculate.calculate("5*6+2/2*6");
        assertEquals(actualValue, expectedValue);
    }
    @Test
    public void testTwoInputs()
    {
        double actualValue = 0.0;
        double expectedValue = Calculate.calculate("sin(1-1)");
        assertEquals(actualValue, expectedValue);
    }
    @Test
    public void testNegative()
    {
        double actualValue = 4.0;
        double expectedValue = Calculate.calculate("-1+5");
        assertEquals(actualValue, expectedValue);
    }

}