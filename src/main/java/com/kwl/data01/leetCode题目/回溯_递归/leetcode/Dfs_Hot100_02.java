package com.kwl.data01.leetCode题目.回溯_递归.leetcode;

/**
 * @author kuang.weilin
 * @date 2021/2/28
 */
public class Dfs_Hot100_02 {


    /**
     * 题目1(leetcode 79): 单词搜索
     * 描述:给定一个二维网格和一个单词，找出该单词是否存在于网格中。
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
     * 同一个单元格内的字母不允许被重复使用。
     * eg: board =
     * [
     *   ['A','B','C','E'],
     *   ['S','F','C','S'],
     *   ['A','D','E','E']
     * ]
     * 给定 word = "ABCCED", 返回 true
     * 给定 word = "SEE", 返回 true
     * 给定 word = "ABCB", 返回 false
     *
     * 思路01(回溯法):
     */
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs09(board,word,i,j,0)) return true;
            }
        }
        return false;
    }
    boolean dfs09(char[][] board,String word,int i,int j,int index){
        //越界&&最后一个值不等于word最后一个值
        if (i>=board.length || i<0 || j>=board[0].length || j<0 || board[i][j] != word.charAt(index)) return false;
        if (index == word.length() - 1) return true;      //index如果不断扩大到word.length - 1就可以
        char temp = board[i][j]; //暂时保存值
        board[i][j] = '.';     //修改值,复制被重复访问
        boolean res = dfs09(board,word,i+1,j,index+1) || dfs09(board,word,i-1,j,index+1)||
                dfs09(board,word,i,j+1,index+1) || dfs09(board,word,i,j-1,index+1);   //从上下左右开始搜索
        board[i][j] = temp;
        return res;
    }

}
