package org.example.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 문제 링크: https://www.acmicpc.net/problem/15649
public class bj_15649 {
    public static void solution(int N, int M, int cur, boolean[] visited, int[] arr) {
        if (cur == M) {
            for (int a : arr) {
                System.out.print(a + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;

            arr[cur] = i;
            visited[i] = true;
            solution(N, M, cur+1, visited, arr);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        solution(N, M, 0, new boolean[N+1], new int[M]);
    }
}
