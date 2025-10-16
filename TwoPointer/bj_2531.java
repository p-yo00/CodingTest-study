package TwoPointer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://www.acmicpc.net/problem/2531">문제 링크</a>
 * 회전 초밥 벨트에 놓인 접시의 수 N, 초밥의 가짓수 d, 연속해서 먹는 접시의 수 k, 쿠폰 번호 c
 */
public class bj_2531 {

    private static int solution(int[] arr, int k, int c) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int j = 0; j < k; j++) {
            count.put(arr[j], count.getOrDefault(arr[j], 0) + 1);
        }
        count.put(c, 1);
        int answer = count.size();

        for (int i = 1; i < arr.length; i++) {
            if (count.get(arr[i-1]) > 1) {
                count.put(arr[i-1], count.get(arr[i-1]) - 1);
            } else {
                count.remove(arr[i-1]);
            }

            count.put(arr[(i+(k-1)) % arr.length], count.getOrDefault(arr[(i+(k-1)) % arr.length], 0) + 1);
            count.put(c, 1);
            System.out.println(count);
            System.out.println(count.size());

            answer = Math.max(answer, count.size());
        }
        return answer;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int d = Integer.parseInt(input[1]);
        int k = Integer.parseInt(input[2]);
        int c = Integer.parseInt(input[3]);
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(Arrays.toString(arr));

        System.out.println(solution(arr, k, c));
    }
}
