import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int[] shortestReach(int n, int[][] edges, int s) {
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        int[] visited = new int[n + 1];
        int[] distance = new int[n + 1];

        for (int i0 = 0; i0 <= n; i0++)
            graph.add(new ArrayList<>());

        for (int[] edge : edges) {
            graph.get(edge[0]).add(new int[] {edge[1], edge[2]});
            graph.get(edge[1]).add(new int[] {edge[0], edge[2]});
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(
            (o1, o2) -> Integer.compare(o1[1], o2[1]));

        queue.add(new int[] {s, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            if (visited[current[0]] == 0) {
                visited[current[0]] = 1;
                distance[current[0]] = current[1];

                for (int[] next : graph.get(current[0]))
                    if (visited[next[0]] == 0)
                        queue.add(new int[] {next[0], current[1] + next[1]});
            }
        }

        return distance;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
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

            int s = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] result = shortestReach(n, edges, s);

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
