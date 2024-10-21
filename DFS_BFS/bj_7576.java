package org.example.DFS_BFS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 문제 링크: https://www.acmicpc.net/problem/7576
public class bj_7576 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String[] input = br.readLine().split(" ");
        int M = Integer.parseInt(input[0]);
        int N = Integer.parseInt(input[1]);
        int answer = -1;

        ArrayDeque<Set<int[]>> queue = new ArrayDeque<>();

        int[][] tomatos = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        Set<int[]> curSet = new HashSet<>();
        for (int i = 0; i < N; i++) {
            tomatos[i] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < M; j++) {
                if (tomatos[i][j] == 1) {
                    curSet.add(new int[]{i, j});
                    visited[i][j] = true;
                } else if (tomatos[i][j] == -1) {
                    visited[i][j] = true;
                }
            }
        }
        queue.add(curSet);
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
        while (!queue.isEmpty()) {
            curSet = queue.removeFirst();
            if (curSet.isEmpty()) continue;
            Set<int[]> nextSet = new HashSet<>();

            for (int[] xy : curSet) {
                for (int[] d : dir) {
                    int nx = xy[0]+d[0];
                    int ny = xy[1]+d[1];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                    if (visited[nx][ny]) continue;
                    if (tomatos[nx][ny] != 0) continue;
                    visited[nx][ny] = true;
                    nextSet.add(new int[]{nx, ny});
                }
            }
            queue.add(nextSet);
            answer++;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    System.out.println(-1);
                    return;
                }
            }
        }
        System.out.println(answer);
    }
}
