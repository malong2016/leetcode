package com.kwl.data01.swordOffer.Tree题组;

import com.kwl.data01.dataStructure.TreeNode;
import com.kwl.data01.dataStructure.题目Struct.TreeWithFather;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * swordOffer 二叉树(BinaryTreeNode)题组03(每个题组是4个题目)
 *
 * @author kuang.weilin
 * @date 2021/2/18
 */
public class TreeSword_02 {
    /**
     * 题目01(swordOffer 第32题-I) 从上到下打印二叉树
     * 描述: [3,9,20,15,7]
     */
    public int[] levelOrder10(TreeNode root) {  //方法一: 使用一个队列
        if (root == null) return new int[0];
        Queue<TreeNode> queue = new LinkedList<>(); //设置一个队列
        LinkedList<Integer> res = new LinkedList<>(); //返回List
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode pollNode = queue.poll();
            res.add(pollNode.val);
            if (pollNode.left != null) queue.offer(pollNode.left);
            if (pollNode.right != null) queue.offer(pollNode.right);
        }
        int[] resInt = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            resInt[i] = res.get(i);
        }
        return resInt;
    }

    /**
     * 题目02(swordOffer 第32题-I) 从上到下打印二叉树
     * 描述: [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     */
    public List<List<Integer>> levelOrder04(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.offer(root);
        while(!queue.isEmpty()){
            List<Integer> path = new LinkedList<>();   //每次都是要新的path
            int size =queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode pollNode =  queue.poll();
                path.add(pollNode.val);
                if (pollNode.left != null) queue.offer(pollNode.left);
                if (pollNode.right != null) queue.offer(pollNode.right);
            }
            res.add(new LinkedList<>(path));
        }
        return res;
    }


    /**
     * 题目03(swordOffer 第32题-III) 从上到下打印二叉树III
     * <p>
     * 描述: 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，
     * 第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
     * <p>
     * 思路: 用一个count去判断奇数和偶数层，然后如果是偶数层，就addFirst到最前端，就可以反向输出链表了
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        int level = 0;                       //level记录出队时候的层数
        if (root != null) queue.offer(root);
        while (!queue.isEmpty()) {
            LinkedList<Integer> path = new LinkedList<>();
            level++;                         //出队层数+1
            int num = queue.size();
            for (int i = 0; i < num; i++) {
                TreeNode poll = queue.poll();
                if (level % 2 == 1) {      //奇数层是正常入队
                    path.add(poll.val);
                } else {
                    path.addFirst(poll.val);  //偶数层是加入队列的首部
                }
                if (poll.left != null) queue.offer(poll.left);
                if (poll.right != null) queue.offer(poll.right);
            }
            res.add(path);
        }
        return res;
    }


    /**
     * 题目04(swordOffer第8题): 二叉树的下一个节点
     * 描述: 给定一棵二叉树和其中一个节点,如何找出中序遍历序列的下一个节点?
     * 树中的节点除了有二个分别指向左右节点的指针,还有一个指向父节点的指针
     * <p>
     * 思路01(swordOffer思路): 假如存在右子树,沿着右节点一路left找到最左边的那个节点
     * 不存在右子树,如果是父节点的左节点,next是父节点.如果是父节点的右节点,一直向上找节点是其父节点
     * 的左节点就是next
     */
    public TreeWithFather getNext(TreeWithFather root) {
        if (root == null) return null;

        if (root.right != null) {     //存在右节点，那么就找到右节点中最左边的那个节点(也是本子树中序遍历最先输出的)
            root = root.right;
            while (root.left != null) root = root.left;
            return root;
        }

        while (root.father != null) {         //无右子树,如果存在父节点
            if (root.father.left == root) return root.father;      //如果这个节点是父节点的左子树
            root = root.father;
        }
        return null;        //无右子树,向上找不到一个节点的左节点是本节点
    }


    /**
     * 题目05(swordOffer 第68题 - I): 二叉搜索树的最近公共祖先
     * 中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，
     * 最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。
     * <p>
     * 思路: 如果root是节点q 和节点p的祖先,那么
     * 1 q 和 P在root的左右测
     * 2 q是root,p是普通节点
     * 3 p是root ,q是普通节点
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {  //使用root做遍历指针2
        while (root != null) {            //这里不可能是null,一定能找到公共节点
            if (root.val > q.val && root.val > p.val) root = root.left;
            else if (root.val < q.val && root.val < p.val) root = root.right;
            else break;        //这个值在这二边的中间
        }
        return root;
    }
    /**
     * 题目06(swordOffer 第68题 - I): 二叉树的最近公共节点
     * 思路01: 最后q和p一定会落在root二端
     */
    public TreeNode lowestCommonAncestor03(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;        //root本身是null，或者root就是p/q就返回本身
        TreeNode left = lowestCommonAncestor03(root.left, q, p);     //左子树进行搜索
        TreeNode right = lowestCommonAncestor03(root.right, q, p);  //右子树进行搜索
        if (left == null) return right;          //注意，root节点不可能是q/p，如果left是null(没有找到),那么一定就是返回右
        if (right == null) return left;
        return root;        //左右子树都找到了，说明在root的二端，就返回root
    }

    /**
     * 题目07(swordOffer 第34题): 二叉树中和为某一值的路径        --leetcode 第437题 没有限制root和叶子节点,那么就要递归每一个节点作为root节点,然后不需要判断叶子节点
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
     * 题目08(swordOffer 第33题): 二叉搜索树的后序遍历序列
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
