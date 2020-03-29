package leetcode.a1162_farFromLand;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;

/**
 * 1162. 地图分析
 * 你现在手里有一份大小为 N x N 的『地图』（网格） grid，上面的每个『区域』（单元格）都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地，你知道距离陆地区域最远的海洋区域是是哪一个吗？请返回该海洋区域到离它最近的陆地区域的距离。
 * 我们这里说的距离是『曼哈顿距离』（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个区域之间的距离是 |x0 - x1| + |y0 - y1| 。
 * 如果我们的地图上只有陆地或者海洋，请返回 -1。
 *
 * 参考答案
 * @author neptune
 * @create 2020 03 29 11:49 上午
 */
public class Solution {

    class Node {
        int x;
        int y;
        int value;
        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getValue() {
            return value;
        }
        public void setValue(int value) {
            this.value = value;
        }

        public Node() {}
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public Node(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }

    public int maxDistance(int[][] grid) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        Queue<Node> queue = new ArrayDeque<>();
        int m = grid.length, n = grid[0].length;
        // 先把所有的陆地都入队。
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    Node land_node = new Node(i, j, 1);
                    queue.offer(land_node);
                }
            }
        }

        // 从各个陆地开始，一圈一圈的遍历海洋，最后遍历到的海洋就是离陆地最远的海洋。
        boolean hasOcean = false;
        Node point = null;
        while (!queue.isEmpty()) {
            point = queue.poll();
            int x = point.getX(), y = point.getY(), value = point.getValue();
            // 取出队列的元素，将其四周的海洋入队。
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (newX < 0 || newX >= m || newY < 0 || newY >= n || grid[newX][newY] != 0) {
                    continue;
                }
                grid[newX][newY] = grid[x][y] + 1; // 这里我直接修改了原数组，因此就不需要额外的数组来标志是否访问
                int newValue = value + 1;
                hasOcean = true;
                queue.offer(new Node(newX, newY, newValue));
            }
        }

        // 没有陆地或者没有海洋，返回-1。
        if (point == null || !hasOcean) {
            return -1;
        }

        // 返回最后一次遍历到的海洋的距离。 为什么-1，因为初始坐标位置的陆地值为1，多加了1
        return point.getValue() - 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] testdata = {{1,0,1},{0,0,0},{1,0,1}};
        System.out.println(solution.maxDistance(testdata));
    }
}
