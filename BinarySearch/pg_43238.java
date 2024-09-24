package org.example.BinarySearch;

import java.util.Arrays;

// 문제 주소 : https://school.programmers.co.kr/learn/courses/30/lessons/43238
public class pg_43238 {

    public static long solution(int n, int[] times) {
        long answer = 0;
        long left = 1;
        long right = times[0];
        for (int time : times) {
            right = Math.min(right, time);
        }
        right *= n;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long cur = 0;
            for (int time : times) {
                cur += mid/time;
                if (cur > n) break;
            }
            if (cur < n) {
                left = mid + 1;
            } else if (cur >= n) {
                answer = mid;
                right = mid - 1;
            }
        }
        return answer;
    }
}
