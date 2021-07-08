package com.kwl.data01.HOT100.LinkedList题组;

import com.kwl.data01.dataStructure.ListNode;

/**
 * @author kuang.weilin
 * @date 2021/7/3 23:16
 */
public class LinkList_Hot100_02 {

    /**
     * 题目01(leetcode 第206题): 反转链表
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode p = head;
        while ( p!= null){
            ListNode temp = p.next;
            p.next = pre;
            pre = p;
            p = temp;
        }
        return pre;
    }


    /**
     * 题目02(leetcode 第83题): 删除排序链表中的重复元素
     * 描述: 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
     * 返回同样按升序排列的结果链表。
     *
     * 思路01: 单指针进行扫描
     * 思路02: 递归(暂时还是没有理解的)
     */
    public ListNode deleteDuplicates01(ListNode head) {   //单指针
        if (head == null) return null;
        ListNode p = head;
        while (p.next != null) {
            if (p.val == p.next.val) p.next = p.next.next;     //删除节点
            else p = p.next;
        }
        return head;
    }
    public ListNode deleteDuplicates02(ListNode head) {      //递归
        if(head == null || head.next == null){
            return head;
        }
        head.next = deleteDuplicates02(head.next);
        if(head.val == head.next.val) head = head.next;
        return head;
    }

    /**
     * 题目03(leetcode  第2题): 两数相加
     * 描述: 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序(反过来就是顺序)的方式存储的，并且每个节点只能存储 一位 数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。你可以假设除了数字 0 之外，这两个数都不会以 0 开头
     * eg: 2->4->3 和 5->6->4  343+465=807 返回7->0->8
     * <p>
     * 思路01: 注意设置头节点，和处理末尾carry
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return null;
        ListNode res = new ListNode(-1);
        ListNode p = res;
        int carry = 0;
        while (l1 != null || l2 != null) {      //l1 == null && l2 == null就跳出循环
            int l1Val = 0, l2Val = 0;     //默认值
            if (l1 != null) {
                l1Val = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                l2Val = l2.val;
                l2 = l2.next;
            }
            int newVal = (l1Val + l2Val + carry) % 10;
            p.next = new ListNode(newVal);
            carry = (l1Val + l2Val + carry) / 10;
            p = p.next;
        }
        p.next = carry == 0 ? null : new ListNode(carry);    //有多余的进位就加入,注意这里多余的进位只能是1
        return res.next;
    }

}
