package DP;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * https://www.acmicpc.net/problem/2294
 */
public class bj_2294 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstInput = br.readLine().split(" ");
        int N = Integer.parseInt(firstInput[0]);
        int K = Integer.parseInt(firstInput[1]);

        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[N][K+1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= K; j++) {
                if (j == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= K; j++) {
                if (i > 0) {
                    if (j >= coins[i]) {
                        if (dp[i][j-coins[i]] != Integer.MAX_VALUE) {
                            dp[i][j] = Math.min(dp[i-1][j], dp[i][j-coins[i]] + 1);
                        } else {
                            dp[i][j] = dp[i-1][j];
                        }
                    } else {
                        dp[i][j] = dp[i-1][j];
                    }
                } else if (j >= coins[i]) {
                    if (dp[i][j-coins[i]] != Integer.MAX_VALUE) {
                        dp[i][j] = dp[i][j-coins[i]] + 1;
                    }
                }
            }
        }

        System.out.println((dp[N-1][K] == Integer.MAX_VALUE) ? -1 : dp[N-1][K]);
    }
}
