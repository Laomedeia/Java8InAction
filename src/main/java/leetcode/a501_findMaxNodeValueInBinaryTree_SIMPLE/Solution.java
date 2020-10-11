package leetcode.a501_findMaxNodeValueInBinaryTree_SIMPLE;

/**
 * 二叉搜索树中的众数
 *
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 * 假定 BST 有如下定义：
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树

 * 例如：
 * 给定 BST [1,null,2,2],
 *    1
 *     \
 *      2
 *     /
 *    2
 * 返回[2].
 *
 * 提示：如果众数超过1个，不需考虑输出顺序
 *
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 *
 *
 * @create 2020 09 24 9:29 下午
 */
public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private TreeNode pre = null;    // 前驱节点
    private int[] result;   // 结果数组
    private int resultCount = 0;    // 结果个数
    private int maxCount = 0;   // 众数数量
    private int currCount = 0;  // 当前重复的数的数量

    public int[] findMode(TreeNode root) {
        findAndFill(root);  // 第一轮，查询 “众数个数”

        // 复位
        this.pre = null;
        this.result = new int[this.resultCount];    // 初始化数组
        this.resultCount = 0;
        this.currCount = 0;

        findAndFill(root);  // 第二轮，填充 众数
        return this.result;
    }

    /**
     * 中根序 遍历 目标二叉树<br/>
     *
     */
    private void findAndFill(TreeNode root) {
        if (root == null) {
            return;
        }
        findAndFill(root.left); // 递归遍历 左子树

        if (this.pre != null && this.pre.val == root.val) { // 与前一个节点的值相等
            this.currCount++;
        } else {
            this.currCount = 1;  // 若 不相等，则 刷新currCount
        }

        if (this.currCount > this.maxCount) {   // 当前最大数 > 最大众数数
            this.maxCount = this.currCount;
            this.resultCount = 1;
        } else if (this.currCount == this.maxCount) {   // 当前最大数 == 最大众数数
            if (this.result != null) {
                this.result[this.resultCount] = root.val;
            }
            this.resultCount++;  // 使 指针向后移动，便于下次录入
        }

        // 进行下轮遍历
        this.pre = root;
        findAndFill(root.right);    // 递归遍历 右子树
    }

}
