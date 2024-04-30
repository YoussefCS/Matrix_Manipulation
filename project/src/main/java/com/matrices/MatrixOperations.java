package com.matrices;

import java.util.Scanner;

public class MatrixOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Matrix Operations Menu:");
            System.out.println("1. Add matrices");
            System.out.println("2. Subtract matrices");
            System.out.println("3. Multiply matrices");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    float[][] matrixA = readMatrix(scanner);
                    float[][] matrixB = readMatrix(scanner);
                    long startTimeAdd = System.currentTimeMillis();
                    addMatrices(matrixA, matrixB);
                    long endTimeAdd = System.currentTimeMillis();
                    float timeElapsedAdd = (endTimeAdd - startTimeAdd) / 1000.0f;
                    System.out.println("Time taken for addition: " + timeElapsedAdd + " seconds");
                    break;
                case 2:
                    matrixA = readMatrix(scanner);
                    matrixB = readMatrix(scanner);
                    long startTimeSubtract = System.currentTimeMillis();
                    subtractMatrices(matrixA, matrixB);
                    long endTimeSubtract = System.currentTimeMillis();
                    float timeElapsedSubtract = (endTimeSubtract - startTimeSubtract) / 1000.0f;
                    System.out.println("Time taken for subtraction: " + timeElapsedSubtract + " seconds");
                    break;
                case 3:
                    matrixA = readMatrix(scanner);
                    matrixB = readMatrix(scanner);
                    long startTimeMultiply = System.currentTimeMillis();
                    multiplyMatrices(matrixA, matrixB);
                    long endTimeMultiply = System.currentTimeMillis();
                    float timeElapsedMultiply = (endTimeMultiply - startTimeMultiply) / 1000.0f;
                    System.out.println("Time taken for multiplication: " + timeElapsedMultiply + " seconds");
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            if (!exit) {
                System.out.println("Press any key to continue...");
                scanner.nextLine(); // Consume the newline character
                scanner.nextLine(); // Wait for any key press before continuing
            }
        }

        scanner.close();
    }

    public static void addMatrices(float[][] matrixA, float[][] matrixB) {
        if (matrixA.length != matrixB.length || matrixA[0].length != matrixB[0].length) {
            System.out.println("Matrices are not of the same size. Addition not possible.");
            return;
        }

        float[][] result = new float[matrixA.length][matrixA[0].length];

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[0].length; j++) {
                result[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }

        long endTime = System.currentTimeMillis();
        float timeElapsed = (endTime - startTime) / 1000.0f;

        System.out.println("Result of addition:");
        printMatrix(result);
        System.out.println("Time taken for addition: " + timeElapsed + " seconds");
    }

    public static void subtractMatrices(float[][] matrixA, float[][] matrixB) {
        if (matrixA.length != matrixB.length || matrixA[0].length != matrixB[0].length) {
            System.out.println("Matrices are not of the same size. Subtraction not possible.");
            return;
        }

        float[][] result = new float[matrixA.length][matrixA[0].length];

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[0].length; j++) {
                result[i][j] = matrixA[i][j] - matrixB[i][j];
            }
        }

        long endTime = System.currentTimeMillis();
        float timeElapsed = (endTime - startTime) / 1000.0f;

        System.out.println("Result of subtraction:");
        printMatrix(result);
        System.out.println("Time taken for subtraction: " + timeElapsed + " seconds");
    }

    public static void multiplyMatrices(float[][] matrixA, float[][] matrixB) {
        if (matrixA[0].length != matrixB.length) {
            System.out.println("Number of columns in the first matrix must be equal to the number of rows in the second matrix. Multiplication not possible.");
            return;
        }

        float[][] result = new float[matrixA.length][matrixB[0].length];

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                for (int k = 0; k < matrixA[0].length; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }

        long endTime = System.currentTimeMillis();
        float timeElapsed = (endTime - startTime) / 1000.0f;

        System.out.println("Result of multiplication:");
        printMatrix(result);
        System.out.println("Time taken for multiplication: " + timeElapsed + " seconds");
    }

    public static float[][] readMatrix(Scanner scanner) {
        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter number of columns: ");
        int columns = scanner.nextInt();

        float[][] matrix = new float[rows][columns];

        System.out.println("Enter the elements of the matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = scanner.nextFloat();
            }
        }

        return matrix;
    }

    public static void printMatrix(float[][] matrix) {
        for (float[] row : matrix) {
            System.out.print("["); // Print opening bracket for each row
            for (int i = 0; i < row.length; i++) {
                System.out.print(row[i]);
                if (i < row.length - 1) {
                    System.out.print(", "); // Add comma and space between elements
                }
            }
            System.out.println("]"); // Print closing bracket for each row
        }
    }
}
