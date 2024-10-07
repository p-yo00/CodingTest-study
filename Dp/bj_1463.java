package org.example.Dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 문제 링크: https://www.acmicpc.net/problem/1463
public class bj_1463 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N+1];

        for (int i = 2; i < N+1; i++) {
            dp[i] = i;
            if (i/3 > 0 && i%3 == 0) {
                dp[i] = Math.min(dp[i], dp[i/3] + 1);
            }
            if (i/2 > 0 && i%2 == 0) {
                dp[i] = Math.min(dp[i], dp[i/2] + 1);
            }
            dp[i] = Math.min(dp[i], dp[i-1] + 1);
        }

        System.out.println(dp[N]);
    }
}
