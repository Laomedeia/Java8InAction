package leetcode.a101_symmetricTree_SIMPLE;

/**
 * 对称二叉树
 *
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *  
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 * @author neptune
 * @create 2020 05 31 9:38 下午
 */
public class Solution {

     public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
     }

    /**
     * 判断二叉树是否对称
     *
     * 若 root == null, 直接返回 true；
     * 否则，判断 root.left 与 root.right 这两棵子树是否对称：
     * 判断 root.left 与 root.right 这两个节点的值是否相等
     * 判断 root.left 的左子树与 root.right 的右子树是否对称
     * 判断 root.left 的右子树与 root.right 的左子树是否对称
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 比较 root.left 与 root.right 这两棵子树是否对称。
        return cmp(root.left, root.right);
    }

    /**
     * 递归比较节点
     * @param node1
     * @param node2
     * @return
     */
    private boolean cmp(TreeNode node1, TreeNode node2) {
        // 首先比较 node1 与 node2 这两个节点的值是否相等
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null || node1.val != node2.val) {
            return false;
        }
        // 再比较 node1 的左子树与 node2 的右子树是否对称，node1 的右子树与 node2 的左子树是否对称。
        return cmp(node1.left, node2.right) && cmp(node1.right, node2.left);
    }

}
