package org.example.MinimumSpanningTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// 문제 링크: https://www.acmicpc.net/problem/17472
public class bj_17472 {

    public static int find(int[] parent, int x) {
        if (parent[x] == x) {
            return x;
        }
        return find(parent, parent[x]);
    }

    public static void union(int[] parent, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);

        if (x > y) {
            parent[x] = y;
        } else {
            parent[y] = x;
        }
    }

    public static void coloring(int row, int col, int[][] map, int x, boolean[][] visited) {
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int[] d : dir) {
            int nx = row + d[0];
            int ny = col + d[1];

            if (nx < 0 || ny < 0 || nx >= map.length || ny >= map[0].length) {
                continue;
            }
            if (visited[nx][ny]) {
                continue;
            }
            if (map[nx][ny] != 1) {
                continue;
            }

            map[nx][ny] = x;
            visited[nx][ny] = true;
            coloring(nx, ny, map, x, visited);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        int x = 2;
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 1) {
                    continue;
                }
                if (visited[i][j]) {
                    continue;
                }
                visited[i][j] = true;
                map[i][j] = x;
                coloring(i, j, map, x, visited);
                x++;
            }
        }

        Map<Integer, Map<Integer, Integer>> paths = new HashMap<>();
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                for (int[] d : dir) {
                    int nx = i + d[0];
                    int ny = j + d[1];

                    if (nx < 0 || ny < 0 || nx >= map.length || ny >= map[0].length) {
                        continue;
                    }
                    if (map[nx][ny] != 0) {
                        continue;
                    }

                    int w = 0;
                    while (map[nx][ny] == 0) {
                        nx += d[0];
                        ny += d[1];

                        if (nx < 0 || ny < 0 || nx >= map.length || ny >= map[0].length) {
                            break;
                        }
                        w++;

                        if (map[nx][ny] != 0) {
                            if (w <= 1) {
                                break;
                            }

                            Map<Integer, Integer> path = paths.getOrDefault(map[i][j],
                                new HashMap<>());
                            int min = Math.min(path.getOrDefault(map[nx][ny], Integer.MAX_VALUE),
                                w);
                            path.put(map[nx][ny], min);
                            paths.put(map[i][j], path);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(paths);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

        for (int node1 : paths.keySet()) {
            for (int node2 : paths.get(node1).keySet()) {
                pq.add(new int[]{node1, node2, paths.get(node1).get(node2)});
            }
        }

        int[] parent = new int[x];
        for (int i = 0; i < x; i++) {
            parent[i] = i;
        }

        int answer = 0;
        while (!pq.isEmpty()) {
            int[] p = pq.remove();
            if (find(parent, p[0]) == find(parent, p[1])) {
                continue;
            }
            union(parent, p[0], p[1]);
            answer += p[2];
        }
        System.out.println(x);
        for (int i = 2; i < x - 1; i++) {
            if (find(parent, i) != find(parent, i + 1)) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(answer);
    }
}
