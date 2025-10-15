package DFS_BFS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://www.acmicpc.net/problem/12919">문제 링크</a>
 * 뒤에 A를 더하거나 B를 더한 후 뒤집어서 S -> T로 바꿀 수 있으면 1을, 아니면 0을 출력
 * - B를 A로 만드는걸로 바꿔서 경우의 수를 축소할 수 있는 문제
 */
public class bj_12919 {

    public static void solution(String S, String T) {
        Deque<StringBuilder> queue = new ArrayDeque<>();
        queue.add(new StringBuilder(T));

        while (!queue.isEmpty()) {
            System.out.println(queue);
            StringBuilder T2 = queue.removeFirst();

            if (T2.toString().equals(S)) {
                System.out.println(1);
                return;
            }
            if (T2.length() < S.length()) {
                System.out.println(0);
                return;
            }
            if (T2.length() == S.length()) {
                continue;
            }

            if (T2.charAt(T2.length()-1) == 'A') {
                StringBuilder addA = new StringBuilder(T2);
                addA.deleteCharAt(addA.length() - 1);
                queue.add(addA);
            }
            if (T2.charAt(0) == 'B') {
                StringBuilder addB = new StringBuilder(T2);
                addB.reverse();
                addB.deleteCharAt(addB.length() - 1);
                queue.add(addB);
            }
        }
        System.out.println(0);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String S = br.readLine();
        String T = br.readLine();

        solution(S, T);
    }
}
