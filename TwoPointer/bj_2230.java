package org.example.TwoPointer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

// 문제 링크: https://www.acmicpc.net/problem/2230
public class bj_2230 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int lp = 0;
        int rp = 0;
        int answer = Integer.MAX_VALUE;

        while (lp < N) {
            int diff = arr[rp] - arr[lp];
            if (diff >= M) {
                answer = Math.min(answer, diff);
                lp++;
                rp = lp;
                continue;
            }
            rp++;
            if (rp >= N) {
                lp++;
                rp = lp;
            }

        }
        System.out.println(answer);
    }
}
