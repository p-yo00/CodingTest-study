package org.example.BinarySearch;

import java.util.Arrays;

// 문제 주소 : https://school.programmers.co.kr/learn/courses/30/lessons/43238
public class pg_43238 {

    public static long solution(int n, int[] times) {
        Arrays.sort(times);
        long lp = 0;
        long rp = times[times.length - 1] * (long) n;
        long mid = 0;

        while (lp < rp) {
            mid = (lp + rp) / 2;
            long sum = 0;

            for (int time : times) {
                sum += mid / time;
            }

            if (sum >= n) {
                rp = mid;
            } else {
                lp = mid + 1;
            }
        }

        return (lp + rp) / 2;
    }
}
