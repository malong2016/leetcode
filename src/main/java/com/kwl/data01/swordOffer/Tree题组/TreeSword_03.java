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
     * 题目01(swordOffer 第34题): 二叉树中和为某一值的路径
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
