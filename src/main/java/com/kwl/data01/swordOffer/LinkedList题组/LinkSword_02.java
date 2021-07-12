package com.kwl.data01.swordOffer.LinkedList题组;

import com.kwl.data01.dataStructure.ListNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author kuang.weilin
 * @date 2021/7/12 14:41
 */
public class LinkSword_02 {


    /**
     * 题目01(swordOffer 第06题):从尾到头打印链表
     */
    public int[] reversePrint(ListNode head) {
        List<Integer> res = new LinkedList<>();
        reverse(head, res);
        int[] resInt = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            resInt[i] = res.get(i);
        }
        return resInt;
    }
    void reverse(ListNode head, List<Integer> res){
        if (head != null){
            reverse(head.next, res);
            res.add(head.val);
        }
    }

    /**
     * 题目02(swordOffer 第22题): 链表中倒数第k个节点
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) return head;
        ListNode fast = head,slow = head;
        for (int i = 0; i < k; i++) {      //先走k步
            fast = fast.next;
        }
        while (fast != null) {      //最后fast == null
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
    public ListNode getKthFromEnd01(ListNode head, int k) {        //优化
        if (head == null) return null;
        ListNode fast = head, slow = head;
        for (int i = 0; fast != null; i++) {
            fast = fast.next;
            if (i >= k) slow = slow.next;
        }
        return slow;
    }

    /**
     * 题目03(swordOffer 第25题): 合并两个排序的链表
     */
    public  ListNode mergeTwoLists(ListNode l1, ListNode l2) {      //思路01: 非递归法
        ListNode res = new ListNode(-1);
        ListNode p = res;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        p.next = l1 == null ? l2 : l1;
        return res.next;
    }

    public  ListNode mergeTwoLists01(ListNode l1, ListNode l2) {  //思路02: 递归法
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists01(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists01(l1, l2.next);
            return l2;
        }
    }
}
