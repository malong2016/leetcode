package com.kwl.data01.leetCode题目.Tree题组.swordOffer;

import com.kwl.data01.dataStructure.TreeNode;

import java.util.Map;

/**
 * @author kuang.weilin
 * @date 2021/2/14
 */
public class TreeSword_01 {


    /**
     * 题目1(swordOffer 面试题55):求二叉树的深度    time:2月14号
     * <p>
     * 思路01: 利用递归,如果rootNode为null,就返回0.否则就返回左右节点更高的+1
     * 思路02: 利用二叉树层次遍历,参考,求二叉树每层的平均值
     */
    public static int treeDepth(TreeNode rootNode) {
        if (rootNode == null) return 0;
        return Math.max(treeDepth(rootNode.left), treeDepth(rootNode.right)) + 1;
    }

    /**
     * 题目2(swordOffer 面试题26):求树的子结构   time:2月14号
     * 描述: 输入二颗二叉树A和B,判断B是不是A的子结构
     * <p>
     * 注意:leetcode有更好的解法
     */
    boolean hasSubTree(TreeNode pRoot1, TreeNode pRoot2) {
        boolean result = false;
        if (pRoot1 != null && pRoot2 != null) {
            if (pRoot1.val == pRoot2.val) {
                result = doesTree1HaveTree2(pRoot1, pRoot2);
            }
            if (!result) {
                return hasSubTree(pRoot1.left, pRoot2);
            }
            if (!result) {
                return hasSubTree(pRoot1.right, pRoot2);
            }
        }
        return result;
    }

    boolean doesTree1HaveTree2(TreeNode pRoot1, TreeNode pRoot2) {
        if (pRoot2 == null) return true;
        if (pRoot1 == null) return false;        //这里是proot2!=null,proot1等于null
        if (pRoot1.val != pRoot2.val) return false;    //如果不等于就返回false
        return doesTree1HaveTree2(pRoot1.left, pRoot2.left) && doesTree1HaveTree2(pRoot1.right, pRoot2.right); //左右孩子也要相等
    }

    /**
     * 题目2(swordOffer 面试题7):重建二叉树
     * 描述: 输入某二叉树的前序遍历和中序遍历的结果,请重建该二叉树(不含有重复的元素)
     * <p>
     * 思路01: 前序遍历第一个值就是root节点,遍历中序找到root节点,找到左右子树的长度
     * 就可以在前序遍历中找到左右子树对应的范围,在遍历左右子树,就可以重建二叉树
     */
    private static Map<Integer, Integer> indexMap;

    public static TreeNode myBuildTree(int[] preorder, int[] inorder, int preorderLeft, int preorderRight, int inorderLeft, int inorderRight) {
        if (preorderLeft > preorderRight) return null;
        Integer inorderRoot = indexMap.get(preorder[preorderLeft]);
        int sizeLeftSubTree = inorderRoot - inorderLeft;
        TreeNode root = new TreeNode(preorder[preorderLeft]);  //前序的preorderRoot就是newTree的根节点
        //注意在inOrder/preOrder是找到中点,左右二边已经固定!!! 用公式 right - left + 1 = size --> right = size + left -1
        root.left = myBuildTree(preorder, inorder, preorderLeft + 1, preorderLeft + sizeLeftSubTree, inorderLeft, inorderRoot - 1);
        root.right = myBuildTree(preorder, inorder, preorderLeft + sizeLeftSubTree + 1, preorderRight, inorderRoot + 1, inorderRight);
        return root;
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        //因为数组是非重复的所以可以把中序中index,value放入到Map<Integer,Integer>中,方便用preOrder root快速寻找到中序的节点
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);       //根据前序root去寻找到中序对应节点的index
        }
        return myBuildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    ;

    /**
     * 题目3(swordOffer 面试题27):二叉树的镜像
     * 描述: 请完成一个函数,输入一棵二叉树,该函数输出它的镜像.
     * 镜像是所有左右节点进行交互
     */
    public static void mirrorRecursively(TreeNode root) {
        if (root == null || root.left == null && root.right == null) return;  //根节点为null,或者左右子树全部是为null，不操作

        TreeNode temp = root.left;     //交换左右子树(必有一个不为nul)
        root.left = root.right;
        root.right = temp;

        if (root.left != null) mirrorRecursively(root.left);
        if (root.right != null) mirrorRecursively(root.right);
    }

    /**
     * 题目4(swordOffer 面试题28):对称的二叉树
     * 描述: 请完成一个函数,来判断一棵二叉树是否是对称的(如果一棵树和他的镜像是一样的,那么它就是对称的)
     * 思路01:题目2(swordOffer 面试题26):求树的子结构
     * 分别比较左右子树的value,进行递归
     */
    public static Boolean isSymmetrical(TreeNode root) {
        return isSymmetrical(root, root);
    }

    public static Boolean isSymmetrical(TreeNode root01, TreeNode root02) {    //同名方法,比较二颗树是否是镜像
        if (root01 == null && root02 == null) return true;        //全部为null,返回true
        if (root01 == null || root02 == null) return false;     //[一个为null,一个不为null]返回false
        //左右子树值相等,左右孩子为镜像
        return root01.val == root02.val && isSymmetrical(root01.left, root02.right) && isSymmetrical(root01.right, root02.left);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
//        root.right = new BinaryTreeNode(3);
        root.left.left = new TreeNode(3);
//        System.out.println("题目1: 二叉树的深度");
//        System.out.println(treeDepth(root));
//        System.out.println("题目3(swordOffer 面试题27):二叉树的镜像 子树");
//        BinaryTreeNode.inOrder(root);
//        System.out.println("");
//        System.out.println("题目3(swordOffer 面试题27):二叉树的镜像 原树的镜像:");
//        mirrorRecursively(root);
//        BinaryTreeNode.inOrder(root);
//        System.out.println("");

        System.out.println("题目4(swordOffer 面试题28):对称的二叉树:");
        System.out.println(isSymmetrical(root));


    }


}
