package com.kwl.data01.leetCode题目.Tree题组.swordOffer;

import com.kwl.data01.dataStructure.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * swordOffer 二叉树(BinaryTreeNode)题组02(每个题组是4个题目)
 *
 * @author kuang.weilin
 * @date 2021/2/16
 */
public class TreeSword_02 {


    /**
     * 题目1(swordOffer 面试题54):二叉搜索树的第k大的节点
     * 描述: 给定一个二叉搜索树(左节点<root节点<右节点),请找到其中第k大的节点。
     * <p>
     * 思路01: 利用中序遍历这颗二叉搜索树,得到的顺序就是从小到大的排序,
     * 就可以找到第k大的节点
     */
    TreeNode res;
    int k;

    TreeNode kthNode(TreeNode root, int k) {
        this.k = k;
        inorder(root);
        return res;
    }

    public void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.right);
        if (k == 0) return;
        if (--k == 0) res = root;      //返回指定的节点
        inorder(root.left);
    }

    /**
     * 题目2(swordOffer 面试题36):二叉搜索树和双向链表
     * 描述: 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表.
     * 要求不能创建任何新的节点,只能调节树中结点的指向。
     * <p>
     * 思路01(leetcode解法):first和last结点设置为null.调用中序遍历helper(root)
     * 如果结点不为null,调用左子树递归(helper(node.left))-->这是寻找最小的最,last为null,初始化first节点
     * 不为null,将last和当前node进行结合
     * 最后first和last形成闭环,返回first
     * <p>
     * 注意:last等于一个遍历器!!不断的将结点进行扩到链表
     */
    public static TreeNode last = null, first = null;

    public static void helper(TreeNode node) {
        if (node != null) {
            helper(node.left);

            if (last == null) first = node;   //如果last为null,就初始化first第一次来
            else {                           //node <=> last进行链接
                last.right = node;
                node.left = last;
            }
            last = node;//last指向当前节点

            helper(node.right);
        }
    }

    public TreeNode treeToDoublyList(TreeNode root) {
        if (root != null) {
            helper(root);
            last.right = first;  //首位指针形成闭环
            first.left = last;
            return first;
        }
        return root;
    }

    /**
     * 题目3(swordOffer 面试题34):二叉树中和为某一值的路径
     * 描述: 输入一棵二叉树和一个整数,打印出二叉树中节点值的和为输入整数的所有路径。
     * 从树的root结点开始向下一直到叶结点所经过的节点形成一条路径
     * <p>
     * 思路01: 回溯法,先序遍历+路径记录.target-遍历的值,如果最后减到了0,就加入到List<Integer>
     */
    LinkedList<List<Integer>> res01 = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        recur(root, sum);
        return res01;
    }

    void recur(TreeNode root, int target) {       //先序遍历
        if (root == null) return;
        path.add(root.val);       //添加到path(路径)中
        target -= root.val;       //target减去添加的值
        if (root.left == null && root.right == null && target == 0)
            res01.add(new LinkedList<>(path));    //这个节点是叶结点,而且target为0
        recur(root.left, target);
        recur(root.right, target);
        path.removeLast();
    }

    /**
     * 题目4(swordOffer 面试题33): 二叉搜索树的后序遍历序列
     * 描述: 输入一个整数数组(该数组没有重复元素)，判断该数组是不是某二叉搜索树(中序遍历从小到大输出)的后序遍历
     * 如果是就返回true,如果不是就返回false
     * <p>
     * 思路01(swordOffer题解,分治算法): 数组中最后一个元素的搜索树的root节点,先遍历arr找到第一个大于根节点的元素
     * 该元素之前是左子树,该元素之后是右子树,遍历右子树,如果右子树存在元素小于root节点,return false,如果不存在
     * 就依次递归左右子树,当左右子树全部返回true,那么这个数组就是二叉搜索树的后序遍历
     * 左子树:(i-1)  右子树:（i...length-2)   root: length-1
     */
    public static boolean verifySquenceOfBSF(int[] postOrder) {
        return recur03(postOrder, 0, postOrder.length - 1);
    }

    public static boolean recur03(int[] postOrder, int i, int j) {        //数组和起始index和结束index
        if (i>=j) return true;       //注意如果没有右子树, j == m
        int p = i;
        while (postOrder[p]<postOrder[j]) p++;      //这个是找到第一个>=root元素的值
        int m = p;
        while (postOrder[p]>postOrder[j]) p++;     //p指针右移动,如果是搜索树,会顺利移动到最后一个root元素
        return p == j && recur03(postOrder, i, m - 1)&&recur03(postOrder,m,j-1);
    }


    public static void main(String[] args) {
        TreeSword_02 treeSword = new TreeSword_02();

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);

        System.out.println(treeSword.pathSum(root, 3));

        System.out.println("题目4(swordOffer 面试题33): 二叉搜索树的后序遍历序列");
        System.out.println(verifySquenceOfBSF(new int[]{20, 1, 3}));


    }
}
