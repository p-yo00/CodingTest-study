package org.example.ShortestPath;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// 문제 경로: https://www.acmicpc.net/problem/1753
public class bj_1753 {

    public static class Graph implements Comparable<Graph> {

        int val;
        int w;

        public Graph(int v, int w) {
            this.val = v;
            this.w = w;
        }

        @Override
        public int compareTo(Graph g) {
            return this.w - g.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String[] input = br.readLine().split(" ");
        int V = Integer.parseInt(input[0]);
        int E = Integer.parseInt(input[1]);
        int start = Integer.parseInt(br.readLine());

        Map<Integer, PriorityQueue<Graph>> graph = new HashMap<>();
        for (int i = 0; i < E; i++) {
            input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            int w = Integer.parseInt(input[2]);

            PriorityQueue<Graph> link = graph.getOrDefault(u, new PriorityQueue<>());
            link.add(new Graph(v, w));

            graph.put(u, link);
        }

        int[] path = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            path[i] = Integer.MAX_VALUE;
        }
        path[start] = 0;

        PriorityQueue<Graph> queue = new PriorityQueue<>();
        queue.add(new Graph(start, 0));

        while (!queue.isEmpty()) {
            Graph g = queue.remove();

            for (Graph next : graph.getOrDefault(g.val, new PriorityQueue<>())) {
                if (path[next.val] > path[g.val] + next.w) {
                    path[next.val] = path[g.val] + next.w;
                    queue.add(new Graph(next.val, path[next.val]));
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if (path[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(path[i]);
            }
        }
    }
}
