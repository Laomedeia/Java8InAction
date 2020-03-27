package leetcode.a999_AvailableCapturesforRook;

/**
 * @author neptune
 * @create 2020 03 26 3:31 下午
 */
public class Solution {

    final int boardWidth = 8;
    final int boardHeight = 8;

    public int numRookCaptures(char[][] board) {
        int result = 0;
        char R = 'R';
//        char dot = '.';
        char B = 'B';
        char p = 'p';
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                if(board[i][j] == R) {
                    //向上查找
                    char searchWord;
                    int tempMinusJ = j;
                    int tempMinusI = i;
                    int tempAddJ = j;
                    int tempAddI = i;
                    while (tempMinusJ > 0) {
                        tempMinusJ -= 1;
                        searchWord = board[i][tempMinusJ];
                        if(searchWord == B) {
                            break;
                        }
                        if(searchWord == p) {
                            result++;
                            break;
                        }
                    }
                    //向下查找
                    while (tempAddJ < boardHeight - 1) {
                        tempAddJ += 1;
                        searchWord = board[i][tempAddJ];
                        if(searchWord == B) {
                            break;
                        }
                        if(searchWord == p) {
                            result++;
                            break;
                        }
                    }
                    // 向左查找
                    while (tempMinusI > 0) {
                        tempMinusI -= 1;
                        searchWord = board[tempMinusI][j];
                        if(searchWord == B) {
                            break;
                        }
                        if(searchWord == p) {
                            result++;
                            break;
                        }
                    }
                    // 向右查找
                    while (tempAddI < boardWidth - 1) {
                        tempAddI += 1;
                        searchWord = board[tempAddI][j];
                        if(searchWord == B) {
                            break;
                        }
                        if(searchWord == p) {
                            result++;
                            break;
                        }
                    }

                }
            }
         }
        return result;
    }

    public static void main(String[] args) {
//        char[][] testcase = {{'.','.','.','.','.','.','.','.'},{'.','.','.','p','.','.','.','.'},{'.','.','.','p','.','.','.','.'},{'p','p','.','R','.','p','B','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','B','.','.','.','.'},{'.','.','.','p','.','.','.','.'},{'.','.','.','.','.','.','.','.'}};
        char[][] testcase = {{'.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','R','.','.','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.'}};
        Solution solution = new Solution();
        System.out.println(solution.numRookCaptures(testcase));

    }
}
