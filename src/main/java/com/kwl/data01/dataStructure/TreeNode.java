package com.kwl.data01.dataStructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * 二叉树
 *
 * @author kuang.weilin
 * @date 2021/2/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {

    public int val;

    public TreeNode left;

    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    /**
     * 递归法-前序遍历
     */
    public static void preOrder(TreeNode treeNode) {
        if (treeNode != null) {
            System.out.println(treeNode.val);
            preOrder(treeNode.left);
            preOrder(treeNode.right);
        }
    }

    /**
     * 递归法-中序遍历
     */
    public static void inOrder(TreeNode treeNode) {
        if (treeNode != null) {
            inOrder(treeNode.left);
            System.out.println(treeNode.val);
            inOrder(treeNode.right);
        }
    }

    /**
     * 递归法-后序遍历
     */
    public static void postOrder(TreeNode treeNode) {
        if (treeNode != null) {
            postOrder(treeNode.left);
            postOrder(treeNode.right);
            System.out.println(treeNode.val);
        }
    }

    /**
     * 迭代法-前序遍历
     * 思路: 用一个栈去维护
     */
    public static List<Integer> preOrderIter(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            res.add(root.val);
            if (root.right != null) stack.push(root.right);
            if (root.left != null) stack.push(root.left);
        }
        return res;
    }

    /**
     * 模仿中序遍历迭代法的先序遍历
     *
     * @param root
     * @return
     */
    public static List<Integer> preOrderIter02(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        while (root != null || !stack.isEmpty()) {    //root是遍历指针!!!!
            while (root != null) {
                list.add(root.val);
                stack.push(root);
                root = root.left;       //将左边的依次入栈
            }
            root = stack.pop();
            root = root.right;
        }
        return list;
    }


    /**
     * 迭代法-中序遍历
     */
    public static void inOrderIter(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {    //root是遍历指针!!!!
            while (root != null) {
                stack.push(root);
                root = root.left;       //将左边的依次入栈
            }
            root = stack.pop();           //这里是出最后的左
            System.out.println(root.val);
            root = root.right;
        }
    }


    /**
     * 迭代法-后序遍历
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            res.addFirst(root.val);
            if (root.left != null) stack.push(root.left);
            if (root.right != null) stack.push(root.right);
        }
        return res;
    }


    /**
     * 层次遍历(每行从左边到右边打印,一行一行从root节点开始打印)
     *
     * @param rootNode
     */
    public static void levelOrder(TreeNode rootNode) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        if (rootNode != null) queue.offer(rootNode);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            list.add(treeNode.val);
            if (treeNode.left != null) queue.offer(treeNode.left);
            if (treeNode.right != null) queue.offer(treeNode.right);
        }
        System.out.println("层次遍历的结果是: " + list);
    }

    /**
     * 利用层次遍历来计算出每层平均值
     *
     * @param rootNode
     */
    public static void levelOrderAvg(TreeNode rootNode) {
        Queue<TreeNode> queueTest = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        if (rootNode != null) queueTest.offer(rootNode);
        while (!queueTest.isEmpty()) {
            int nodeNum = queueTest.size(), total = 0;                   //nodeNum节点数,total该行的总计数
            for (int i = 0; i < nodeNum; i++) {        //每次出队都是要把该层所有的node都出队
                TreeNode pollNode = queueTest.poll();
                total += pollNode.val;
                if (pollNode.left != null) queueTest.offer(pollNode.left);
                if (pollNode.right != null) queueTest.offer(pollNode.right);
            }
            //求出每一行的平均数,注意:每次while循环都是 1 出栈本层的所有节点,计算平均数 2 入栈下一层所有的节点(本层的所有的孩子节点)
            list.add(total / nodeNum);
        }
        System.out.println("每层节点平均值: " + list);
    }

    /**
     * 复制一棵二叉树
     */
    public static TreeNode copyTree(TreeNode root) {
        if (root == null) return null;
        TreeNode copyTree = new TreeNode(root.val);
        copyTree.left = copyTree(root.left);      //要指向,不能创建,是复制指针
        copyTree.right = copyTree(root.right);
        return copyTree;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(200, new TreeNode(1), new TreeNode(3));    //根节点
        root.left.left = new TreeNode(12);
        root.right.right = new TreeNode(13);
//        inOrder(root);  //中序遍历
//        System.out.println("========");
//        inOrderIter(root);
        preOrder(root);  //前序遍历
//        System.out.println("===");
//        preOrderIter(root);
        System.out.println("...");
        System.out.println("先序遍历02");
        System.out.println(preOrderIter02(root));
//        postOrder(root);  //后序遍历
//        levelOrder(root);    //层次遍历
//        levelOrderAvg(root);
//        System.out.println("复制一棵二叉树: ");


    }


}
