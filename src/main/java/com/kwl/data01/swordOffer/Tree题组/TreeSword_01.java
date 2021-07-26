package com.kwl.data01.swordOffer.Tree题组;

import com.kwl.data01.dataStructure.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 剑指offer_树(tree)题组01 本题组一共是8到题目
 *
 * @author kuang.weilin
 * @date 2021/2/14
 */
public class TreeSword_01 {


    /**
     * 题目01(swordOffer 第55题-I): 二叉树的深度
     * <p>
     * 思路01: 利用递归,如果rootNode为null,就返回0.否则就返回左右节点更高的+1
     * 思路02: 利用二叉树层次遍历,参考,求二叉树每层的平均值
     */
    public  int treeDepth(TreeNode rootNode) {
        if (rootNode == null) return 0;
        return Math.max(treeDepth(rootNode.left), treeDepth(rootNode.right)) + 1;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>() {{
            add(root);
        }};
        int depth = 0;
        while (!queue.isEmpty()) {
            int cursize = queue.size();
            for (int i = 0; i < cursize; i++) {                //将本层的所有节点都出队，然后把本层所有的左右孩子都入队
                TreeNode temp = queue.poll();
                if (temp.left != null) queue.add(temp.left);
                if (temp.right != null) queue.add(temp.right);
            }
            depth++;
        }
        return depth;
    }

    /**
     * 题目02(swordOffer 第55题-II):平衡二叉树
     * 二叉平衡树的定义:输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
     * <p>
     * 思路:
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs(treeDepth(root.left) - treeDepth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 题目03(swordOffer 第26题):求树的子结构
     * 描述: 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
     * eg: [1,2,3,4]  [3]  --> true
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        //当A和B同时不是null的时候,A和B是子结构,A的左右子树和B是子结构a满足其中之一就可以.
        //isSubStructure(A.left, B) 也不一定是A.left做root(A.left子树满足就可以),所以还是要继续递归
        //注意后手要括号起来,因为如果前面出现A为null,后手所有的都不会执行，如果没有括号,后面会执行！！！
        return (A != null && B != null) && (recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    Boolean recur(TreeNode A, TreeNode B) {       //判断从A的root节点出发，节点B是不是A的子树
        if (B == null) return true;
        if (A == null || A.val != B.val) return false;
        return recur(A.left, B.left) && recur(A.right, B.right);
    }

    /**
     * 题目04(swordOffer 第07题): 重建二叉树                              --本题和 leetcode 第105题 重合
     * 描述: 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * <p>
     * 思路01: 前序遍历第一个值就是root节点,遍历中序找到root节点,找到左右子树的长度
     * 就可以在前序遍历中找到左右子树对应的范围,在遍历左右子树,就可以重建二叉树
     */
    HashMap<Integer, Integer> map = new HashMap<>();     //key代表int[]的值,value是对应arr中的索引

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) return null;       //有一个为null，就直接返回
        for (int i = 0; i < inorder.length; i++) {       //将中序遍历注入到map中,也可以顺序查找
            map.put(inorder[i], i);
        }
        return dfs(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    TreeNode dfs(int[] preorder, int[] inorder, int preL, int preR, int iL, int iR) {// 设置好边界
        if (preL > preR) return null;        //注意这里是可以等于的,等于是一个节点
        int rootValue = preorder[preL];
        Integer iRoot = map.get(rootValue);      //获取到root在中序遍历的值
        int lLen = iRoot - iL;     //获取到左子树的长度

        TreeNode root = new TreeNode(rootValue);          //构建新树root
        root.left = dfs(preorder, inorder,preL + 1,preL + lLen,iL,iRoot -1);       //左子树
        root.right = dfs(preorder, inorder, preL + lLen + 1, preR, iRoot + 1, iR);       //有子树
        return root;
    }


    /**
     * 题目05(swordOffer 第27题): 二叉树的镜像
     * 描述: 请完成一个函数,输入一棵二叉树,该函数输出它的镜像.
     * 镜像是所有左右节点进行交互
     */
    public  TreeNode mirrorRecursively(TreeNode root) {
        if (root == null) return null;
//        TreeNode temp = root.left;                   //k神
//        root.left = mirrorRecursively(root.right);
//        root.right = mirrorRecursively(temp);
//        return root;

        TreeNode left = mirrorRecursively(root.left);             //官方
        TreeNode right = mirrorRecursively(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 题目06(swordOffer 第28题): 对称的二叉树
     * 描述: 请完成一个函数,来判断一棵二叉树是否是对称的(如果一棵树和他的镜像是一样的,那么它就是对称的)
     * 思路01:题目02(swordOffer 第26):求树的子结构
     * 分别比较左右子树的value,进行递归
     */
    public  Boolean isSymmetrical(TreeNode root) {
        if (root == null) return true;
        return isSymmetrical(root.left, root.right);
    }

    public  Boolean isSymmetrical(TreeNode root01, TreeNode root02) {    //同名方法,比较二颗树是否是镜像
        if (root01 == null && root02 == null) return true;        //全部为null,返回true
        if (root01 == null || root02 == null) return false;     //[一个为null,一个不为null]返回false
        //左右子树值相等,左右孩子为镜像
        return root01.val == root02.val && isSymmetrical(root01.left, root02.right) && isSymmetrical(root01.right, root02.left);
    }

    /**
     * 题目07(swordOffer 第54题): 二叉搜索树的第k大节点
     * 描述: 给定一个二叉搜索树(左节点<root节点<右节点),请找到其中第k大的节点。
     * 描述: 我们可以中序遍历的逆序,先遍历右子树,在遍历中，最后遍历左子树。这种遍历是从大到小!!!
     */
    int ans01 = -1, k;
    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        return ans01;
    }
    void dfs147(TreeNode root){
        if (root != null) {
            dfs147(root.right);
            if (--k == 0) {
                ans01 = root.val;
                return;        //找到答案就不要搜索下面的左子树了!!!
            }
            dfs147(root.left);
        }
    }


    /**
     * 题目08(swordOffer 第36题): 二叉搜索树与双向链表
     * 描述: 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表.(从小到大进行排序)
     * 要求不能创建任何新的节点,只能调节树中结点的指向。
     *
     * 注意: 特别地，我们希望可以就地完成转换操作。当转化完成以后，
     * 树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
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






}
