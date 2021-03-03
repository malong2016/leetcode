package com.kwl.data01.leetCode题目.Stack题组.swordOffer;

import java.util.Stack;

/**
 * 剑指offer_栈(stack)题组01
 *
 * @author kuang.weilin
 * @date 2021/2/14
 */
public class StackSword_01 {

    /**
     * 题目1(swordOffer 面试题9):用二个栈实现一个队列.队列的声明如下:
     * 二个方法appendTail和deleteHead,请分别完成在队列尾部插入节点和队列头部删除节点的功能
     * <p>
     * 思路01: 栈A进行接收插入的队列的元素,如果要弹出,
     * 1)先判断栈B是否是null,如果是null,就将栈A所有的元素依次入栈到栈B,在从栈B栈顶弹出元素
     * 2)如果栈B不是null,就从栈B弹出元素(栈B栈顶的元素相对本栈是最先入队的,相对栈A也是先入队的,所有是最先出队)
     */

    public static Integer appendTail(Stack<Integer> stack01, Integer element) {    //入队,只需要栈01
        return stack01.push(element);      //返回入栈的value
    }

    public static Integer deleteHead(Stack<Integer> stack01, Stack<Integer> stack02) throws Exception {
        if (stack01.isEmpty() && stack02.isEmpty()) throw new Exception("队列中元素为空,不能出队!!");
        if (stack02.isEmpty()) {            //如果栈2为null,将栈1的所有元素入栈2
            while (!stack01.isEmpty()) {
                stack02.push(stack01.pop());
            }
        }
        return stack02.pop();                    //返回出栈元素(注意java出栈是返回出栈元素,C++返回void!!!)
    }

    /**
     * 题目2(swordOffer 面试题31):栈的压入、弹出序列       time:2月14号  todo 还没有做完,等参考leetcode
     * 描述: 输入二个整数序列,第一个表示栈的压入序列,请判断第二个序列是否为栈的弹出序列
     * eg: {1,2,3,4,5}是压入序列,{4,5,3,2,1}是对应的一个弹出序列,{4,3,5,2,1}不可能是弹出序列
     * <p>
     * 思路01: 设置一个辅助栈A,遍历popArr[],如果value等于辅助栈栈顶,辅助栈出栈,继续遍历
     * 反之,压入未入栈pushArr,直到栈顶元素等于value,如果pushArr全部压入完成,此时栈顶元素还是不等于value,就返回false.
     * <p>
     * 如果最后遍历完成popArr,辅助栈为null,就返回true,此时popArr是栈的弹出序列
     */
    public boolean isPopOrder(int[] pushArr, int[] popArr) {
        Stack<Integer> stack = new Stack<>();
        int pushIndex = 0;         //压入栈的索引编号
        for (int i = 0; i < popArr.length; i++) {
            if (popArr[i] == stack.peek()) {
                stack.pop();
            } else {
                for (; pushIndex < pushArr.length; pushIndex++) {
                    if (stack.peek() != popArr[i]) {
                        stack.push(pushArr[pushIndex]);
                    }
                }
                if (pushIndex==pushArr.length){      //溢出就是找不到
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 题目3(swordOffer 面试题30):包含min函数的栈
     * 描述: 定义栈的数据结构,请在该类型中实现一个能够得到栈的最小元素的min函数
     * 在该栈中调用min,push,pop的时间复杂度都是o(1)
     *
     * 思路01(swordOffer解题): 定义一个辅助栈,每次main栈压入元素,
     * 在辅助栈也压入最小value(和栈顶元素进行比较,如果小于栈顶,压入本元素,大于，还是压入辅助栈栈顶元素)
     * ,main栈弹出元素,辅助栈也弹出元素
     */
    public static void minPush(Stack<Integer> mainStack,Stack<Integer> minValueStack,Integer data){ //压入元素
        mainStack.push(data);
        if (minValueStack.isEmpty()) minValueStack.push(data);
        else minValueStack.push(Math.min(minValueStack.peek(), data));   //和栈顶元素进行比较,比较小的值压入辅助栈
    }
    public static void minPop(Stack<Integer> mainStack,Stack<Integer> minValueStack) throws Exception {
        if (mainStack.isEmpty()) throw new Exception("栈为空,不能弹出元素");
        mainStack.pop();             //主栈和辅助栈全部弹出元素
        minValueStack.pop();
    }
    public static Integer findStackMinValue(Stack<Integer> minValueStack) throws Exception {
        if (minValueStack.isEmpty()) throw new Exception("栈为空,无最小元素");
        return minValueStack.peek();
    }



    public static void main(String[] args) throws Exception {
        System.out.println("题目1(swordOffer 面试题9):用二个栈实现一个队列.队列的声明如下:");
//        Stack<Integer> stack01 = new Stack<>();
//        Stack<Integer> stack02 = new Stack<>();
//
//        //入队
//        appendTail(stack01, 1);
//        appendTail(stack01, 2);
//        appendTail(stack01, 3);
//
//        appendTail(stack02, 4);        //加入原本就有元素
//
//        //出队测试
//        System.out.println("第一次出队:");
//        System.out.println(deleteHead(stack01, stack02));

        System.out.println("题目3(swordOffer 面试题30):包含min函数的栈");
        Stack<Integer> mainStack = new Stack<>();
        Stack<Integer> minValueStack = new Stack<>();    //储存最小值的栈
        minPush(mainStack,minValueStack,2);
        minPush(mainStack,minValueStack,1);
        minPush(mainStack,minValueStack,3);
        System.out.println("打印一下此时minValueStack的元素:"+minValueStack);
    }
}
