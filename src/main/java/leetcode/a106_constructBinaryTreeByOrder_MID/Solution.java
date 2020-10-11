package leetcode.a106_constructBinaryTreeByOrder_MID;

import java.util.HashMap;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * @create 2020 09 25 11:39 下午
 */
public class Solution {

    public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
   }

    HashMap<Integer,Integer> inorderArrayMap = new HashMap<>();
    int[] post;

    /**
     * 树的还原过程描述
     * 根据中序遍历和后续遍历的特性我们进行树的还原过程分析
     *
     * 首先在后序遍历序列中找到根节点(最后一个元素)
     * 根据根节点在中序遍历序列中找到根节点的位置
     * 根据根节点的位置将中序遍历序列分为左子树和右子树
     * 根据根节点的位置确定左子树和右子树在中序数组和后续数组中的左右边界位置
     * 递归构造左子树和右子树
     * 返回根节点结束
     *
     * 作者：reals
     * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/solution/tu-jie-gou-zao-er-cha-shu-wei-wan-dai-xu-by-user72/
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for(int i = 0;i < inorder.length; i++) {
            inorderArrayMap.put(inorder[i], i); //将节点值及索引全部记录在哈希表中
        }

        post = postorder;
        TreeNode root = buildTree(0, inorder.length - 1, 0, post.length - 1);
        return root;
    }

    public TreeNode buildTree(int inorderStart, int inorderEnd, int postorderStart, int postorderEnd) {
        if(inorderEnd < inorderStart || postorderEnd < postorderStart) return null;

        int root = post[postorderEnd];//根据后序遍历结果，取得根节点
        int rootIndexInInorderArray = inorderArrayMap.get(root);//获取对应的索引

        TreeNode node = new TreeNode(root);//创建该节点
        node.left = buildTree(inorderStart, rootIndexInInorderArray - 1, postorderStart, postorderStart + rootIndexInInorderArray - inorderStart - 1);
        node.right = buildTree(rootIndexInInorderArray + 1, inorderEnd, postorderStart + rootIndexInInorderArray - inorderStart, postorderEnd - 1);
        return node;    //返回新建的node
    }
}
