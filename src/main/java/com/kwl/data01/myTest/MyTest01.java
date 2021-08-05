package com.kwl.data01.myTest;


import com.kwl.data01.dataStructure.Array;
import com.kwl.data01.dataStructure.ListNode;
import com.kwl.data01.dataStructure.TreeNode;

import java.util.*;

/**
 * @author kuang.weilin
 * @date 2021/7/3 0:08
 */
public class MyTest01 {


    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if (dfs(board,word,i,j,0)) return true;   //只要一个返回true,就直接返回true
            }
        }
        return false;
    }

    boolean dfs(char[][] board, String word, int i, int j,int index){
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1 || board[i][j] != word.charAt(index)){
            return false;           //越界或者不匹配
        }
        if (index == word.length() - 1) return true;
        board[i][j] = '\0';        //设置为空额
        boolean res = dfs(board,word,i - 1,j,index + 1) || dfs(board,word,i + 1,j,index + 1)
                || dfs(board,word,i,j - 1,index + 1) || dfs(board,word,i,j + 1,index + 1);
        board[i][j] = word.charAt(index);    //这里是一定等于的
        return res;
    }




}
