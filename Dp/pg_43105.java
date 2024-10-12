package org.example.Dp;

// 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/43105
public class pg_43105 {

    public int solution(int[][] triangle) {
        int answer = 0;
        int[][] dp = new int[triangle.length][];

        dp[0] = new int[]{triangle[0][0]};

        for (int i=1; i<triangle.length; i++) {
            dp[i] = new int[triangle[i].length];
            for (int j=0; j<triangle[i].length; j++) {
                if (j > 0) {
                    dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                }
                if (j < triangle[i].length-1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j] + triangle[i][j]);
                }
            }
        }

        for (int a : dp[triangle.length-1]) {
            answer = Math.max(answer, a);
        }

        return answer;
    }
}
