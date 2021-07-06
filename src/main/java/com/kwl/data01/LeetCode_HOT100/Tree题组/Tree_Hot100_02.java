package com.kwl.data01.LeetCode_HOT100.Tree题组;

import com.kwl.data01.dataStructure.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author kuang.weilin
 * @date 2021/7/6 23:55
 */
public class Tree_Hot100_02 {

    /**
     * 题目1(leetcode 第94题): 二叉树的中序遍历
     * 描述: 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
     * <p>
     * 思路01: 迭代法
     */
    public List<Integer> inorderTraversal(TreeNode root) {    //方法二：使用一个栈来进行迭代法比遍历,不需要递归
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()){   //扫描节点不是null或者栈不是空
            while (root != null) {
                stack.push(root);   //入栈
                root = root.left;     //不断扫描左边的值
            }
            root = stack.pop();    //指向出栈的节点
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    /**
     * 题目2(leetcode 第101题): 对称二叉树
     * 描述: 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     * <p>
     * 思路01: 递归
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left,root.right);
    }

    boolean isSymmetric(TreeNode root01,TreeNode root02){   //二颗树是不是一样的
        if (root01 == null && root02 == null) return true;      //全部为null,返回true
        if (root01 == null || root02 == null) return false;      //一个为null一个不为null,返回fasle
        return root01.val == root02.val && isSymmetric(root01.left,root02.right) && isSymmetric(root01.right,root02.left);
    }

    /**
     * 题目03(leetcode 第102题): 二叉树的层序遍历
     * 描述: 返回每层 [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();      //队列里面是没有Queue
        if (root != null) queue.offer(root);
        while(!queue.isEmpty()) {
            List<Integer> path = new LinkedList<>();
            int num = queue.size();
            for (int i = 0; i < num; i++) {
                TreeNode pollNode = queue.poll();
                path.add(pollNode.val);
                if (pollNode.left != null) queue.offer(pollNode.left);
                if (pollNode.right != null) queue.offer(pollNode.right);
            }
            res.add(new LinkedList<>(path));
        }
        return res;
    }
    /**
     * 题目04(leetcode 第104题): 二叉树的最大深度
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
    /**
     * 题目05(leetcode 第226题): 翻转二叉树
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
