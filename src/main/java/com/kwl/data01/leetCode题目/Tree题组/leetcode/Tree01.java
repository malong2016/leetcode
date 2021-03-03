package com.kwl.data01.leetCode题目.Tree题组.leetcode;

import com.kwl.data01.dataStructure.TreeNode;

/**
 * @author kuang.weilin
 * @date 2021/3/1
 */
public class Tree01 {


    /**
     * 题目1(leetcode 543题): 二叉树的直径
     * 描述: 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
     * 描述: 就是求每一个节点 左高+右高,最后返回最大的值
     */
    int res = 0;
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
}
