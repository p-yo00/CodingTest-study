package org.example.TwoPointer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

// 문제 주소: https://www.acmicpc.net/problem/1806
public class bj_1806 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int S = Integer.parseInt(input[1]);
        int[] arr = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        int answer = N+1;
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = i; j < N; j++) {
                sum += arr[j];
                if (j-i+1 >= answer) break;
                if (sum >= S) {
                    answer = j-i+1;
                    break;
                }
            }
        }

        if (answer == N+1) {
            System.out.println(0);
        } else {
            System.out.println(answer);
        }
    }
}
