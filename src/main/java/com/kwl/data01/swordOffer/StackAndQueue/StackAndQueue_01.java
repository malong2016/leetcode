package com.kwl.data01.swordOffer.StackAndQueue;

import java.util.Stack;

/**
 * 剑指offer_栈(stack)题组01
 *
 * @author kuang.weilin
 * @date 2021/2/14
 */
public class StackAndQueue_01 {

    /**
     * 题目01(swordOffer 第09题): 用两个栈实现队列
     * 二个方法appendTail和deleteHead,请分别完成在队列尾部插入节点和队列头部删除节点的功能
     *
     * 出栈失败是返回-1（leetcode）
     *
     * 代码参考: com.kwl.data01.dataStructure.题目Struct.CQueue
     *
     *
     * 思路01: 栈A进行接收插入的队列的元素,如果要弹出,
     * 1)先判断栈B是否是null,如果是null,就将栈A所有的元素依次入栈到栈B,在从栈B栈顶弹出元素
     * 2)如果栈B不是null,就从栈B弹出元素(栈B栈顶的元素相对本栈是最先入队的,相对栈A也是先入队的,所有是最先出队)
     */


    /**
     * 题目02(swordOffer 第31):栈的压入、弹出序列
     * 描述: 输入二个整数序列,第一个表示栈的压入序列,请判断第二个序列是否为栈的弹出序列
     * eg: {1,2,3,4,5}是压入序列,{4,5,3,2,1}是对应的一个弹出序列,{4,3,5,2,1}不可能是弹出序列
     * <p>
     * 思路01: 设置一个辅助栈A,遍历popArr[],如果value等于辅助栈栈顶,辅助栈出栈,继续遍历
     * 反之,压入未入栈pushArr,直到栈顶元素等于value,如果pushArr全部压入完成,此时栈顶元素还是不等于value,就返回false.
     * <p>
     * 如果最后遍历完成popArr,辅助栈为null,就返回true,此时popArr是栈的弹出序列
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for (Integer num : pushed) {     //遍历压入队列
            stack.push(num);
            while (!stack.isEmpty() && stack.peek() == popped[i]) {
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 题目03(swordOffer 第30题):包含min函数的栈
     * 描述: 定义栈的数据结构,请在该类型中实现一个能够得到栈的最小元素的min函数
     * 在该栈中调用min,push,pop的时间复杂度都是o(1)
     *
     * 参考:com.kwl.data01.dataStructure.题目Struct.MinStack
     */

    /**
     * 题目04(swordOffer 第59题-II):队列的最大值
     * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
     * 若队列为空，pop_front 和 max_value 需要返回 -1
     *
     *
     * 参考com.kwl.data01.dataStructure.题目Struct.MaxQueue
     */
    /**
     * 题目05(swordOffer 第41题): 数据流中的中位数
     * 描述: 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
     * 例如:
     * [2,3,4] 的中位数是 3
     * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
     * 设计一个支持以下两种操作的数据结构：
     * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
     * double findMedian() - 返回目前所有元素的中位数
     *
     * 参考: com.kwl.data01.dataStructure.题目Struct.MedianFinder
     */
}
