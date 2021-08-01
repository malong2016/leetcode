package com.kwl.data01.HOT100.Tree题组;

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
     * 题目01(leetcode 第94题): 二叉树的中序遍历
     * 描述: 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
     * <p>
     * 思路01: 迭代法
     */
    public List<Integer> inorderTraversal(TreeNode root) {    //方法二：使用一个栈来进行迭代法比遍历,不需要递归
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {   //扫描节点不是null或者栈不是空
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
     * 题目02(leetcode 第101题): 对称二叉树
     * 描述: 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     * <p>
     * 思路01: 递归
     * 思路02: 利用一个队列进行迭代法，每次都是入二个节点
     */
    public boolean isSymmetric(TreeNode root) { // 递归法
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    boolean isSymmetric(TreeNode root01, TreeNode root02) {   //二颗树是不是一样的
        if (root01 == null && root02 == null) return true;      //全部为null,返回true
        if (root01 == null || root02 == null) return false;      //一个为null一个不为null,返回fasle
        return root01.val == root02.val && isSymmetric(root01.left, root02.right) && isSymmetric(root01.right, root02.left);
    }

    public boolean isSymmetric02(TreeNode root) {       //迭代法
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            if (node1 == null && node2 == null) {
                continue;
            }
            if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }
            queue.offer(node1.left);
            queue.offer(node2.right);
            queue.offer(node1.right);
            queue.offer(node2.left);
        }
        return true;
    }


    /**
     * 题目03(leetcode 第102题): 二叉树的层序遍历
     * 描述: 返回每层 [
     * [3],
     * [9,20],
     * [15,7]
     * ]
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();     //注意这里是TreeNode
        if (root != null) queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> path = new LinkedList<>();    //每次都是新的地址，所以res.add(直接加)
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode pollNode = queue.poll();
                path.add(pollNode.val);
                if (pollNode.left != null) queue.offer(pollNode.left);
                if (pollNode.right != null) queue.offer(pollNode.right);
            }
            res.add(path);
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

    /**
     * 题目06(leetcode 第543题): 二叉树的直径
     * 描述: 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
     * 描述: 就是求每一个节点 左高+右高,最后返回最大的值
     */
    int res01 = 0;

    public int diameterOfBinaryTree(TreeNode root) {    //这里是返回的是res，而高度是返回的高度，所以要取
        treeDepth(root);
        return res01;
    }

    int treeDepth(TreeNode root) {       //在返回高度的同时，对res进行更新
        if (root == null) return 0;
        int lDepth = treeDepth(root.left);      //先要记录一下左右深度,因为要求最高，不断的比较
        int rDepth = treeDepth(root.right);
        res01 = Math.max(lDepth + rDepth, res01);
        return Math.max(lDepth, rDepth) + 1;
    }

    /**
     * 题目07(leetcode 第538题): 把二叉搜索树转换为累加树
     * 描述: 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
     * <p>
     * 提醒一下，二叉搜索树满足下列约束条件：
     * <p>
     * 节点的左子树仅包含键 小于 节点键的节点。
     * 节点的右子树仅包含键 大于 节点键的节点。
     * 左右子树也必须是二叉搜索树。
     * <p>
     * 思路01: 反中序遍历,先遍历右子树，累加。
     */
    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;        //把right子树+本root节点的值全部累加
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }

    /**
     * 题目08(leetcode 第619题): 合并二叉树          --有点像链表那个融合
     * 描述:给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
     * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
     * <p>
     * 思路01: 不开辟新的空间，累加到root1
     * 思路02: 开辟新的空间，合成一棵新的数
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {    //累加到root01
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        root1.val += root2.val;     //累加值
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }

    public TreeNode mergeTrees01(TreeNode root1, TreeNode root2) {     //开辟新的值
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        TreeNode newNode = new TreeNode(root1.val + root2.val);
        newNode.left = mergeTrees01(root1.left, root2.left);
        newNode.right = mergeTrees01(root1.right, root2.right);
        return newNode;
    }

}
