package com.kwl.data01.swordOffer.Tree题组;

import com.kwl.data01.dataStructure.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 剑指offer_树(tree)题组01 本题组一共是8到题目
 *
 * 题目1(swordOffer 面试题55):求二叉树的深度
 * 题目2(swordOffer 面试题26):求树的子结构
 * 题目2(swordOffer 面试题7):重建二叉树
 * 题目3(swordOffer 面试题27):二叉树的镜像
 * 题目4(swordOffer 面试题28):对称的二叉树
 * 题目5(swordOffer 面试题54):二叉搜索树的第k大的节点
 * 题目6(swordOffer 面试题36):二叉搜索树和双向链表
 * 题目7(swordOffer 面试题34):二叉树中和为某一值的路径
 *题目8(swordOffer 面试题33): 二叉搜索树的后序遍历序列
 *
 *
 *
 *
 * @author kuang.weilin
 * @date 2021/2/14
 */
public class TreeSword_01 {


    /**
     * 题目1(swordOffer 面试题55):求二叉树的深度
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

    /**
     * 题目5(swordOffer 面试题54):二叉搜索树的第k大的节点
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
     * 题目6(swordOffer 面试题36):二叉搜索树和双向链表
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
     * 题目7(swordOffer 面试题34):二叉树中和为某一值的路径
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
     * 题目8(swordOffer 面试题33): 二叉搜索树的后序遍历序列
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


}
