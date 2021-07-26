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
            if (root.right != null) stack.push(root.right);      //先入右节点，后出！！
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
        List<Integer> res = new ArrayList<>();
        while (root != null || !stack.isEmpty()) {    //root是遍历指针!!!!
            while (root != null) {
                res.add(root.val);
                stack.push(root);
                root = root.left;       //将左边的依次入栈
            }
            root = stack.pop();
            root = root.right;
        }
        return res;
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
     * 迭代法-中序遍历
     */
    public static List<Integer> inOrderIter(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        while (root != null || !stack.isEmpty()) {    //root是遍历指针!!!!
            while (root != null) {
                stack.push(root);
                root = root.left;       //将左边的依次入栈
            }
            res.add(stack.pop().val);  //这里是出最后的左
            root = root.right;      //注意最后一个节点是没有左子树，跳出循环的时候！！，直接指向右手
        }
        return res;
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
     * 迭代法-后序遍历
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;
        LinkedList<TreeNode> stack = new LinkedList<>();
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
    public static List<Integer> levelOrder(TreeNode rootNode) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        if (rootNode != null) queue.offer(rootNode);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            res.add(treeNode.val);
            if (treeNode.left != null) queue.offer(treeNode.left);
            if (treeNode.right != null) queue.offer(treeNode.right);
        }
        return res;
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


    }
}
