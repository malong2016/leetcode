package com.kwl.data01.algorithm;

/**
 * 回溯法(backTracking)
 *
 * @author kuang.weilin
 * @date 2021/2/16
 */
public class BackTracking {

    /**
     * 类似枚举,本质上是一个决策树模型，一层一层向下递归,尝试搜索答案
     * 1)找到答案->尝试别的可能,返回答案
     * 2)找不到答案-->返回上一层递归,尝试别的路径(这也是回溯法里面剪枝!!!)
     *
     * 回溯法的几个要素
     * 1 路径
     * 2 选择列表
     * 3 结束条件
     *
     * 模板是下列:
     *   res = [] # 定义全局变量保存最终结果
     *   state = [] # 定义状态变量保存当前状态
     *   p,q,r # 定义条件变量（一般条件变量就是题目直接给的参数）
     *   def back(状态，条件1，条件2，……):
     *      if # 不满足合法条件（可以说是剪枝）
     *              return
     *      elif # 状态满足最终要求
     *              res.append(state) # 加入结果
     *              return
     *      # 主要递归过程，一般是带有 循环体 或者 条件体
     *      for # 满足执行条件
     *      if # 满足执行条件
     *             back(状态，条件1，条件2，……)
     *    back(状态，条件1，条件2，……)
     *    return res
     * leetcode: 22题(括号生成) 78题(子集) 77题(组合)  46题(全排序)  八皇后问题和树独问题(相对复杂!!!)
     */
    public static void main(String[] args) {
    }
}
