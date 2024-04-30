const fs = require('fs');
const readlineSync = require('readline-sync');

class MatrixOperations {
    static addMatrices(matrixA, matrixB) {
        if (matrixA.length !== matrixB.length || matrixA[0].length !== matrixB[0].length) {
            console.log("Matrices are not of the same size. Addition not possible.");
            return;
        }

        const startTime = performance.now();

        const result = matrixA.map((rowA, i) => rowA.map((valA, j) => parseFloat(valA) + parseFloat(matrixB[i][j])));

        const endTime = performance.now();
        const timeTaken = endTime - startTime;

        console.log(`Result of multiplication:`);
        this.printMatrix(result);
        console.log(`Time taken for multiplication: ${timeTaken.toFixed(2)} milliseconds`);
    }

    static subtractMatrices(matrixA, matrixB) {
        if (matrixA.length !== matrixB.length || matrixA[0].length !== matrixB[0].length) {
            console.log("Matrices are not of the same size. Subtraction not possible.");
            return;
        }

        const startTime = performance.now();

        const result = matrixA.map((rowA, i) => rowA.map((valA, j) => parseFloat(valA) - parseFloat(matrixB[i][j])));

        const endTime = performance.now();
        const timeTaken = endTime - startTime;

        console.log(`Result of multiplication:`);
        this.printMatrix(result);
        console.log(`Time taken for multiplication: ${timeTaken.toFixed(2)} milliseconds`);
    }

    static multiplyMatrices(matrixA, matrixB) {
        if (matrixA[0].length !== matrixB.length) {
            console.log("Number of columns in the first matrix must be equal to the number of rows in the second matrix. Multiplication not possible.");
            return;
        }

        const startTime = performance.now();

        const result = [];
        for (let i = 0; i < matrixA.length; i++) {
            result.push([]);
            for (let j = 0; j < matrixB[0].length; j++) {
                let sum = 0;
                for (let k = 0; k < matrixA[0].length; k++) {
                    sum += parseFloat(matrixA[i][k]) * parseFloat(matrixB[k][j]);
                }
                result[i].push(sum);
            }
        }

        const endTime = performance.now();
        const timeTaken = endTime - startTime;

        console.log(`Result of multiplication:`);
        this.printMatrix(result);
        console.log(`Time taken for multiplication: ${timeTaken.toFixed(2)} milliseconds`);
    }

    static readMatrix() {
        console.log("Enter number of rows and columns for the matrices (separated by space): ");
        const [rows, columns] = readlineSync.question().split(" ").map(Number);

        const matrix = [];
        console.log(`Enter the elements of the ${rows}x${columns} matrix:`);
        for (let i = 0; i < rows; i++) {
            const row = readlineSync.question().split(" ").map(Number);
            matrix.push(row);
        }

        return matrix;
    }

    static readMatrixFromFile(filename) {
        try {
            const data = fs.readFileSync(filename, 'utf8');
            const lines = data.trim().split('\n');
            const [rows, cols] = lines.shift().split(' ').map(Number);
            const matrix = [];
            for (let i = 0; i < rows; i++) {
                const row = lines[i].split(' ').map(Number);
                matrix.push(row);
            }
            return matrix;
        } catch (err) {
            console.error(`Error reading file ${filename}:`, err);
            return null;
        }
    }

    static printMatrix(matrix) {
        for (let i = 0; i < matrix.length; i++) {
            console.log(`[${matrix[i].map(val => parseFloat(val).toFixed(2)).join(", ")}]`);
        }
    }
}

class MatrixDriver {
    static main() {
        const exit = false;

        while (!exit) {
            console.log("\nChoose option for inputting matrices:");
            console.log("1. Enter matrices manually");
            console.log("2. Read matrices from files");
            console.log("3. Exit");

            const choice = parseInt(readlineSync.question("Enter your choice: "));

            switch (choice) {
                case 1:
                    this.testMatrixOperations(false);
                    break;
                case 2:
                    this.testMatrixOperations(true);
                    break;
                case 3:
                    console.log("Exiting...");
                    return;
                default:
                    console.log("Invalid choice.");
                    break;
            }
        }
    }

    static testMatrixOperations(readFromFile) {
        try {
            let matrixA, matrixB;
            if (!readFromFile) {
                matrixA = MatrixOperations.readMatrix();
                matrixB = MatrixOperations.readMatrix();
                this.testMatrixOperation(matrixA, matrixB);
            } else {
                matrixA = MatrixOperations.readMatrixFromFile('./project/src/main/resources/matrix1.txt'); // Adjust the path if needed
                matrixB = MatrixOperations.readMatrixFromFile('./project/src/main/resources/matrix2.txt'); // Adjust the path if needed
                console.log("Contents of matrix1.txt:");
                MatrixOperations.printMatrix(matrixA);
                console.log("Contents of matrix2.txt:");
                MatrixOperations.printMatrix(matrixB);
                this.testMatrixOperation(matrixA, matrixB);
            }
        } catch (error) {
            console.error(error);
        }
    }

    static testMatrixOperation(matrixA, matrixB) {
        const exit = false;
        if (matrixA.length !== matrixB.length || matrixA[0].length !== matrixB[0].length) {
            console.log("Matrices must have the same number of rows and columns.");
            return;
        }

        while(!exit){
            const choice = parseInt(readlineSync.question("\nChoose operation to test:\n1. Matrix Addition\n2. Matrix Subtraction\n3. Matrix Multiplication\n4. Exit\nEnter your choice: "));
            switch (choice) {
                case 1:
                    console.log("Testing matrix addition:");
                    MatrixOperations.addMatrices(matrixA, matrixB);
                    break;
                case 2:
                    console.log("Testing matrix subtraction:");
                    MatrixOperations.subtractMatrices(matrixA, matrixB);
                    break;
                case 3:
                    console.log("Testing matrix multiplication:");
                    MatrixOperations.multiplyMatrices(matrixA, matrixB);
                    break;
                case 4:
                    console.log("Exiting...");
                    return;
                default:
                    console.log("Invalid choice.");
                    break;
            }
        }    
    }
}

MatrixDriver.main();
