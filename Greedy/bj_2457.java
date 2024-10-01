import java.io.*;
import java.util.*;

// 문제 링크: https://www.acmicpc.net/problem/2457
public class bj_2457 {

    static class Flower implements Comparable<Flower> {

        @Override
        public int compareTo(Flower o) {
            if (o.startMonth < this.startMonth) {
                return 1;
            } else if (o.startMonth == this.startMonth) {
                return this.startDay - o.startDay;
            } else {
                return -1;
            }
        }

        int startMonth;
        int startDay;
        int endMonth;
        int endDay;

        public Flower(int startMonth, int startDay, int endMonth, int endDay) {
            this.startMonth = startMonth;
            this.startDay = startDay;
            this.endMonth = endMonth;
            this.endDay = endDay;
        }
    }

    static int solution(Flower[] flowers) {
        int answer = 0;
        Arrays.sort(flowers);

        if (flowers[0].startMonth > 3 && flowers[0].startDay > 1) {
            return 0;
        }

        int prevMonth = 3;
        int prevDay = 1;
        int i = 0;
        List<Flower> flowerList;

        while (i < flowers.length) {
            flowerList = new ArrayList<>();
            while (i < flowers.length) {
                if (flowers[i].startMonth < prevMonth ||
                    (flowers[i].startMonth == prevMonth && flowers[i].startDay <= prevDay)) {
                    flowerList.add(flowers[i]);
                    i++;
                } else {
                    break;
                }
            }
            if (flowerList.isEmpty()) {
                return 0;
            }
            for (Flower flower : flowerList) {
                if (flower.endMonth > prevMonth ||
                    (flower.endMonth == prevMonth && flower.endDay > prevDay)) {
                    prevMonth = flower.endMonth;
                    prevDay = flower.endDay;
                }
            }
            answer++;
            if (prevMonth >= 12) {
                break;
            }
        }
        if (prevMonth < 12) {
            return 0;
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Flower[] flowers = new Flower[N];

        for (int i = 0; i < N; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
            flowers[i] = new Flower(input[0], input[1], input[2], input[3]);
        }

        System.out.println(solution(flowers));
    }
}
