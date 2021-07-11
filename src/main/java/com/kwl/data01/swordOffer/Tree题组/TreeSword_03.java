package com.kwl.data01.swordOffer.Tree题组;

import com.kwl.data01.dataStructure.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author kuang.weilin
 * @date 2021/7/8 3:30
 */
public class TreeSword_03 {

    /**
     * 题目01(swordOffer 第36题): 二叉搜索树与双向链表
     * 描述: 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表.(从小到大进行排序)
     * 要求不能创建任何新的节点,只能调节树中结点的指向。
     * <p>
     * 思路01(leetcode解法):first和last结点设置为null.调用中序遍历helper(root)
     * 如果结点不为null,调用左子树递归(helper(node.left))-->这是寻找最小的最,last为null,初始化first节点
     * 不为null,将last和当前node进行结合
     * 最后first和last形成闭环,返回first
     * <p>
     * 注意:last等于一个遍历器!!不断的将结点进行扩到链表
     */
    public TreeNode last = null, first = null;

    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) return null;
        helper(root);
        last.right = first;  //首位指针形成闭环
        first.left = last;
        return first;
    }

    public void helper(TreeNode node) {       //中序遍历出来的二叉搜索树是按照顺序来排序的
        if (node != null) {
            helper(node.left);

            if (last == null) first = node;   //first第一来,那么直接就指向第一个节点(注意第一个节点也是最小值)
            else {                           //node <=> last进行链接
                last.right = node;       //注意左是指向较大值，所以是right
                node.left = last;
            }
            last = node;//last指向当前节点

            helper(node.right);
        }
    }

    /**
     * 题目02(swordOffer 第34题): 二叉树中和为某一值的路径
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


}
