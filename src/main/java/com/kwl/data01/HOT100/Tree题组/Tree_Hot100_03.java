package com.kwl.data01.HOT100.Tree题组;

import com.kwl.data01.dataStructure.TreeNode;

import java.util.HashMap;

/**
 * @author kuang.weilin
 * @date 2021/7/7 21:59
 */
public class Tree_Hot100_03 {

    /**
     * 题目01(leetcode 第619题): 合并二叉树          --有点像链表那个融合
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

    /**
     * 题目02(leetcode 第105题): 从前序与中序遍历序列构造二叉树         --本题和swordOffer 第07题 一样
     * 描述:输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * <p>
     * eg:
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     * <p>
     * 思路01: 根据前序遍历第一个值,在中序遍历的地方，可以知道左右子树！！！
     * 将前序遍历的中序遍历加入到map中,比较难的是pre的中间!!!index,要计算左子树的长度
     */
    HashMap<Integer, Integer> map = new HashMap<>();     //key代表int[]的值,value是对应arr中的索引

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) return null;       //有一个为null，就直接返回
        for (int i = 0; i < inorder.length; i++) {       //将中序遍历注入到map中,也可以顺序查找
            map.put(inorder[i], i);
        }
        return dfs(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    TreeNode dfs(int[] preorder, int[] inorder, int preL, int preR, int iL, int iR) {// 设置好边界
        if (preL > preR) return null;        //注意这里是可以等于的,等于是一个节点
        int rootValue = preorder[preL];
        Integer iRoot = map.get(rootValue);      //获取到root在中序遍历的值
        int lLen = iRoot - iL;     //获取到左子树的长度

        TreeNode root = new TreeNode(rootValue);          //构建新树root
        root.left = dfs(preorder, inorder,preL + 1,preL + lLen,iL,iRoot -1);       //左子树
        root.right = dfs(preorder, inorder, preL + lLen + 1, preR, iRoot + 1, iR);       //有子树
        return root;
    }

}
