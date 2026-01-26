package DP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/2293
 */
public class bj_2293 {

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
            dp[i][0] = 1;
        }
        for (int i = 0; i <= K; i++) {
            if (i >= coins[0]) {
                dp[0][i] = dp[0][i - coins[0]];
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= K; j++) {
                int coin = coins[i];

                if (j >= coin) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-coin];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        for (int[] d : dp)
            System.out.println(Arrays.toString(d));
        System.out.println(dp[N-1][K]);
    }
}
