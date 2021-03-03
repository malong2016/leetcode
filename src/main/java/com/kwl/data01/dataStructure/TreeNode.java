package com.kwl.data01.dataStructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * 深入理解 二叉树(前序、中序、后序、层次遍历)
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
     * 前序遍历(中间节点-->左节点-->右节点)
     *
     * @param treeNode
     */
    public static void preOrder(TreeNode treeNode) {
        if (treeNode != null) {
            System.out.print(treeNode.val + " ");
            preOrder(treeNode.left);
            preOrder(treeNode.right);
        }
    }

    /**
     * 迭代法进行前序遍历
     * 思路: 用一个栈去维护
     */
    public static List<Integer> preOrderIter(TreeNode root) {
        //返回的结果集
        List<Integer> res = new ArrayList<>();
        //如果根节点为空，那么就直接返回空的结果集，没遍历的必要了
        if(root == null) return res;
        //创建一个栈
        Deque<TreeNode> stack = new LinkedList<>();
        //将当前的根节点放入栈中
        stack.push(root);
        //如果当前的栈不为空，那就一直循环
        while(!stack.isEmpty()){
            //从栈中取出一个结点
            root = stack.pop();
            //由于是前序遍历，所以将当前的结点直接存入结果集合中
            res.add(root.val);
            //此处的顺序比较有将就，前序是：根 → 左 → 右
            //所以我们要先将右结点压入栈，再将左结点压入栈
            //因为左结点是最晚压入栈的，所以取的时候，会先取左结点，然后是右结点，就符合前序遍历了
            if(root.right != null) stack.push(root.right);//如果当前结点的右子结点不为空，就把右子结点压入栈
            if(root.left != null) stack.push(root.left);//如果当前节点的左子结点不为空，就把左子结点压入栈
        }
        return res;
    }

    /**
     * 模仿中序遍历迭代法的先序遍历
     * @param root
     * @return
     */
    public static List<Integer> preOrderIter02(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        while (root!=null || !stack.isEmpty()){    //root是遍历指针!!!!
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
     * 中序遍历(左节点-->中间节点-->右节点)  --递归法
     *
     * @param treeNode
     */
    public static void inOrder(TreeNode treeNode) {
        if (treeNode != null) {
            inOrder(treeNode.left);
            System.out.print(treeNode.val + " ");
            inOrder(treeNode.right);
        }
    }

    /**
     * 中序遍历(左节点-->中间节点-->右节点)             --迭代法
     */
    public static void inOrderIter(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (root!=null || !stack.isEmpty()){    //root是遍历指针!!!!
            while (root != null) {
                stack.push(root);
                root = root.left;       //将左边的依次入栈
            }
            root = stack.pop();
            System.out.print(root.val + " ");
            root = root.right;
        }
    }

    /**
     * 后序遍历(左节点-->右节点-->中间节点)
     *
     * @param treeNode
     */
    public static void postOrder(TreeNode treeNode) {
        if (treeNode != null) {
            postOrder(treeNode.left);
            postOrder(treeNode.right);
            System.out.print(treeNode.val + " ");
        }
    }

    /**
     * 迭代法 后序遍历
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        //此处的结果集就不能再声明为List了，我们需要使用到双端队列的功能，所以声明为LinkedList
        LinkedList<Integer> res = new LinkedList<>();
        //如果根节点为空，那么就直接返回空的结果集，没遍历的必要了
        if(root == null) return res;
        //创建一个栈
        Deque<TreeNode> stack = new LinkedList<>();
        //将当前的根节点放入栈中
        stack.push(root);
        //如果当前的栈不为空，那就一直循环
        while(!stack.isEmpty()){
            //从栈中取出一个结点
            root = stack.pop();
            //此处跟前序遍历不一样，前序遍历是往结果集的末尾添加，而后续遍历是前序遍历变形后的倒序输出
            //所以每次取出的结点都从结果集头部插入，这样子就能实现将前序遍历的变形倒序输出了
            res.addFirst(root.val);
            //因为后序是：左 → 右 → 根，上一行代码我们已经插入了根节点，所以接下来该再插入右结点，然后再插入左结点（注意，每次插入都是从结果集头部插入）
            //所以右结点应该被先取出，左结点最后被取出
            //根据栈的特性，最后被取出那就得先入栈，所以先将左结点入栈，然后是右结点入栈
            if(root.left != null) stack.push(root.left);//如果当前结点的右子结点不为空，就把右子结点压入栈
            if(root.right != null) stack.push(root.right);//如果当前节点的左子结点不为空，就把左子结点压入栈
        }
        return res;
    }


    /**
     * 层次遍历(每行从左边到右边打印,一行一行从root节点开始打印)
     *
     * @param rootNode
     */
    public static void levelOrder(TreeNode rootNode) {
        Queue<TreeNode> queueTest = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        if (rootNode!=null) queueTest.offer(rootNode);           //如果rootNode不是null,就先把根节点入队
        while (!queueTest.isEmpty()) {       //队列不为null
            TreeNode treeNode = queueTest.poll();     //这是出队节点
            list.add(treeNode.val);                     //将出队节点的data添加到List数组
            if (treeNode.left!=null) queueTest.offer(treeNode.left);     //左右如果不是null,就入队
            if (treeNode.right!=null) queueTest.offer(treeNode.right);
        }
        System.out.println("层次遍历的结果是: "+list);
    }

    /**
     * 利用层次遍历来计算出每层平均值
     *
     * @param rootNode
     */
    public static void levelOrderAvg(TreeNode rootNode) {
        Queue<TreeNode> queueTest = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        if (rootNode!=null) queueTest.offer(rootNode);           //如果rootNode不是null,就先把根节点入队
        while (!queueTest.isEmpty()) {       //队列不为null
            int nodeNum = queueTest.size(),total=0;                   //nodeNum节点数,total该行的总计数
            for (int i = 0; i < nodeNum; i++) {        //每次出队都是要把该层所有的node都出队
                TreeNode pollNode = queueTest.poll();
                total+=pollNode.val;
                if (pollNode.left!=null) queueTest.offer(pollNode.left);     //左右如果不是null,就入队
                if (pollNode.right!=null) queueTest.offer(pollNode.right);
            }
            //求出每一行的平均数,注意:每次while循环都是 1 出栈本层的所有节点,计算平均数 2 入栈下一层所有的节点(本层的所有的孩子节点)
            list.add(total / nodeNum);
        }
        System.out.println("每层节点平均值: "+list);
    }

    /**
     * 复制一棵二叉树
     */
    public static TreeNode copyTree(TreeNode root) {
        if (root == null) return null;
        TreeNode copyTree = new TreeNode(root.val);
        copyTree.left =  copyTree(root.left);      //要指向,不能创建,是复制指针
        copyTree.right = copyTree(root.right);
        return copyTree;
    }



    public static void main(String[] args) {
        TreeNode root = new TreeNode(200,new TreeNode(1), new TreeNode(3));    //根节点
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
