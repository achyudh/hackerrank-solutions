import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static void insertionSort2(int n, int[] arr) {
        for (int l0 = 1; l0 < arr.length; l0++) {
            int i0, temp, next = arr[l0];
            for (i0 = 0; i0 < l0; i0++) {
                if (arr[i0] > next)
                    break;
            }

            for (int i1 = i0; i1 <= l0; i1++) {
                temp = arr[i1];
                arr[i1] = next;
                next = temp;
            }

            for (int i1 = 0; i1 < arr.length; i1++) {
                System.out.print(arr[i1] + " ");
            }
            System.out.println();
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        insertionSort2(n, arr);

        scanner.close();
    }
}
