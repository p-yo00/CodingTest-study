package org.example.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 문제 링크: https://www.acmicpc.net/problem/3109
public class bj_3109 {

    public static boolean dfs(boolean[][] map, int i, int j, int R, int C) {
        if (j == C-1) {
            return true;
        }
        for (int k = -1; k <= 1; k++) {
            if (i+k >= 0 && i+k < R) {
                if (map[i+k][j+1]) continue;
                map[i+k][j+1] = true;
                if (dfs(map, i+k, j+1, R, C)) {
                    return true;
                }
                // map[i+k][j+1] = false; -> 한번 가서 실패한 경로는 다시 가도 도달할 수 없으므로 방문했다고 봐도 됨
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt).toArray();
        int R = input[0];
        int C = input[1];
        boolean[][] map = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                if (line.charAt(j) == '.')
                    map[i][j] = false;
                else
                    map[i][j] = true;
            }
        }
        int answer = 0;

        for (int i = 0; i < R; i++) {
            if (dfs(map, i, 0, R, C)) answer++;
        }
        System.out.println(answer);
    }
}
