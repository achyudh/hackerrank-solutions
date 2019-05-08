import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int[] bfs(int n, int m, int[][] edges, int s) {
        
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i0 = 0; i0 <= n; i0++)
            graph.add(new ArrayList<>());

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int[] visited = new int[n+1];
        int[] distance = new int[n+1];

        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[] {s, 0});

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            if (visited[node[0]] == 0) {
                visited[node[0]] = 1;
                distance[node[0]] = node[1];

                for (int next : graph.get(node[0]))
                    queue.add(new int[] {next, node[1] + 6});
            }
        }

        return distance;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            int[][] edges = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            int s = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] result = bfs(n, m, edges, s);

            for (int i = 1; i < result.length; i++) {
                if (i == s) 
                    continue;
                else if (result[i] == 0)
                    bufferedWriter.write(String.valueOf(-1));
                else
                    bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
