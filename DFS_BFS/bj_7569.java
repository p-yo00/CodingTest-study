package org.example.DFS_BFS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 문제 링크: https://www.acmicpc.net/problem/7569
public class bj_7569 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String[] input = br.readLine().split(" ");
        int M = Integer.parseInt(input[0]);
        int N = Integer.parseInt(input[1]);
        int H = Integer.parseInt(input[2]);
        int answer = -1;

        ArrayDeque<Set<int[]>> queue = new ArrayDeque<>();

        int[][][] tomatos = new int[H][N][M];
        boolean[][][] visited = new boolean[H][N][M];

        Set<int[]> curSet = new HashSet<>();
        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                tomatos[h][i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
                for (int j = 0; j < M; j++) {
                    if (tomatos[h][i][j] == 1) {
                        curSet.add(new int[]{h, i, j});
                        visited[h][i][j] = true;
                    } else if (tomatos[h][i][j] == -1) {
                        visited[h][i][j] = true;
                    }
                }
            }
        }

        queue.add(curSet);
        int[][] dir = {{0,1,0},{0,-1,0},{1,0,0},{-1,0,0},{0,0,1},{0,0,-1}};
        while (!queue.isEmpty()) {
            curSet = queue.removeFirst();
            if (curSet.isEmpty()) continue;
            Set<int[]> nextSet = new HashSet<>();

            for (int[] xy : curSet) {
                for (int[] d : dir) {
                    int nh = xy[0]+d[0];
                    int nx = xy[1]+d[1];
                    int ny = xy[2]+d[2];

                    if (nx < 0 || ny < 0 || nh < 0 || nx >= N || ny >= M || nh >= H) continue;
                    if (visited[nh][nx][ny]) continue;
                    if (tomatos[nh][nx][ny] != 0) continue;
                    visited[nh][nx][ny] = true;
                    nextSet.add(new int[]{nh, nx, ny});
                }
            }
            queue.add(nextSet);
            answer++;
        }

        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (!visited[h][i][j]) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
