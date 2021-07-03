package com.kwl.data01.LeetCode_HOT100.LinkList题组;

import com.kwl.data01.dataStructure.ListNode;

/**
 * @author kuang.weilin
 * @date 2021/7/3 23:16
 */
public class LinkList_Hot100_02 {


    /**
     * 题目2(leetcode 83题): 删除排序链表中的重复元素
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

}
