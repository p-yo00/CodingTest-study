package DP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/12865 평범한 배낭 (냅색)
 */
public class bj_12865 {

    public static class Item {
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "weight=" + weight +
                    ", value=" + value +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstInput = br.readLine().split(" ");
        int N = Integer.parseInt(firstInput[0]);
        int K = Integer.parseInt(firstInput[1]);

        Item[] items = new Item[N];
        for (int i = 0; i < N; i++) {
            String[] nInput = br.readLine().split(" ");
            items[i] = new Item(Integer.parseInt(nInput[0]),
                    Integer.parseInt(nInput[1]));
        }

        int[][] dp =  new int[N][K+1];
        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= K; j++) {
                if (i == 0) {
                    if (items[i].weight <= j) {
                        dp[i][j] = items[i].value;
                    }
                    continue;
                }
                if (items[i].weight <= j) {
                    dp[i][j] = Math.max(
                            dp[i-1][j],
                            dp[i-1][j-items[i].weight] + items[i].value);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
    }
}
