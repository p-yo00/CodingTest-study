package org.example.DivideAndConquer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 문제 링크: https://www.acmicpc.net/problem/1780
public class bj_1780 {
    static int[][] matrix;
    static Map<Integer, Integer> answer = new HashMap<>();

    public static Map<Integer, Integer> getPaperNum(int[] row, int[] col) {
        int val = matrix[row[0]][col[0]];
        int N = row[1] - row[0] + 1;
        boolean equalCheck = false;
        for (int i = row[0]; i <= row[1]; i++) {
            for (int j = col[0]; j <= col[1]; j++) {
                if (matrix[i][j] != val) {
                    equalCheck = true;
                    break;
                }
            }
            if (equalCheck) {
                break;
            }
        }
        if (!equalCheck) {
            answer.put(val, answer.getOrDefault(val, 0) + 1);
            return answer;
        }

        int unit = N/3;
        for (int i = row[0]; i <= row[1]; i+=unit) {
            for (int j = col[0]; j <= col[1]; j+=unit) {
                getPaperNum(new int[]{i, i+unit-1}, new int[]{j, j+unit-1});
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        int N = Integer.parseInt(br.readLine());
        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(input[j]);
            }
        }
        getPaperNum(new int[]{0, N-1}, new int[]{0, N-1});
        for (int i = -1; i <= 1; i++) {
            System.out.println(answer.getOrDefault(i, 0));
        }
    }
}
