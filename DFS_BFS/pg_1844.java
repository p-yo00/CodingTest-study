package DFS_BFS;

import java.util.ArrayDeque;

public class pg_1844 {

  public static int solution(int[][] maps) {
    int answer = -1;
    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    boolean[][] visited = new boolean[maps.length][maps[0].length];
    int[] target = {maps.length - 1, maps[0].length - 1};

    ArrayDeque<int[]> queue = new ArrayDeque<>();
    queue.add(new int[]{0, 0, 1}); // x,y,distance

    while (!queue.isEmpty()) {
      int[] cur = queue.removeFirst();

      for (int[] dir : dirs) {
        int[] next = {cur[0] + dir[0], cur[1] + dir[1], cur[2] + 1};
        if (next[0] < 0 || next[1] < 0 || next[0] >= maps.length || next[1] >= maps[0].length) {
          continue;
        }
        if (maps[next[0]][next[1]] == 0) {
          continue;
        }
        if (visited[next[0]][next[1]]) {
          continue;
        }
        if ((next[0] == target[0]) && (next[1] == target[1])) {
          answer = next[2];
          queue.clear();
          break;
        }
        visited[next[0]][next[1]] = true;
        queue.add(new int[]{next[0], next[1], next[2]});
      }
    }

    return answer;
  }

  public static void main(String[] args) {
    int[][] maps = new int[][]{
      {1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}
    };

    System.out.println(solution(maps));
  }
}
