package com.kwl.data01.LeetCode_HOT100.Tree题组;

import com.kwl.data01.dataStructure.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author kuang.weilin
 * @date 2021/3/1
 */
public class Tree_Hot100_01 {


    /**
     * 题目1(leetcode 543题): 二叉树的直径
     * 描述: 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
     * 描述: 就是求每一个节点 左高+右高,最后返回最大的值
     */
    int res = 0;        //这个res一定是要写在外面,因为递归是值传递(如果不是引用的化)，会丢失
    public int diameterOfBinaryTree(TreeNode root) { //等于所有节点leftHigh+rightHigh的max值
        if(root == null) return 0;
        countTreeHigh(root);
        return res;
    }
    int countTreeHigh(TreeNode root){
        if(root == null) return 0;
        int leftHigh = countTreeHigh(root.left);
        int rightHigh = countTreeHigh(root.right);
        res = Math.max(leftHigh+rightHigh, res);
        return Math.max(leftHigh, rightHigh)+1;
    }

    /**
     * 题目2(leetcode 543题): 二叉树展开为链表
     * 描述: 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     * 1 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 2 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     *
     * 思路01: 设置一个List<TreeNode> list，对root进行先序遍历，
     * 把节点按照顺序加入到list中，在遍历list指针，把节点串成新的链表
     * 思路02(递归法): 利用右子树-->左子树-->root(后序遍历的逆序,不会丢失孩子节点),设置全局变量pre，保存
     * 遍历指针的right节点
     */
    public void flatten(TreeNode root) {
        List<TreeNode> list = new LinkedList<>();
        dfs(root, list);
        for (int i = 1; i < list.size(); i++) {
            root.right = list.get(i);
            root.left = null;
            root = root.right; //root链表的下一个指针
        }
    }

    void dfs(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            dfs(root.left,list);
            dfs(root.right,list);
        }
    }

    private TreeNode pre = null;

    public void flatten01(TreeNode root) {   //逆序的后序遍历,pre是当前遍历指针的下一个节点
        if (root == null)
            return;
        flatten(root.right);
        flatten(root.left);
        root.right = pre;
        root.left = null;     //因为已经遍历了，所以不用担心节点的丢失
        pre = root;
    }

    

}
