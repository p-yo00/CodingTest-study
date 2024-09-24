package org.example.BinarySearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class bj_2295 {

    // 이진 탐색을 쓰지만 시간복잡도가 약 O(N^2logN)으로 시간 초과
    public static int findSum1(int[] arr) {
        int left;
        int right;
        int mid;
        int goal = arr.length - 1;

        while (goal >= 3) {
            int sum = arr[goal - 1] + arr[goal - 2] + arr[goal - 3];
            if (sum < arr[goal]) {
                goal--;
                continue;
            } else if (sum == arr[goal]) {
                return arr[goal];
            }
            left = 0;
            right = goal - 1;
            mid = (left + right) / 2;

            while (left < right) {
                if (arr[left] + arr[right] >= arr[goal]) {
                    right--;
                    mid = (left + right) / 2;
                    continue;
                }
                if (arr[left] + arr[right] + arr[right - 1] < arr[goal]) {
                    left++;
                    mid = (left + right) / 2;
                    continue;
                }
                if (arr[left] + arr[left + 1] + arr[right] > arr[goal]) {
                    right--;
                    mid = (left + right) / 2;
                    continue;
                }
                while (left < mid && mid < right) {
                    sum = arr[left] + arr[right] + arr[mid];
                    int temp = mid;
                    if (sum > arr[goal]) {
                        mid = (left + mid) / 2;
                    } else if (sum < arr[goal]) {
                        mid = (right + mid) / 2;
                    } else {
                        return arr[goal];
                    }
                    if (mid == temp) {
                        break;
                    }
                }

                if (sum > arr[goal]) {
                    right--;
                } else if (sum < arr[goal]) {
                    left++;
                }
            }
            goal--;
        }
        return 0;
    }

    // 이진 탐색을 사용하진 않음 O(N^2)
    public static int findSum2(int[] arr) {
        Map<Integer, Integer> sum = new HashMap<>();
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i; j < arr.length - 1; j++) {
                sum.put(arr[i] + arr[j], 0);
            }
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (sum.containsKey(arr[i] - arr[j])) {
                    return arr[i];
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        System.out.println(findSum2(arr));
    }
}
