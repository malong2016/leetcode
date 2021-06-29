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
     * 题目1(swordOffer 面试题32 III) 从上到下打印二叉树III
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
     * 题目2(swordOffer面试题8): 二叉树的下一个节点
     * 描述: 给定一棵二叉树和其中一个节点,如果找出中序遍历序列的下一个节点?
     * 树中的节点除了有二个分别指向左右节点的指针,还有一个指向父节点的指针
     * <p>
     * 思路01(swordOffer思路): 假如存在右子树,沿着右节点一路left找到最左边的那个节点
     * 不存在右子树,如果是父节点的左节点,next是父节点.如果是父节点的右节点,一直向上找节点是其父节点
     * 的左节点就是next
     */
    public TreeWithFather getNext(TreeWithFather root) {
        if (root == null) return null;     //根节点或者目标节点为null,返回null

        if (root.right != null) {
            root = root.right;
            while (root.left != null) root = root.left;
            return root;
        }

        while (root.father != null) {         //无右子树,如果存在父节点
            if (root.father.left == root) return root.father;      //如果这个节点是父节点的左子树
            root = root.father;         //不断向上找父节点
        }
        return null;        //无右子树,不存在父节点
    }

    /**
     * 题目3(swordOffer 面试题37):序列化二叉树
     * 描述: 请实现二个函数,分别用来序列化和反序列化!!!
     * leetcode是层序遍历,空指针用null表示,[1,2,null,null]
     * <p>
     * 思路01(leetcode): 序列化，利用层序遍历和stringBuild来实现输出
     */
    public static String serialize(TreeNode root) {
        if (root == null) return "[]";
        StringBuilder res = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {          //分成==null的化,!=null(左右都是要添加的!!!)
                res.append(node.val + ",");
                queue.add(node.left);
                queue.add(node.right);
            } else res.append("null,");
        }
        res.deleteCharAt(res.length() - 1);
        res.append("]");
        return res.toString();
    }

    public static TreeNode deserialize(String data) {
        if (data == "[]") return null;
        String[] arr = data.substring(1, data.length() - 1).split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        queue.offer(root);
        int i = 1;   //通过i不断进行控制找到下一个
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (!arr[i].equals("null")) {
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

    /**
     * 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
     * 中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，
     * 最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。
     * <p>
     * 思路: 如果root是节点q 和节点p的祖先,那么
     * 1 q 和 P在root的左右测
     * 2 q是root,p是普通节点
     * 3 p是root ,q是普通节点
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {            //这里不可能是null,一定能找到公共节点
            if (root.val > q.val && root.val > p.val) root = root.left;
            else if (root.val < q.val && root.val < p.val) root = root.right;
            else break;
        }
        return root;
    }

    public TreeNode lowestCommonAncestor01(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > q.val) { // 保证 p.val < q.val      //优化下面判断可以少判断
            TreeNode tmp = p;
            p = q;
            q = tmp;
        }
        while (root != null) {
            if (root.val < p.val) // p,q 都在 root 的右子树中
                root = root.right; // 遍历至右子节点
            else if (root.val > q.val) // p,q 都在 root 的左子树中
                root = root.left; // 遍历至左子节点
            else break;
        }
        return root;
    }
}
