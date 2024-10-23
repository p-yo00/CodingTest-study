package org.example.DFS_BFS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class bj_2606 {

    public static void dfs(Map<Integer, List<Integer>> computers, boolean[] visited, int x) {
        List<Integer> nodes = computers.get(x);
        if (nodes == null) return;

        for (int node : nodes) {
            if (visited[node]) continue;
            visited[node] = true;
            dfs(computers, visited, node);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        int N = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());
        Map<Integer, List<Integer>> computers = new HashMap<>();
        for (int i = 0; i < E; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt)
                .toArray();
            List<Integer> nodes = computers.getOrDefault(input[0], new ArrayList<>());
            nodes.add(input[1]);
            computers.put(input[0], nodes);

            List<Integer> nodes2 = computers.getOrDefault(input[1], new ArrayList<>());
            nodes2.add(input[0]);
            computers.put(input[1], nodes2);
        }

        boolean[] visited = new boolean[N+1];
        visited[1] = true;
        dfs(computers, visited, 1);

        int answer = 0;
        for (int i = 2; i < N+1; i++) {
            if (visited[i]) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}
