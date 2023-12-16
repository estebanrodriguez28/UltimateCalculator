package edu.usd.PhysicsService;

import java.lang.Math;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class KinematicCalculatorTest {
    KinematicCalculator kinematicCalculator;

    @BeforeEach
    void setup() {
        kinematicCalculator = new KinematicCalculator();
    }

    @Test
    @DisplayName("Test Getter for Inputs")
    void getInputsTest() {
        String[] inputs = kinematicCalculator.getInputs();
        assertEquals(null, inputs[0], "Get Inputs Failed.");
        assertEquals(null, inputs[1], "Get Inputs Failed.");
        assertEquals(null, inputs[2], "Get Inputs Failed.");
        assertEquals(null, inputs[3], "Get Inputs Failed.");
        assertEquals(null, inputs[4], "Get Inputs Failed.");
    }
    
    @Test
    @DisplayName("Test Getter for Option")
    void getOptionTest() {
        assertEquals(null, kinematicCalculator.getOption(), "Get Option Failed.");
    }

    @Test
    @DisplayName("Test Value Setter")
    void setValuesTest() {
        String[] expectedInputs = { "1.0", "2.0", "3.0", "0.0", "2.0" };
        kinematicCalculator.setValues(expectedInputs, "Test");

        String[] inputs = kinematicCalculator.getInputs();
        assertEquals("1.0", inputs[0], "Set Inputs Failed.");
        assertEquals("2.0", inputs[1], "Set Inputs Failed.");
        assertEquals("3.0", inputs[2], "Set Inputs Failed.");
        assertEquals("0.0", inputs[3], "Set Inputs Failed.");
        assertEquals("2.0", inputs[4], "Set Inputs Failed.");

        assertEquals("Test", kinematicCalculator.getOption(), "Set Option Failed.");
    }

    @Test
    @DisplayName("Test Distance Calculation")
    void distanceTest() {
        double initV, finalV, accel, time;

        String[] firstInput = { "0.0", "5.0", null, "1.0", null };
        String option = "Distance";
        kinematicCalculator.setValues(firstInput, option);

        initV = Double.parseDouble(firstInput[0]);
        finalV = Double.parseDouble(firstInput[1]);
        accel = Double.parseDouble(firstInput[3]);

        double expectedDistance = ((Math.pow(finalV, 2) - Math.pow(initV, 2)) / (2 * accel));

        assertEquals(expectedDistance, kinematicCalculator.calculateDist(),
                "Error Calculating Distance(Initial V, Final V, Acceleration)");

        String[] secondInput = { "0.0", null, "5.0", "1.0", null };
        kinematicCalculator.setValues(secondInput, option);

        initV = Double.parseDouble(secondInput[0]);
        time = Double.parseDouble(secondInput[2]);
        accel = Double.parseDouble(secondInput[3]);

        expectedDistance = (initV * time) + (0.5 * accel * Math.pow(time, 2));

        assertEquals(expectedDistance, kinematicCalculator.calculateDist(),
                "Error Calculating Distance(Initial V, Time, Acceleration)");

        String[] thirdInput = { "0.0", "5.0", "4.0", null, null };
        kinematicCalculator.setValues(thirdInput, option);

        initV = Double.parseDouble(thirdInput[0]);
        finalV = Double.parseDouble(thirdInput[1]);
        time = Double.parseDouble(thirdInput[2]);

        expectedDistance = (0.5 * (initV + finalV) * time);

        assertEquals(expectedDistance, kinematicCalculator.calculateDist(),
                "Error Calculating Distance(Initial V, Final V, Time)");
    }

    @Test
    @DisplayName("Test Acceleration Calculation")
    void accelerationTest() {
        double initV, finalV, time, distance;

        String[] firstInput = { "0.0", "5.0", "5.0", null, null };
        String option = "Acceleration";
        kinematicCalculator.setValues(firstInput, option);

        initV = Double.parseDouble(firstInput[0]);
        finalV = Double.parseDouble(firstInput[1]);
        time = Double.parseDouble(firstInput[2]);

        double expectedAccel = ((Math.pow(finalV, 2) - Math.pow(initV, 2)) / (2 * time));

        assertEquals(expectedAccel, kinematicCalculator.calculateAccel(),
                "Error Calculating Acceleration(Initial V, Final V, Time)");

        String[] secondInput = { "0.0", null, "5.0", null, "4.0" };
        kinematicCalculator.setValues(secondInput, option);

        initV = Double.parseDouble(secondInput[0]);
        time = Double.parseDouble(secondInput[2]);
        distance = Double.parseDouble(secondInput[4]);

        expectedAccel = (distance - (initV * time)) / (0.5 * Math.pow(time, 2));

        assertEquals(expectedAccel, kinematicCalculator.calculateAccel(),
                "Error Calculating Acceleration (Initial V, Time, Distance)");

        String[] thirdInput = { "0.0", "5.0", "4.0", null, null };
        kinematicCalculator.setValues(thirdInput, option);

        initV = Double.parseDouble(thirdInput[0]);
        finalV = Double.parseDouble(thirdInput[1]);
        time = Double.parseDouble(thirdInput[2]);

        expectedAccel = (finalV - initV) / time;

        assertEquals(expectedAccel, kinematicCalculator.calculateAccel(),
                "Error Calculating Acceleration (Initial V, Final V, Time)");
    }

    @Test
    @DisplayName("Test Time Calculation")
    void timeTest() {
        double accel, finalV, initV, distance;

        String[] firstInput = { "0.0", "5.0", null, "1.0", null };
        String option = "Time";
        kinematicCalculator.setValues(firstInput, option);

        initV = Double.parseDouble(firstInput[0]);
        finalV = Double.parseDouble(firstInput[1]);
        accel = Double.parseDouble(firstInput[3]);

        double expectedTime = (finalV - initV) / accel;

        assertEquals(expectedTime, kinematicCalculator.calculateTime(),
                "Error Calculating Time (Initial V, Final V, Acceleration)");

        String[] secondInput = { "0.0", null, null, "5.0", "4.0" };
        kinematicCalculator.setValues(secondInput, option);

        initV = Double.parseDouble(secondInput[0]);
        accel = Double.parseDouble(secondInput[3]);
        distance = Double.parseDouble(secondInput[4]);

        double arg = (Math.pow(initV, 2) - (4 * (0.5 * accel) * (distance * -1)));
        expectedTime = ((initV * -1) + Math.sqrt(arg)) / (2 * accel);

        assertEquals(expectedTime, kinematicCalculator.calculateTime(),
                "Error Calculating Time (Initial V, Distance, Acceleration)");

        String[] thirdInput = { "0.0", "5.0", null, null, "5.0" };
        kinematicCalculator.setValues(thirdInput, option);

        initV = Double.parseDouble(thirdInput[0]);
        finalV = Double.parseDouble(thirdInput[1]);
        distance = Double.parseDouble(thirdInput[4]);

        expectedTime = distance / (0.5 * (initV + finalV));
        assertEquals(expectedTime, kinematicCalculator.calculateTime(),
                "Error Calculating Time (Initial V, Final V, Distance)");
    }

    @Test
    @DisplayName("Test Initial Velocity Calculation")
    void initialVelocityTest() {
        double accel, finalV, time, distance;
        String[] firstInput = { null, "5.0", null, "1.0", "5.0" };
        String option = "Initial Velocity";
        kinematicCalculator.setValues(firstInput, option);

        finalV = Double.parseDouble(firstInput[1]);
        accel = Double.parseDouble(firstInput[3]);
        distance = Double.parseDouble(firstInput[4]);

        double expectedInitV = Math.sqrt(Math.pow(finalV, 2) - (2.0 * accel * distance));

        assertEquals(expectedInitV, kinematicCalculator.calculateInitV(),
                "Error Calculating Initial V (Final V, Acceleration, Distance)");

        String[] secondInput = { null, "5.0", "5.0", "1.0", null };
        kinematicCalculator.setValues(secondInput, option);

        finalV = Double.parseDouble(secondInput[1]);
        accel = Double.parseDouble(secondInput[3]);
        time = Double.parseDouble(secondInput[2]);

        expectedInitV = finalV - (accel * time);

        assertEquals(expectedInitV, kinematicCalculator.calculateInitV(),
                "Error Calculating Initial V (Final V, Acceleration, Time)");

        String[] thirdInput = { null, null, "5.0", "1.0", "5.0" };
        kinematicCalculator.setValues(thirdInput, option);

        time = Double.parseDouble(thirdInput[2]);
        accel = Double.parseDouble(thirdInput[3]);
        distance = Double.parseDouble(thirdInput[4]);

        expectedInitV = (distance - (0.5 * accel * Math.pow(time, 2)) / time);

        assertEquals(expectedInitV, kinematicCalculator.calculateInitV(),
                "Error Calculating Initial V (Distance, Acceleration, Time)");

        String[] fourthInput = { null, "5.0", "5.0", null, "10.0" };
        kinematicCalculator.setValues(fourthInput, option);

        finalV = Double.parseDouble(fourthInput[1]);
        time = Double.parseDouble(fourthInput[2]);
        distance = Double.parseDouble(fourthInput[4]);

        expectedInitV = ((distance / time) * 2.0) - finalV;

        assertEquals(expectedInitV, kinematicCalculator.calculateInitV(),
                "Error Calculating Initial V (Distance, Final V, Time)");
    }
    
    @Test
    @DisplayName("Test Final Velocity Calculation")
    void finalVelocityTest() {
        double accel, initV, time, distance;
        String[] firstInput = { "1.0", null, null, "5.0", "5.0" };
        String option = "Final Velocity";
        kinematicCalculator.setValues(firstInput, option);

        initV = Double.parseDouble(firstInput[0]);
        accel = Double.parseDouble(firstInput[3]);
        distance = Double.parseDouble(firstInput[2]);

        double expectedFinalV = Math.sqrt(Math.pow(initV, 2) + (2 * accel * distance));

        assertEquals(expectedFinalV, kinematicCalculator.calculateFinalV(),
                "Error Calculating Final V (Initial V, Acceleration, Distance)");

        String[] secondInput = { "3.0", null, "5.0", "1.0", null };
        kinematicCalculator.setValues(secondInput, option);

        initV = Double.parseDouble(secondInput[0]);
        accel = Double.parseDouble(secondInput[3]);
        time = Double.parseDouble(secondInput[2]);

        expectedFinalV = initV + (accel * time);

        assertEquals(expectedFinalV, kinematicCalculator.calculateFinalV(),
                "Error Calculating Final V (Initial V, Acceleration, Time)");

        String[] thirdInput = { "0.0", null, "5.0", null, "5.0" };
        kinematicCalculator.setValues(thirdInput, option);

        initV = Double.parseDouble(thirdInput[0]);
        time = Double.parseDouble(thirdInput[2]);
        distance = Double.parseDouble(thirdInput[4]);

        expectedFinalV = (distance / (0.5 * time)) - initV;

        assertEquals(expectedFinalV, kinematicCalculator.calculateFinalV(),
                "Error Calculating Final V (Initial V, Time, Distance)");
    }
    
    @ParameterizedTest
    @DisplayName("Test Calculation Method")
    @CsvSource({
            "Distance,             6.0",
            "Acceleration,         2.0",
            "Time,                 2.0",
            "'Final Velocity',     5.0",
            "'Initial Velocity',   5.0"
    })
    void calculateTest(String option, double expected) {
        String[] input = { "1.0", "5.0", "2.0", "2.0", "6.0" };

        assertEquals(expected, kinematicCalculator.calculate(input, option), "Calculate method failed " + option + "calculation.");
    }

    @ParameterizedTest
    @DisplayName("Test Time < 0 Error")
    @ValueSource(strings = {"Distance", "Acceleration", "Initial Velocity", "Final Velocity"})
    void testTimeError(String option) {
        String[] input = { null, null, "-1.0", null, null };

        Exception exception = assertThrows(IllegalArgumentException.class, () -> kinematicCalculator.calculate(input, option));
        assertEquals("Time must be positive.", exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("Test Insufficient Information Error")
    @ValueSource(strings = {"Distance", "Acceleration", "Time", "Initial Velocity", "Final Velocity"})
    void testInformationError(String option) {
        String[] input = { null, null, null, null, null };

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> kinematicCalculator.calculate(input, option));
        assertEquals("Not enough information to compute.", exception.getMessage());
    }

    @ParameterizedTest 
    @DisplayName("Test Number Format Exception")
    @ValueSource(strings = {"Distance", "Acceleration", "Time", "Initial Velocity", "Final Velocity"})
    void numberFormatExceptionTest(String option) {
        String[] input = { "Test", "Test", "Test", "Test", "Test" };

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> kinematicCalculator.calculate(input, option));
        assertEquals("Variables must be numbers.", exception.getMessage());
    }
}