package edu.usd;

import java.io.BufferedWriter;

public class Matrix {
    private double[][] array;

    /**
     * Constructs the matrix using an array
     *
     * @param array the two-dimensional array used to initialize the matrix
     */
    public Matrix(double[][] array) {
    }

    /**
     * Getter for the number of rows
     *
     * @return the number of rows in the matrix
     */
    public int getNumRows() {
        return 0;
    }

    /**
     * Getter for the number of columns
     *
     * @return the number of rows in the matrix
     */
    public int getNumColumns() {
        return 0;
    }

    /**
     * Transforms the matrix into a two-dimensional Java array
     *
     * @return a two-dimensional array with equivalent elements to the matrix
     */
    public double[][] toArray() {
        return new double[0][0];
    }

    /**
     * Gets the value at the specified row and column for the matrix
     *
     * @param row    the row number
     * @param column the column number
     * @return the element at the specified row and column number
     */
    public double getAtIndices(int row, int column) {
        return 0.0;
    }

    /**
     * Multiplies every element in the matrix by the scalar value
     *
     * @param scalar the value used to multiply each element
     */
    public void multiply(double scalar) {
    }

    /**
     * Performs matrix multiplication
     *
     * @param otherMatrix the matrix on the right side of the matrix multiplication operator.
     */
    public void multiply(Matrix otherMatrix) throws IllegalArgumentException {
    }

    /**
     * Saves the matrix
     *
     * @param writer writes the data from the matrix
     */
    public void save(BufferedWriter writer) {
    }
}