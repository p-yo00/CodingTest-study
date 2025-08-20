package Heap;

import java.util.PriorityQueue;

public class pg_42626 {

  public static int solution(int[] scoville, int K) {
    int answer = 0;
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    for (int sco : scoville) {
      pq.add(sco);
    }

    while (pq.peek() < K) {
      if (pq.size() <= 1) {
        answer = -1;
        break;
      }
      int min1 = pq.remove();
      int min2 = pq.remove();
      int newFood = min1 + (min2 * 2);

      pq.add(newFood);
      answer++;
    }

    return answer;
  }

  public static void main(String[] args) {
    int[] maps = new int[]{
      1, 2, 3, 9, 10, 12
    };

    System.out.println(solution(maps, 7));
  }
}
