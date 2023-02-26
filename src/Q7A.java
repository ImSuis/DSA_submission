//a)	Implement multi-threaded algorithm to multiply n*n matrix.

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Q7A {
    public static void main(String[] args) {
        int[][] matrixA = {{3, 3, 1}, {5,2, 9}, {1, 4, 7}};
        int[][] matrixB = {{2, 1, 9}, {2, 4, 7}, {1, 5, 1}};
        int n = matrixA.length;
        int[][] resultMatrix = new int[n][n];

        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                final int row = i;
                final int col = j;
                executorService.submit(() -> {
                    int sum = 0;
                    for (int k = 0; k < n; k++) {
                        sum += matrixA[row][k] * matrixB[k][col];
                    }
                    resultMatrix[row][col] = sum;
                });
            }
        }

        executorService.shutdown();
        while (!executorService.isTerminated()) {

        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(resultMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
