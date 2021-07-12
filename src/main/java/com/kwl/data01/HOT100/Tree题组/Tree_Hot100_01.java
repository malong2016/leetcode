package com.kwl.data01.HOT100.Tree题组;

import com.kwl.data01.dataStructure.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author kuang.weilin
 * @date 2021/3/1
 */
public class Tree_Hot100_01 {

    /**
     * 题目01(leetcode 第114题): 二叉树展开为链表
     * 描述: 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     * 1 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 2 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     * <p>
     * 思路01: 设置一个List<TreeNode> list，对root进行先序遍历，
     * 把节点按照顺序加入到list中，在遍历list指针，把节点串成新的链表
     * 思路02(递归法): 利用右子树-->左子树-->root(后序遍历的逆序,不会丢失孩子节点),设置全局变量pre，保存
     * 遍历指针的right节点
     */
    public void flatten(TreeNode root) {
        List<TreeNode> list = new LinkedList<>();
        dfs(root, list);
        for (int i = 1; i < list.size(); i++) {
            root.right = list.get(i);
            root.left = null;
            root = root.right; //root链表的下一个指针
        }
    }

    void dfs(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            dfs(root.left, list);
            dfs(root.right, list);
        }
    }

    private TreeNode pre = null;

    public void flatten01(TreeNode root) {   //逆序的后序遍历,pre是当前遍历指针的下一个节点
        if (root == null)
            return;
        flatten(root.right);
        flatten(root.left);
        root.right = pre;
        root.left = null;     //因为已经遍历了，所以不用担心节点的丢失
        pre = root;
    }

    /**
     * 题目02(leetcode 第98题): 验证二叉搜索树
     * 描述: 假设一个二叉搜索树具有如下特征
     * 1 节点的左子树只包含小于当前节点的数。
     * 2 节点的右子树只包含大于当前节点的数。
     * 3 所有左子树和右子树自身必须也是二叉搜索树。
     * <p>
     * 思路01(解题区01): 中序遍历节点，设置一个当前节点的前驱pre,比较二个值，不断进行递归
     * 思路02(官方高赞): 设置dfs上下界，不断进行更新上下界
     */
    int pre01 = Integer.MIN_VALUE;         //注意测试会使用[-2147483648] -2^32,所以这里要设置更小的Long.MIN_VALUE

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST(root.left)) return false;      //如果是false，就返回false,注意这里是true,还需要继续向下遍历，所以不能直接返回
        if (root.val <= pre01) return false;     //小于前驱节点,直接返回
        pre01 = root.val;          //更新前驱节点
        return isValidBST(root.right);     //判断右子树是否是
    }

    public boolean isValidBST01(TreeNode root) {
        if (root == null) return true;
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    boolean dfs(TreeNode root, long min, long max) {  //确定上下界
        if (root == null) return true;
        if (root.val >= max || root.val <= min) return false;
        return dfs(root.left, min, root.val) && dfs(root.right, root.val, max);  //递归
    }

    /**
     * 题目03(leetcode 第236题): 二叉树的最近公共祖先   --本题和swordOffer 68 II 重合
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
     * 题目04(leetcode 第437题): 路径总和 III
     * 描述: 给定一个二叉树，它的每个结点都存放着一个整数值。
     * 找出路径和等于给定数值的路径总数。
     * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
     * <p>
     * 思路: SwordOffer 34题和本题思路有点像，不过本题是没有规定从root开始，叶结点结束，而是从任何结点开始！！任何结点结束
     * 所以要用递归
     */
    int count = 0;

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        dfs(root, targetSum);         //以root为开始统计
        pathSum(root.left, targetSum);    //递归,注意这里不是dfs!!!，root.left只不过是一种情况
        pathSum(root.right, targetSum);
        return count;
    }

    void dfs(TreeNode root, int targetSum) {
        if (root != null) {
            targetSum -= root.val;
            if (targetSum == 0) count++;
            dfs(root.left, targetSum);
            dfs(root.right, targetSum);
        }
    }

    /**
     * 题目05(leetcode 第124题): 二叉树中的最大路径和
     * 描述: 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
     * 路径和 是路径中各节点值的总和。
     * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
     * <p>
     * 注意: 本题可以转弯,就是任何都可以连接,如果存在父节点,那么左右子树只能选最大贡献值的一个
     * <p>
     * 思路01(官方解答):
     * 1 每一个节点有一个最大贡献值为root.val + max(root.val,root.val); --该节点存在父节点时候
     * 2 每次更新res = root.val + maxGain(root.left) + maxGain(root.right)  --该节点就是父节点时候
     * <p>
     * 对于任意一个节点, 如果最大和路径包含该节点, 那么只可能是两种情况:
     * 1. 其左右子树中所构成的和路径值较大的那个加上该节点的值后向父节点回溯构成最大路径
     * 2. 左右子树都在最大路径中, 加上该节点的值构成了最终的最大路径
     */
    int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return ans;
    }

    int maxGain(TreeNode root) {
        if (root == null) return 0;
        int left = Math.max(0, maxGain(root.left));  //如果最大贡献小于0，就舍弃
        int right = Math.max(0, maxGain(root.right));
        ans = Math.max(ans, left + right + root.val);  //当前节点作为父节点，如果大于就更新res
        return root.val + Math.max(left, right);  //该节点不是父节点，而存在父节点时候，只能在左右子树中选最大的
    }
    /**
     * 题目06(leetcode 第297题): 二叉树的序列化与反序列化              -- 本题和swordOffer 第37题 重复
     * 描述: 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
     * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
     * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
     * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

     */
    public String serialize(TreeNode root) {
        if (root == null) return "[]";
        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode poll = queue.poll();
            if (poll != null){
                stringBuilder.append(poll.val+",");
                queue.offer(poll.left);
                queue.offer(poll.right);
            }else stringBuilder.append("null,");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1); //删除最后一个多余,
        stringBuilder.append("]");         //添加最后一个]
        return stringBuilder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("[]")) return null;
        int i = 1;                                          //根节点已经入队，所有从索引为1开始计算
        String[] str = data.substring(1, data.length() - 1).split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(str[0])); //一定要实例,最后返回的就是root
        queue.offer(root);      //将首歌节点入队
        while(!queue.isEmpty()){
            TreeNode poll = queue.poll();
            if(!str[i].equals("null")){//不为null,才能入队，还有如果是Null,不需要复制给left/right,默认初始化就是null
                poll.left = new TreeNode(Integer.parseInt(str[i]));
                queue.offer(poll.left);
            }
            i++;       //无论是否null，都需要++
            if(!str[i].equals("null")){
                poll.right = new TreeNode(Integer.parseInt(str[i]));
                queue.offer(poll.right);
            }
            i++;
        }
        return root;
    }
    /**
     * 题目07(leetcode 第96题): 不同的二叉搜索树
     * 描述: 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     * <p>
     * 思路01: 动态规划,注意dp[n]就是n个节点2
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;       //0个节点,就一种情况
        dp[1] = 1;       //1个节点，也是一种情况
        for (int i = 2; i <= n ; i++) { //多少个节点
            for (int j = 1; j <= i; j++) { //这个是从哪个点开始分割左右子树,j代表的是中点
                dp[i] += dp[j - 1] * dp[i - j]; //dp[j - 1]是前半段(j-1就是前半段节点数),dp[i - j]是后半段(这是是从1开始,i-j就是后半段的节点数)
            }
        }
        return dp[n];
    }

    /**
     * 题目08(leetcode 第208题): 实现 Trie (前缀树)  -- 本题代码: com.kwl.data01.dataStructure.题目Struct.Trie
     * 描述: Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
     *
     * 请你实现 Trie 类：
     *
     * Trie() 初始化前缀树对象。
     * void insert(String word) 向前缀树中插入字符串 word 。
     * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
     * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
     *
     * eg:
     * 输入
     * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
     * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
     * 输出
     * [null, null, true, false, true, null, true]
     *
     * 解释
     * Trie trie = new Trie();
     * trie.insert("apple");
     * trie.search("apple");   // 返回 True
     * trie.search("app");     // 返回 False
     * trie.startsWith("app"); // 返回 True
     * trie.insert("app");
     * trie.search("app");     // 返回 True
     */

}
