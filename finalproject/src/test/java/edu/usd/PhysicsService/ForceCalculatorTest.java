package edu.usd.PhysicsService;

import java.beans.Transient;
import java.lang.Math;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

public class ForceCalculatorTest {
    ForceCalculator forceCalculator;

    @BeforeEach
    void setup() {
        forceCalculator = new ForceCalculator();
    }

    @Test
    @DisplayName("Test Getter for Inputs")
    void getInputsTest() {
        String[] inputs = forceCalculator.getInputs();
        assertEquals(null, inputs[0], "Get Inputs Failed.");
        assertEquals(null, inputs[1], "Get Inputs Failed.");
        assertEquals(null, inputs[2], "Get Inputs Failed.");
    }

    @Test
    @DisplayName("Test Getter for Option")
    void getOptionTest() {
        assertEquals(null, forceCalculator.getOption(), "Get Option Failed.");
    }

    @Test
    @DisplayName("Test Value Setter")
    void setValuesTest() {
        String[] expectedInputs = { "1.0", "2.0", "0.5" };
        forceCalculator.setValues(expectedInputs, "Test");

        String[] inputs = forceCalculator.getInputs();
        assertEquals("1.0", inputs[0], "Set Inputs Failed.");
        assertEquals("2.0", inputs[1], "Set Inputs Failed.");
        assertEquals("0.5", inputs[2], "Set Inputs Failed.");

        assertEquals("Test", forceCalculator.getOption(), "Set Option Failed.");
    }
    
    @Test
    @DisplayName("Test Force Calculation")
    void forceTest() {
        String[] inputs = { null, "5.0", "1.0" };
        forceCalculator.setValues(inputs, "Force");

        double mass = Double.parseDouble(inputs[1]);
        double accel = Double.parseDouble(inputs[2]);
        double expectedForce = mass * accel;

        assertEquals(expectedForce, forceCalculator.calculateForce(), "Force Calculation Failed.");
    }
    
    @Test
    @DisplayName("Test Acceleration Calculation")
    void accelerationTest() {
        String[] inputs = { "1.0", "5.0", null };
        forceCalculator.setValues(inputs, "Acceleration");

        double force = Double.parseDouble(inputs[0]);
        double mass = Double.parseDouble(inputs[1]);
        double expectedAccel = force / mass;

        assertEquals(expectedAccel, forceCalculator.calculateAccel(), "Acceleration Calculation Failed.");
    }

    @Test
    @DisplayName("Test Mass Calculation")
    void massTest() {
        String[] inputs = { "2.0", null, "1.0" };
        forceCalculator.setValues(inputs, "Mass");

        double force = Double.parseDouble(inputs[0]);
        double accel = Double.parseDouble(inputs[2]);
        double expectedMass = force / accel;

        assertEquals(expectedMass, forceCalculator.calculateMass(), "Mass Calculation Failed.");
    }

    @Parameterized
    @DisplayName("Test Calculation Method")
    @CsvSource({
            "Force,                2.0",
            "Acceleration,         1.0",
            "Mass,                 2.0"
    })
    void calculateTest(String option, double expected) {
        String[] input = { "2.0", "2.0", "1.0" };

        assertEquals(expected, forceCalculator.calculate(input, option),
                "Calculate method failed " + option + "calculation.");
    }

    @Parameterized
    @DisplayName("Test Insufficient Information Error")
    @ValueSource(Strings = { "Force", "Mass", "Acceleration" })
    void testInformationError(String option) {
        String[] input = { null, null, null };

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> forceCalculator.calculate(input, option));
        assertEquals("Not enough information to compute.", exception.getMessage());
    }

    @Parameterized
    @DisplayName("Test Mass <= 0 Error")
    @ValueSource(Strings = { "Force", "Acceleration" })
    void testMassError(String option) {
        String[] input = { "1.0", "-1.0", "1.0" };

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> forceCalculator.calculate(input, option));
        assertEquals("Mass cannot be negative or zero.", exception.getMessage());
    }
    
    @Test
    @DisplayName("Test Force and Acceleration Invalid Input Error")
    void testForceAccelError() {
        String[] input = { "1.0", null, "-1.0" };

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> forceCalculator.calculate(input, option));
        assertEquals("Force and acceleration don't make sense.", exception.getMessage());
    }

    @Parameterized
    @DisplayName("Test Number Format Exception")
    @ValueSource(Strings = {  "Force", "Mass", "Acceleration" })
    void numberFormatExceptionTest(String option) {
        String[] input = { "Test", "Test", "Test"};

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> forceCalculator.calculate(input, option));
        assertEquals("Variables must be numbers.", exception.getMessage());
    }
}