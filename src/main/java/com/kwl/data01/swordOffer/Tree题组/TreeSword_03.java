package com.kwl.data01.swordOffer.Tree题组;

import com.kwl.data01.dataStructure.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author kuang.weilin
 * @date 2021/7/8 3:30
 */
public class TreeSword_03 {

    /**
     * 题目01(swordOffer 第36题): 二叉搜索树与双向链表
     * 描述: 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表.(从小到大进行排序)
     * 要求不能创建任何新的节点,只能调节树中结点的指向。
     * <p>
     * 思路01(leetcode解法):first和last结点设置为null.调用中序遍历helper(root)
     * 如果结点不为null,调用左子树递归(helper(node.left))-->这是寻找最小的最,last为null,初始化first节点
     * 不为null,将last和当前node进行结合
     * 最后first和last形成闭环,返回first
     * <p>
     * 注意:last等于一个遍历器!!不断的将结点进行扩到链表
     */
    public TreeNode last = null, first = null;

    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) return null;
        helper(root);
        last.right = first;  //首位指针形成闭环
        first.left = last;
        return first;
    }

    public void helper(TreeNode node) {       //中序遍历出来的二叉搜索树是按照顺序来排序的
        if (node != null) {
            helper(node.left);

            if (last == null) first = node;   //first第一来,那么直接就指向第一个节点(注意第一个节点也是最小值)
            else {                           //node <=> last进行链接
                last.right = node;       //注意左是指向较大值，所以是right
                node.left = last;
            }
            last = node;//last指向当前节点

            helper(node.right);
        }
    }

    /**
     * 题目02(swordOffer 第34题): 二叉树中和为某一值的路径
     * 描述: 输入一棵二叉树和一个整数,打印出二叉树中节点值的和为输入整数的所有路径。
     * 从树的root结点开始向下一直到叶结点所经过的节点形成一条路径
     * <p>
     * 思路01: 回溯法,先序遍历+路径记录.target-遍历的值,如果最后减到了0,就加入到List<Integer>
     */


    public List<List<Integer>> pathSum(TreeNode root, int target) {  //回溯法遍历找值
        List<List<Integer>> res = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        dfs(root, target, res, path);
        return res;
    }

    void dfs(TreeNode root, int target, List<List<Integer>> res, LinkedList<Integer> path) {
        if (root != null) {
            path.add(root.val);           //先进行添加
            target -= root.val;
            if (root.left == null && root.right == null && target == 0) {
                res.add(new LinkedList<>(path));
            }
            dfs(root.left, target, res, path);
            dfs(root.right, target, res, path);
            path.removeLast();
        }
    }

    /**
     * 题目03(swordOffer 第33题): 二叉搜索树的后序遍历序列
     * 描述: 输入一个整数数组(该数组没有重复元素)，判断该数组是不是某二叉搜索树的后序遍历
     * 如果是就返回true,如果不是就返回false
     * <p>
     * 思路01(swordOffer题解,分治算法): 数组中最后一个元素的搜索树的root节点,先遍历arr找到第一个大于根节点的元素
     * 该元素之前是左子树,该元素之后是右子树,遍历右子树,如果右子树存在元素小于root节点,return false,如果不存在
     * 就依次递归左右子树,当左右子树全部返回true,那么这个数组就是二叉搜索树的后序遍历
     * 左子树:(i-1)  右子树:（i...length-2)   root: length-1
     */
    public boolean verifySquenceOfBSF(int[] postOrder) {
        return recur03(postOrder, 0, postOrder.length - 1);
    }

    public boolean recur03(int[] postOrder, int l, int r) {        //数组和起始index和结束index
        if (l >= r) return true;       //注意如果没有右子树, j == m
        int p = l;
        while (postOrder[p] < postOrder[r]) p++;      //这个是找到第一个>=root元素的值,不需要设置边界
        int m = p;
        while (postOrder[p] > postOrder[r]) p++;     //p指针右移动,如果是搜索树,会顺利移动到最后一个root元素,如果不能到达就是错误的
        return p == r && recur03(postOrder, l, m - 1) && recur03(postOrder, m, r - 1);
    }

    public boolean recur04(int[] postOrder, int l, int r) {        //数组和起始index和结束index
        if (l >= r) return true;       //注意如果没有右子树, j == m
        int p = l;
        while (postOrder[p] < postOrder[r]) p++;      //这个是找到第一个>=root元素的值
        for (int i = p; i < r; i++) {
            if (postOrder[i] < postOrder[r]) return false;
        }
        return recur04(postOrder, l, p - 1) && recur04(postOrder, p, r - 1);
    }
}
