package org.example.Greedy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

// 문제 링크: https://www.acmicpc.net/problem/1911
public class bj_1911 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int L = Integer.parseInt(input[1]);
        int answer = 0;

        int[][] water = new int[N][2];
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            water[i][0] = Integer.parseInt(input[0]);
            water[i][1] = Integer.parseInt(input[1]);
        }

        Arrays.sort(water, (o1, o2) -> o1[0] - o2[0]);

        int cur = 0;
        for (int[] w : water) {
            int start = Math.max(w[0], cur);
            int end = w[1];
            if (start >= end) continue;

            int distance = end - start;
            int board = (distance % L != 0) ? distance / L + 1 : distance / L;
            cur = start + (L * board);
            answer += board;
        }
        System.out.println(answer);
    }
}
