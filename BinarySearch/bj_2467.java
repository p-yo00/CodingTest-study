package org.example.BinarySearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

// 문제 주소: https://www.acmicpc.net/problem/2467
public class bj_2467 {

    public static String findClosestFromZero(int N, int[] arr) {
        Arrays.sort(arr);
        int[] answer = {Integer.MAX_VALUE, Integer.MAX_VALUE};

        for (int i = 0; i < N-1; i++) {
            int l = i + 1;
            int r = N - 1;
            int mid;

            while (l <= r) {
                mid = l+(r-l)/2;
                long sum = arr[mid] + arr[i];
                if (sum == 0) {
                    return arr[i] + " " + arr[mid];
                } else if (sum > 0) {
                    long temp = (long) answer[0] + (long) answer[1];
                    if (Math.abs(temp) > Math.abs(sum)) {
                        answer = new int[]{arr[i], arr[mid]};
                    }
                    r = mid - 1;
                } else {
                    long temp = (long) answer[0] + (long) answer[1];
                    if (Math.abs(temp) > Math.abs(sum)) {
                        answer = new int[]{arr[i], arr[mid]};
                    }
                    l = mid + 1;
                }
            }
        }
        return answer[0] + " " + answer[1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        System.out.println(findClosestFromZero(N, arr));
    }
}
