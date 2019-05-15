import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int traverseGrid(int[][] matrix, int x, int y) {
        int componentSize = 0;
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x, y});

        while (!queue.isEmpty()) {
            int[] coord = queue.poll();
            int i = coord[0], j = coord[1];

            if (i >= 0 && j >= 0 && i < matrix.length &&
                j < matrix[0].length && matrix[i][j] == 1) {
                    componentSize++;
                    matrix[i][j] = 0;

                    queue.add(new int[] {i - 1, j});            
                    queue.add(new int[] {i - 1, j - 1});                
                    queue.add(new int[] {i, j - 1});  
                    queue.add(new int[] {i + 1, j - 1});                
                    queue.add(new int[] {i, j + 1}); 
                    queue.add(new int[] {i + 1, j + 1});
                    queue.add(new int[] {i + 1, j}); 
                    queue.add(new int[] {i - 1, j + 1});
                }         
        }

        return componentSize;
    }

    static int connectedCell(int[][] matrix) {
        int maxSize = 0;

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                if (matrix[i][j] == 1)
                    maxSize = Math.max(maxSize, traverseGrid(matrix, i, j));

        return maxSize;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] matrixRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < m; j++) {
                int matrixItem = Integer.parseInt(matrixRowItems[j]);
                matrix[i][j] = matrixItem;
            }
        }

        int result = connectedCell(matrix);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
