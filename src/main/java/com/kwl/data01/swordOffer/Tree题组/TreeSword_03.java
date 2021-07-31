package com.kwl.data01.swordOffer.Tree题组;

import com.kwl.data01.dataStructure.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author kuang.weilin
 * @date 2021/7/8 3:30
 */
public class TreeSword_03 {


    /**
     * 题目07(swordOffer 第37题): 序列化二叉树
     * 描述: 请实现二个函数,分别用来序列化和反序列化!!!
     * leetcode是层序遍历,空指针用null表示,[1,2,null,null]
     * <p>
     * 思路01(leetcode): 序列化，利用层序遍历和stringBuild来实现输出
     */
    public  String serialize(TreeNode root) {           //这是序列化
        if (root == null) return "[]";
        StringBuilder res = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                res.append(node.val + ",");
                queue.add(node.left);           //无论是否是null都是要添加进来
                queue.add(node.right);
            } else res.append("null,");
        }
        res.deleteCharAt(res.length() - 1);
        res.append("]");
        return res.toString();
    }

    public  TreeNode deserialize(String data) {       //反序列化
        if (data == "[]") return null;
        String[] arr = data.substring(1, data.length() - 1).split(",");      //转化为数组类型
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        queue.offer(root);
        int i = 1;   //通过i不断进行控制找到下一个,root节点已经入队
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();          //这里出队的化是本层所有的扫描到到队列中，这时候的匹配是下一层
            if (!arr[i].equals("null")) {        // 构造树和入队都是在判断条件里面进行的
                poll.left = new TreeNode(Integer.parseInt(arr[i]));
                queue.offer(poll.left);
            }
            i++;
            if (!arr[i].equals("null")) {
                poll.right = new TreeNode(Integer.parseInt(arr[i]));
                queue.offer(poll.right);
            }
            i++;
        }
        return root;
    }


    public static void main(String[] args) {
        TreeSword_03 treeSword_03 = new TreeSword_03();
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.right.left = new TreeNode(4);
        treeNode.right.left = new TreeNode(4);
        treeNode.right.right = new TreeNode(5);
        System.out.println(treeSword_03.serialize(treeNode));
    }




}
