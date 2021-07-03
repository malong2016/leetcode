package com.kwl.data01.LeetCode_HOT100.回溯_递归;

import java.util.*;

/**
 * @author kuang.weilin
 * @date 2021/2/23
 */
public class Dfs_Hot100_01 {

    /**
     * 题目1(leetcode 78题): 子集
     * 描述: 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     * <p>
     * eg:nums = [1,2,3]  --> [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
     * nums = [0]   --> [[],[0]]
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();     //这是返回的结果集!!
        LinkedList<Integer> path = new LinkedList<>();
        dfs(0, nums, res, path);
        return res;
    }

    public static void dfs(int index, int[] nums, List<List<Integer>> res, LinkedList<Integer> path) {
        res.add(new LinkedList<>(path));      //要动态分配空间,temp总在变化,所以要动态分配
        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(i + 1, nums, res, path);
            path.removeLast();
        }
    }

    /**
     * 题目2(leetcode 46): 全排列
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
        if (index == nums.length) res.add(new ArrayList<>(path));   //如果遍历完成一轮,就添加到res中,在这里就已经做完了
        for (int i = index; i < nums.length; i++) {
            Collections.swap(path, index, i);
            dfs(nums, index + 1, res, path);
            Collections.swap(path, index, i);    //回溯到原来的状态
        }
    }

    public List<List<Integer>> permuteAndVisit(int[] nums) {     //使用visit[i]方法
        List<List<Integer>> res = new ArrayList<>();
        int[] visited = new int[nums.length];
        backtrack(res, nums, new ArrayList<Integer>(), visited);
        return res;
    }

    private void backtrack(List<List<Integer>> res, int[] nums, ArrayList<Integer> tmp, int[] visited) {
        System.out.println("我是全排列tmp = " + tmp);
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {     //总是从0开始搜索，等价于就是在交换元素
            if (visited[i] == 1) continue;     //搜索之后不能再搜索本身
            visited[i] = 1;
            tmp.add(nums[i]);
            backtrack(res, nums, tmp, visited);
            visited[i] = 0;
            tmp.remove(tmp.size() - 1);
        }
    }

    /**
     * 题目3(leetcode 39): 组合总和
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
     * 题目4(leetcode 22): 括号生成
     * 描述: 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * eg: n = 3 -->  ["((()))","(()())","(())()","()(())","()()()"]
     */

    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        dfs("", n, 0, 0, res);
        return res;
    }

    public void dfs(String ans, int n, int lc, int rc, List<String> res) {
        // 剪枝 右边扣号数量大于左边
        if (rc > lc || lc > n) return;
        // 满足结果
        if (lc == n && lc == rc) res.add(ans);
        // 遍历+递归
        dfs(ans + '(', n, lc + 1, rc, res);
        dfs(ans + ')', n, lc, rc + 1, res);
    }

    /**
     * 题目5(leetcode 15题):三数之和
     * 描述: 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * eg: nums = [-1,0,1,2,-1,-4] --> [[-1,-1,2],[-1,0,1]]
     * <p>
     * 思路01: 暴力解
     * 思路02: 排序+遍历+回溯+剪枝
     */
    public List<List<Integer>> threeSum(int[] nums) {
        return null;
    }

    /**
     * 题目6(leetcode 79题): 单词搜索
     * 描述: 给定一个二维网格和一个单词，找出该单词是否存在于网格中
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
     * 同一个单元格内的字母不允许被重复使用。
     * eg: board = [
     *    ['A','B','C','E'],
     *    ['S','F','C','S'],
     *    ['A','D','E','E']
     * ]
     * 给定 word = "ABCCED", 返回 true  给定 word = "SEE", 返回 true
     *
     * 思路01: 回溯法
     */
    /**
     * 题目7(leetcode 79): 单词搜索
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
                if (dfs09(board, word, i, j, 0)) return true;
            }
        }
        return false;
    }

    boolean dfs09(char[][] board, String word, int i, int j, int index) {
        //越界&&最后一个值不等于word最后一个值
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word.charAt(index))
            return false;
        if (index == word.length() - 1) return true;      //index如果不断扩大到word.length - 1就可以
        char temp = board[i][j]; //暂时保存值
        board[i][j] = '.';     //修改值,复制被重复访问
        boolean res = dfs09(board, word, i + 1, j, index + 1) || dfs09(board, word, i - 1, j, index + 1) ||
                dfs09(board, word, i, j + 1, index + 1) || dfs09(board, word, i, j - 1, index + 1);   //从上下左右开始搜索
        board[i][j] = temp;
        return res;
    }
}
