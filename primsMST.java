import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int prims(int n, int[][] edges, int start) {
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        int[] visited = new int[n+1];
        int total = 0;

        for (int i = 0; i <= n; i++)
            graph.add(new ArrayList<>());
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(new int[] {edge[1], edge[2]});
            graph.get(edge[1]).add(new int[] {edge[0], edge[2]});
        }

        PriorityQueue<int[]> heap = new PriorityQueue<>(
            (o1, o2) -> Integer.compare(o1[1], o2[1]));

        heap.add(new int[] {start, 0});
        
        while (!heap.isEmpty()) {
            int[] node = heap.poll();
            if (visited[node[0]] == 0) {
                visited[node[0]] = 1;
                total += node[1];
                for (int[] edge : graph.get(node[0])) {
                    heap.add(new int[] {edge[0], edge[1]});
                }
            }
        }

        return total;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] edges = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] edgesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int edgesItem = Integer.parseInt(edgesRowItems[j]);
                edges[i][j] = edgesItem;
            }
        }

        int start = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int result = prims(n, edges, start);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
