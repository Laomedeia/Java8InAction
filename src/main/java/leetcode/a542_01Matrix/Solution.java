package leetcode.a542_01Matrix;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 同题目a1162, 可用多源 BFS 求解
 * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
 * 两个相邻元素间的距离为 1 。
 *
 * 示例 1:
 * 输入:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * 输出:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 *
 * 示例 2:
 * 输入:
 * 0 0 0
 * 0 1 0
 * 1 1 1
 * 输出:
 * 0 0 0
 * 0 1 0
 * 1 2 1
 *
 * 注意:
 * 给定矩阵的元素个数不超过 10000。
 * 给定矩阵中至少有一个元素是 0。
 * 矩阵中的元素只在四个方向上相邻: 上、下、左、右。
 *
 * @author neptune
 * @create 2020 04 15 3:11 下午
 */
public class Solution {

    public int[][] updateMatrix(int[][] matrix) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        Queue<int[]> queue = new ArrayDeque<>();
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i,j});
                } else  {
                    matrix[i][j] = -1;  // 设置成任何值用以区别 1
                }
            }
        }

        // 开始 BFS 搜索
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0], y = point[1];
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                // 设置跳出条件，如果没有找到之前设置成 -1 的点则跳过
                if (newX < 0 || newX >= m || newY < 0 || newY >= n || matrix[newX][newY] != -1) {
                    continue;
                }
                matrix[newX][newY] = matrix[x][y] + 1; // 直接修改了原数组 如果
                queue.offer(new int[] {newX, newY});
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[][] matrix = new int[][]{{0,0,0},{0,1,0}, {0,0,0}};
        int[][] matrix = new int[][]{{0,0,0},{0,1,0}, {1,1,1}};
        matrix = solution.updateMatrix(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "    ");
            }
            System.out.println();
        }
    }

}
