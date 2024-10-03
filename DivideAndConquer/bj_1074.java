package org.example.DivideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 문제 링크: https://www.acmicpc.net/problem/1074
public class bj_1074 {

    public static class Axis {
        int x;
        int y;

        public Axis(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int getZ(int startVal, Axis start, Axis end, Axis target) {
        if (start.x == end.x) {
            return startVal;
        }

        int per = ((end.x - start.x+1)/2)*((end.x - start.x+1)/2);
        if (target.x <= (start.x+end.x)/2 && target.y <= (start.y+end.y)/2) {
            return getZ(startVal,
                new Axis(start.x, start.y),
                new Axis((start.x+end.x)/2, (start.y+end.y)/2), target);
        }
        else if (target.x <= (start.x+end.x)/2 && target.y > (start.y+end.y)/2) {
            return getZ(startVal + per,
                new Axis(start.x, (start.y+end.y)/2 + 1),
                new Axis((start.x+end.x)/2, end.y), target);
        }
        else if (target.x > (start.x+end.x)/2 && target.y <= (start.y+end.y)/2) {
            return getZ(startVal + (per*2),
                new Axis((start.x+end.x)/2+1, start.y),
                new Axis(end.x, (start.y+end.y)/2), target);
        }
        else {
            return getZ(startVal + (per*3),
                new Axis((start.x+end.x)/2+1, (start.y+end.y)/2+1),
                new Axis(end.x, end.y), target);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        int row = 1;
        for (int i = 0; i < input[0]; i++) {
            row *= 2;
        }

        System.out.println(getZ(
            0,
            new Axis(0,0),
            new Axis(row-1, row-1),
            new Axis(input[1], input[2])));
    }
}
