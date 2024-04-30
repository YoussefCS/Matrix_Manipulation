package com.matrices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class MatrixDriver {
    private static final String FILENAME1 = "/matrix1.txt";
    private static final String FILENAME2 = "/matrix2.txt";

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String choice;
        try {
            while (true) {
                System.out.println("\nChoose option for inputting matrices:");
                System.out.println("1. Enter matrices manually");
                System.out.println("2. Read matrices from files");
                System.out.println("3. Exit");
                choice = br.readLine();
                switch (choice) {
                    case "1":
                        testMatrixOperations(false);
                        break;
                    case "2":
                        testMatrixOperations(true);
                        break;
                    case "3":
                        System.out.println("Exiting...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testMatrixOperations(boolean readFromFile) {
        try {
            float[][] matrixA, matrixB;
            if (!readFromFile) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter the number of rows for matrices:");
                int rows = Integer.parseInt(br.readLine());
                System.out.println("Enter the number of columns for matrices:");
                int cols = Integer.parseInt(br.readLine());
                matrixA = readMatrix(rows, cols, br);
                matrixB = readMatrix(rows, cols, br);
                testMatrixOperation(matrixA, matrixB);
            } else {
                matrixA = readMatrixFromFile(FILENAME1);
                matrixB = readMatrixFromFile(FILENAME2);
                // Print contents of matrices from files
                System.out.println("Contents of " + FILENAME1 + ":");
                printMatrix(matrixA);
                System.out.println("Contents of " + FILENAME2 + ":");
                printMatrix(matrixB);
                testMatrixOperation(matrixA, matrixB);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testMatrixOperation(float[][] matrixA, float[][] matrixB) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String choice;
        try {
            while (true) {
                System.out.println("\nChoose operation to test:");
                System.out.println("1. Matrix Addition");
                System.out.println("2. Matrix Subtraction");
                System.out.println("3. Matrix Multiplication");
                System.out.println("4. Exit");
                choice = br.readLine();
                switch (choice) {
                    case "1":
                        System.out.println("Testing matrix addition:");
                        MatrixOperations.addMatrices(matrixA, matrixB);
                        break;
                    case "2":
                        System.out.println("Testing matrix subtraction:");
                        MatrixOperations.subtractMatrices(matrixA, matrixB);
                        break;
                    case "3":
                        System.out.println("Testing matrix multiplication:");
                        MatrixOperations.multiplyMatrices(matrixA, matrixB);
                        break;
                    case "4":
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static float[][] readMatrix(int rows, int cols, BufferedReader br) throws IOException {
        float[][] matrix = new float[rows][cols];
        System.out.println("Enter elements for matrix:");
        for (int i = 0; i < rows; i++) {
            String[] elements = br.readLine().split(" ");
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Float.parseFloat(elements[j]);
            }
        }
        return matrix;
    }

    public static float[][] readMatrixFromFile(String filename) throws IOException {
        try (InputStream inputStream = MatrixDriver.class.getResourceAsStream(filename);
             BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            String line = br.readLine();
            String[] size = line.split(" ");
            int rows = Integer.parseInt(size[0]);
            int cols = Integer.parseInt(size[1]);
            float[][] matrix = new float[rows][cols];
            // Read the matrix from the file
            for (int i = 0; i < rows; i++) {
                line = br.readLine();
                String[] elements = line.split(" ");
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = Float.parseFloat(elements[j]);
                }
            }
            return matrix;
        }
    }

    public static void printMatrix(float[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
