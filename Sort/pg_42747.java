import java.util.*;

class pg_42747 {
    public int solution(int[] citations) {
        int answer = 0;
        int left = 0;
        int right = citations.length ;
        int mid = left + (right - left) / 2;

        int cnt = 0;
        while (left <= right) {
            cnt = 0;
            mid = left + (right - left) / 2;
            for (int citation : citations) {
                if (citation >= mid) {
                    cnt++;
                }
            }
            if (cnt == mid) {
                break;
            } else if (cnt < mid) {
                right = mid-1;
            } else {
                answer = mid;
                left = mid+1;
            }
        }
        if (cnt >= mid) {
            answer =mid;
        }
        return answer;
    }
}