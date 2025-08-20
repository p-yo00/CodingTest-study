package Greedy;

import java.util.Arrays;

public class pg_42885 {

  public static int solution(int[] people, int limit) {
    int answer = 0;
    int lPoint = 0;
    int rPoint = people.length - 1;

    Arrays.sort(people);

    while (lPoint <= rPoint) {
      answer++;
      if (lPoint == rPoint) {
        break;
      }
      if (people[lPoint] + people[rPoint] > limit) {
        rPoint--;
      } else {
        rPoint--;
        lPoint++;
      }
    }

    return answer;
  }

  public static void main(String[] args) {
    int[] maps = { 70, 80, 50 };

    System.out.println(solution(maps, 100));
  }
}
