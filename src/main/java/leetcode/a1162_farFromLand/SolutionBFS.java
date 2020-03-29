package leetcode.a1162_farFromLand;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 广度优先搜索算法 BFS
 * https://leetcode-cn.com/problems/as-far-from-land-as-possible/solution/jian-dan-java-miao-dong-tu-de-bfs-by-sweetiee/
 * @author neptune
 * @create 2020 03 29 2:49 下午
 */
public class SolutionBFS {

        public int maxDistance(int[][] grid) {
            int[] dx = {0, 0, 1, -1};
            int[] dy = {1, -1, 0, 0};

            Queue<int[]> queue = new ArrayDeque<>();
            int m = grid.length, n = grid[0].length;
            // 先把所有的陆地都入队。
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        queue.offer(new int[] {i, j});
                    }
                }
            }

            // 从各个陆地开始，一圈一圈的遍历海洋，最后遍历到的海洋就是离陆地最远的海洋。
            boolean hasOcean = false;
            int[] point = null;
            while (!queue.isEmpty()) {
                point = queue.poll();
                int x = point[0], y = point[1];
                // 取出队列的元素，将其四周的海洋入队。
                for (int i = 0; i < 4; i++) {
                    int newX = x + dx[i];
                    int newY = y + dy[i];
                    if (newX < 0 || newX >= m || newY < 0 || newY >= n || grid[newX][newY] != 0) {
                        continue;
                    }
                    grid[newX][newY] = grid[x][y] + 1; // 这里我直接修改了原数组，因此就不需要额外的数组来标志是否访问
                    hasOcean = true;
                    queue.offer(new int[] {newX, newY});
                }
            }

            // 没有陆地或者没有海洋，返回-1。
            if (point == null || !hasOcean) {
                return -1;
            }

            // 返回最后一次遍历到的海洋的距离。 为什么-1，因为初始坐标位置的陆地值为1，多加了1
            return grid[point[0]][point[1]] - 1;

        }

    public static void main(String[] args) {
        SolutionBFS solution = new SolutionBFS();
        int[][] testdata = {{1,1,1},{1,1,1},{1,1,1},{1,1,1}};
        System.out.println(solution.maxDistance(testdata));
    }
}
