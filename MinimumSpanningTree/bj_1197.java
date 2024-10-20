package org.example.MinimumSpanningTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// 문제 링크: https://www.acmicpc.net/problem/1197
public class bj_1197 {
    public static class Graph implements Comparable<Graph>{

        int u;
        int v;
        int w;

        public Graph(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Graph o) {
            return this.w - o.w;
        }
    }

    public static void union(int[] parent, int u, int v) {
        u = find(parent, u);
        v = find(parent, v);

        if (u > v) {
            parent[u] = v;
        } else {
            parent[v] = u;
        }
    }

    public static int find(int[] parent, int u) {
        if (parent[u] == u) {
            return u;
        }
        return find(parent, parent[u]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String[] input = br.readLine().split(" ");
        int V = Integer.parseInt(input[0]);
        int E = Integer.parseInt(input[1]);

        List<Graph> graphs = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            int w = Integer.parseInt(input[2]);

            graphs.add(new Graph(u, v, w));
        }
        graphs.sort(Comparator.naturalOrder());
        int[] parent = new int[V+1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }
        int cnt = 0;
        long pathSum = 0;

        for (Graph g : graphs) {
            if (find(parent, g.u) == find(parent, g.v)) continue;
            union(parent, g.u, g.v);
            pathSum += g.w;

            cnt++;
            if (cnt >= V-1) break;
        }

        System.out.println(pathSum);
    }
}
