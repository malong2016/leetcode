package com.kwl.data01.dataStructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 输入理解链表 创建链表(头插法,尾插法)、正序/逆序遍历链表、链表的长度、快慢指针找中心节点
 * 注意:本类中头节点都是带val域!!!
 *
 * @author kuang.weilin
 * @date 2021/2/11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListNode {

    public int val;

    public ListNode next;


    public ListNode(int val) {
        this.val = val;
    }

    /**
     * 1 头插法创建链表
     */
    public static ListNode createLinkListByHead(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        ListNode head = new ListNode(arr[0], null);    //将插入arr的第一个元素作为head,head.next指向null
        for (int i = 1; i < arr.length; i++) {        //遍历数组,逐步进行头插
            ListNode curNode = new ListNode(arr[i]);
            curNode.next = head.next;
            head.next = curNode;
        }
        return head;
    }

    /**
     * 2  尾插法创建链表
     */
    public static ListNode createLinkListByTail(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        ListNode head = new ListNode(arr[0], null);    //将插入arr的第一个元素作为head,head.next指向null
        ListNode pTail = head;
        for (int i = 1; i < arr.length; i++) {             //尾指针始终指向创建链表的最后一个节点
            ListNode curNode = new ListNode(arr[i]);
            pTail.next = curNode;
            pTail = pTail.next;
        }
        pTail.next = null;
        return head;
    }

    /**
     * 3 正序遍历打印链表
     */
    public static void printLinkList(ListNode headNode) {
        while (headNode != null) {
            System.out.println(headNode.val);
            headNode = headNode.next;
        }
    }

    /**
     * 4 逆序打印链表(递归或者利用栈都可以)
     */
    public static void printListByReverse(ListNode p) {         //递归法
        if (p != null) {
            printListByReverse(p.next);
            System.out.println(p.val);
        }
    }

    /**
     * 5 计算链表的长度
     */
    public static int countNodeNum(ListNode headNode) {
        int nodeNum = 0;
        while (headNode != null) {
            nodeNum++;
            headNode = headNode.next;
        }
        return nodeNum;
    }

    /**
     * 6 快(fast)慢(slow)指针寻找链表中间的节点
     * 思路: 设置fast和slow指针,扫描链表,fast指针一次走二步,slow指针一次走一步
     * 当fast指针到达尾节点or尾节点的next,slow指针到达链表中间节点
     */
    public static ListNode getMidNodeByFastAndSlow(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {    //到最后一个节点或者跳出
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;              //如果有二个中点,slow指向后一个中点
    }





}
