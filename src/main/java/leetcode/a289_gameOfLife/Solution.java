package leetcode.a289_gameOfLife;

/**
 * 解题思路
 * 分析：11 代表细胞活的， 00 代表细胞死的，那么这个位置就四种状态，用【下一个状态，当前状态】表示，最后需要用右移操作更新结果
 *
 * 状态 00： 0000 ，死的，下一轮还是死的
 * 状态 11： 0101，活的，下一轮死了
 * 状态 22： 1010，死的，下一轮活了
 * 状态 33： 1111，活的，下一轮继续活着
 * 进一步：下一轮活的可能有两种，也就是要把单元格变为 11
 *
 * 这个活细胞周围八个位置有两个或三个活细胞，下一轮继续活，属于 1111
 * 这个细胞本来死的，周围有三个活着的，下一轮复活了，属于 1010
 * 那遍历下每个格子看他周围细胞有多少个活细胞就行了，然后更改为状态，那么对于第一种可能，把 board[i][j]board[i][j] 设置为 33，对于第二种可能状态设置为 22，设置个高位flag，遍历后面的格子，拿到与他相邻的格子中有多少个 alivealive 的，和 11 与一下即可，最后我们把 board[i][j]board[i][j] 右移 11位，更新结果
 *
 * 作者：jerry_nju
 * 链接：https://leetcode-cn.com/problems/game-of-life/solution/si-lu-jian-dan-yi-dong-zhu-xing-jie-shi-by-jerry_n/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @create 2020 04 02 8:26 下午
 */
class Solution {
    int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
    int[][] board;
    int m, n;

    public void gameOfLife(int[][] board) {
        this.board = board;
        // 特判
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return;
        this.m = board.length;
        this.n = board[0].length;
        // 遍历
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 拿到当前位置周围活细胞数量
                int cnt = countAlive(i, j);
                // 1. 活细胞周围八个位置有两个或三个活细胞，下一轮继续活
                if (board[i][j] == 1 && (cnt == 2 || cnt == 3)) board[i][j] = 3;
                // 2. 死细胞周围有三个活细胞，下一轮复活了
                if (board[i][j] == 0 && cnt == 3) board[i][j] = 2;
            }
        }

        // 更新结果
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;
            }
        }
    }

    private int countAlive(int x, int y) {
        int cnt = 0;
        for (int k = 0; k < 8; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
            // 如果这个位置为 0，代表当前轮是死的，不需要算进去
            // 如果这个位置为 1，代表当前轮是活得，需要算进去
            // 如果这个位置为 2，代表当前轮是死的（状态10，下一轮是活的），不需要算进去
            // 如果这个位置为 3，代表是当前轮是活的（状态11，下一轮也是活的），需要算进去
            cnt += (board[nx][ny] & 1);
        }
        return cnt;
    }
}