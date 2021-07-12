package com.kwl.data01.HOT100.回溯_递归;

import java.util.*;

/**
 * @author kuang.weilin
 * @date 2021/2/23
 */
public class Dfs_Hot100_01 {

    /**
     * 题目01(leetcode 第78题): 子集
     * 描述: 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     * <p>
     * eg:nums = [1,2,3]  --> [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
     * nums = [0]   --> [[],[0]]
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();     //这是返回的结果集!!
        LinkedList<Integer> path = new LinkedList<>();
        dfs(0, nums, res, path);
        return res;
    }

    public void dfs(int index, int[] nums, List<List<Integer>> res, LinkedList<Integer> path) {
        res.add(new LinkedList<>(path));      //要动态分配空间,temp总在变化,所以要动态分配
        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(i + 1, nums, res, path);
            path.removeLast();
        }
    }

    /**
     * 题目02(leetcode 第46题): 全排列
     * 描述: 给定一个 没有重复 数字的序列，返回其所有可能的全排列
     * eg: 输入: [1,2,3] --> {[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]}
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        for (int num : nums) {
            path.add(num);
        }
        dfs(nums, 0, res, path);
        return res;
    }

    void dfs(int[] nums, int index, List<List<Integer>> res, List<Integer> path) {
        if (index == nums.length) {
            res.add(new ArrayList<>(path));   //如果遍历完成一轮,就添加到res中,在这里就已经做完了
        }
        for (int i = index; i < nums.length; i++) {
            Collections.swap(path, index, i);
            dfs(nums, index + 1, res, path);
            Collections.swap(path, index, i);    //回溯到原来的状态
        }
    }

    public List<List<Integer>> permuteAndVisit(int[] nums) {       //使用visit来处理全排列,使用visit不需要index，每次都是重头开始遍历
        LinkedList<Integer> path = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        boolean[] visit = new boolean[nums.length];
        dfs(visit, nums, path, res);
        return res;
    }

    public void dfs(boolean[] visit, int[] nums, LinkedList<Integer> path, List<List<Integer>> res) {
        if (path.size() == nums.length) {      //path添加满了
            res.add(new LinkedList<>(path));       //注意，这里要new地址
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visit[i]) continue;       //如果已经访问过，就直接跳过
            visit[i] = true;
            path.add(nums[i]);
            dfs(visit, nums, path, res);
            visit[i] = false;
            path.removeLast();
        }
    }

    /**
     * 题目03(leetcode 39): 组合总和
     * 描述:给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的数字可以无限制重复被选取。
     * 说明: 所有数字（包括 target）都是正整数。 解集(结果)不能包含重复的组合。
     * eg: 输入：candidates = [2,3,6,7], target = 7, --> {{7},{2,2,3}}
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        Arrays.sort(candidates);
        dfs01(0, candidates, target, res, path);
        return res;
    }

    void dfs01(int index, int[] nums, int target, List<List<Integer>> res, LinkedList<Integer> path) {      //回溯
        if (target == 0) res.add(new LinkedList<>(path));
        for (int i = index; i < nums.length; i++) {
            if (target - nums[i] < 0) break;   //已经排序,本nums[i]排序不来，后面也排序不来
            path.add(nums[i]);
            dfs01(i, nums, target - nums[i], res, path); //因为可以重复,所有继续从i开始寻找
            path.removeLast();
        }
    }


    /**
     * 题目04(leetcode 第22题): 括号生成
     * 描述: 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * eg: n = 3 -->  ["((()))","(()())","(())()","()(())","()()()"]
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        dfs("", res, n, 0, 0); //初始左右括号计数都是等于0
        return res;
    }

    void dfs(String str, List<String> res, int n, int lc, int rc) {
        if (rc > lc || rc > n || lc > n) return;         //右统计大于左统计，或者左右大于n，直接返回
        if (lc == n && rc == n) {         //左右统计同时符合n添加
            res.add(str);
            return;
        }
        dfs(str + '(', res, n, lc + 1, rc);       //左添加一个'('
        dfs(str + ')', res, n, lc, rc + 1);        //右添加一个')'
    }


    /**
     * 题目05(leetcode 第79题): 单词搜索
     * 描述:给定一个二维网格和一个单词，找出该单词是否存在于网格中。
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
     * 同一个单元格内的字母不允许被重复使用。
     * eg: board =
     * [
     * ['A','B','C','E'],
     * ['S','F','C','S'],
     * ['A','D','E','E']
     * ]
     * 给定 word = "ABCCED", 返回 true
     * 给定 word = "SEE", 返回 true
     * 给定 word = "ABCB", 返回 false
     * <p>
     * 思路01(回溯法):
     */
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, word, i, j, 0)) return true;   //从单词第一个字母的下标0开始搜索开始搜索
            }
        }
        return false;
    }

    boolean dfs(char[][] board, String word, int i, int j, int index) {
        //i或者j越界,或者对应搜索的字符不满足单词对应index的字符的值!!!
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word.charAt(index))
            return false;
        if (index == word.length() - 1) return true;        //不断满足单词对应index字符,如果index来到了n - 1那么就符合
        char temp = board[i][j];                                //暂时保存值
        board[i][j] = '.';                                     //修改值,复制被重复访问
        boolean res = dfs(board, word, i + 1, j, index + 1) || dfs(board, word, i - 1, j, index + 1) ||
                dfs(board, word, i, j + 1, index + 1) || dfs(board, word, i, j - 1, index + 1);   //从上下左右开始搜索
        board[i][j] = temp;                                      //恢复原来的值
        return res;
    }


    /**
     * 题目06(leetcode 第72题) 编辑距离
     * 描述: 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
     * 你可以对一个单词进行如下三种操作：
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符。
     * 输入：word1 = "horse", word2 = "ros"
     * 输出：3
     * 解释：
     * horse -> rorse (将 'h' 替换为 'r')
     * rorse -> rose (删除 'r')
     * rose -> ros (删除 'e')
     * <p>
     * 思路: 动态规划,初始值是dp[0][i] = i,dp[j][0]
     * 状态转移方程见下
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        if (m * n == 0) return m + n;  //有一个等于,最低是返回宁外一个
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) dp[i][0] = i;
        for (int i = 0; i < n + 1; i++) dp[0][i] = i;
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
//                int k1 = dp[i - 1][j] + 1;                    //官方最优解
//                int k2 = dp[i][j - 1] + 1;
//                int k3 = dp[i - 1][j - 1];
//                if (word1.charAt(i - 1) != word2.charAt(j -1)) k3++;     //注意单词的索引要小1！！我们dp是从1开始的
//                dp[i][j] = Math.min(k1, Math.min(k2, k3));
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {            //评论区最优解
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 题目07(leetcode 第494题): 目标和
     * 描述: 给你一个整数数组 nums 和一个整数 target 。
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     * <p>
     * 输入：nums = [1,1,1,1,1], target = 3
     * 输出：5
     * 解释：一共有 5 种方法让最终目标和为 3 。
     * -1 + 1 + 1 + 1 + 1 = 3
     * +1 - 1 + 1 + 1 + 1 = 3
     * +1 + 1 - 1 + 1 + 1 = 3
     * +1 + 1 + 1 - 1 + 1 = 3
     * +1 + 1 + 1 + 1 - 1 = 3
     * <p>
     * 思路01(官方回溯法):  见下面
     * 思路02(动态规划): 见官方解答  todo 还是01背包问题
     */
    int count01 = 0;

    public int findTargetSumWays(int[] nums, int target) {
        dfs(nums, target, 0, 0);
        return count01;
    }

    void dfs(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) count01++;
        } else {
            dfs(nums, target, index + 1, sum - nums[index]);
            dfs(nums, target, index + 1, sum + nums[index]);
        }
    }

    /**
     * 题目08(leetcode 第17题): 电话号码的字母组合
     * 描述: 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     */
    Map<Character, String> phoneMap = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};
    List<String> res = new ArrayList<>();

    public List<String> letterCombinations(String digits) { //回溯法(官方)
        if (digits.length() == 0) return res;
        dfs01(digits, 0, new StringBuffer());
        return res;
    }

    public void dfs01(String digits, int index, StringBuffer combination) {
        if (index == digits.length()) res.add(combination.toString());
        else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersConut = letters.length();
            for (int i = 0; i < lettersConut; i++) {
                combination.append(letters.charAt(i));
                dfs01(digits, index + 1, combination);
                combination.deleteCharAt(combination.length() - 1);
            }
        }
    }
}
