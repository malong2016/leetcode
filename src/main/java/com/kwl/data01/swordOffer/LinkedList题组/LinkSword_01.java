package com.kwl.data01.swordOffer.LinkedList题组;

import com.kwl.data01.dataStructure.ListNode;
import com.kwl.data01.dataStructure.题目Struct.ComplexLinkList;

import java.util.*;

/**
 * 剑指offer_链表_题组01
 *
 * @author kuang.weilin
 * @date 2021/2/13
 */
public class LinkSword_01 {


    /**
     * 题目01(swordOffer 第18  --本题是swordOffer原题):在O(1)的时间内删除链表的节点
     * 描述:给定单向链表的头指针和一个节点指针,定义一个函数在O(1)的时间内删除该节点
     *
     *
     * 思路_01:如果存在后继节点,将后继节点的值赋值给deleteNode,然后删除后继节点
     * 1)不存在后继节点
     * 2)deleteNode是headNode,直接将headNode置空
     * 3)不是头节点,扫描链表找到该节点的前继节点,在删除
     *
     * 思路02(看leetcode原题): 直接从头节点扫描找到deleteNode的前继节点进行删除操作(设置一个无val头节点),时间复杂度是O(n)
     */
    public  void deleteNode(ListNode headNode, ListNode deleteNode) {
        if (headNode == null || deleteNode == null) return;
        if (deleteNode.next != null) {                   //情况01-存在后继节点
            deleteNode.val = deleteNode.next.val;
            deleteNode.next = deleteNode.next.next;
        } else if (deleteNode == headNode) {          //情况02-删除节点是头节点
            headNode = null;
            deleteNode = null;
        } else {                                   //情况03-删除节点是尾节点，要找到尾巴节点的前一个节点
            ListNode p = headNode;
            while (p.next != deleteNode) {
                p = p.next;
            }
            p.next = null;
        }
    }
    /**
     * 题目02(swordOffer 第18题  --本题leetcode改动了):删除链表的节点
     * 描述: 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
     * 返回删除后的链表的头节点。
     */
    public ListNode deleteNode(ListNode head, int val) {
        ListNode res = new ListNode(-1);    //设置一个null的头节点,处理一个元素好处理
        res.next = head;
        ListNode p = res;     //扫描指针
        while (p.next != null){
            if (p.next.val == val){
                p.next = p.next.next;
                break;    //因为链表中的值各自不同,只要找到待删除节点,就立刻跳出循环
            }
            p = p.next;
        }
        return res.next;
    }


    /**
     * 题目02(swordOffer 第22):输入一个链表,输出该链表中倒数第K个节点(注意,尾节点是倒数第一个节点)
     */
    public  ListNode findKthToTail(ListNode head, int k) {
        if (head == null) return null;        //记住,要先判断一下子
        ListNode fast = head, slow = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 题目03(swordOffer 第23):链表环中的入口节点
     * 思路01: HashSet方法
     * 思路02: fast和slow先找到第一次相遇的指针位置，然后fast指向head,同时跑起来while(q == p)第二次相遇就是所求的节点
     * 数学解释: fast = 2*slow = slow + nb(循环一圈要b步) --> slow = nb   （你在圈圈里面要追上一个人，那么就要领先n圈）
     * 所以慢指针走 nb + a(到入口节点要的步数a)达到入口节点
     */
    public  boolean meetingNode(ListNode head) {
        Set<Integer> seen = new HashSet<Integer>();
        while (head != null) {
            if (!seen.add(head.val)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 题目04(swordOffer 第24题): 反转链表
     *
     * 思路: 双指针pre(初始化指向null)和p进行扫描逆转,后继指针temp用来保留p的后继
     */
    public  ListNode reverseList(ListNode headNode) {
        ListNode pre = null;
        ListNode p = headNode;
        while (p != null) {
            ListNode pNodeNext = p.next;
            p.next = pre;
            pre = p;
            p = pNodeNext;
        }
        return pre;
    }



    /**
     * 题目05(swordOffer 第25):输入二个递增的链表,合并这二个链表并使得新链表中的节点依旧是排序的
     *
     * 思路01: 如下，不断比较进行合并
     * 思路02: 利用归并思想，递归合并
     *
     */
    public  ListNode mergeLinkList(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null ? l2 : l1;
        return prehead.next;
    }


    /**
     * 题目06(swordOffer 第52题): 两个链表的第一个公共节点
     *
     * 思路01: 先走到null的链表A,链表A接入到链表B，同时达到公共节点
     * 思路02: HashSet(),先入HashSet,在遍历宁外一个链表
     */
    public  ListNode findFirstCommonNode_01(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return null;
        ListNode p1 = l1, p2 = l2;
        while (p1 != p2) {
            p1 = p1 == null ? l2 : p1.next;
            p2 = p2 == null ? l1 : p2.next;
        }
        return p1;
    }



    /**
     * 题目07(swordOffer 第35):复杂链表的复制
     * 描述: 请实现函数ComplexListNode clone(complexListNode pHead)复制一个复杂链表。
     * 在复杂链表中，每个节点除了有一个m_pNext指针指向下一个节点，还有一个m_pSibling指针指向链表
     * 的任意节点或者null
     * <p>
     * 思路01(空间复杂度是o(n)):利用HashMap建立原node和copyNode的一一映射关系,在遍历copyNode把next和random(按照原node的连接)连接上就可以
     * 思路02(空间复杂度是o(1)):在后面接一个节点
     */
    public ComplexLinkList copyRandomList(ComplexLinkList head) {      //HashMap
        Map<ComplexLinkList, ComplexLinkList> map = new HashMap<>();
        ComplexLinkList p = head;
        while (p != null) {                   //构建copy的节点
            map.put(p, new ComplexLinkList(p.val));       //建立一一映射关系
            p = p.next;
        }
        p = head;
        while (p != null) {                      //构建next和ramdom二个指针
            ComplexLinkList copyNode = map.get(p);
            copyNode.next = map.get(p.next);
            copyNode.random = map.get(p.random);
            p = p.next;
        }
        return map.get(head);
    }

    public ComplexLinkList complexLinkClone(ComplexLinkList head) {     //在后面接一个节点
        if (head == null) return null;
        ComplexLinkList p = head;
        while (p != null) {                //构建节点和next指针
            ComplexLinkList copyNode = new ComplexLinkList(p.val);
            copyNode.next = p.next; //指向p的下一个指针
            p.next = copyNode;
            p = p.next.next;
        }
        p = head;
        while (p != null) {                //构建random指针
            ComplexLinkList copyNode = p.next;
            if (p.random != null) {              //注意,这里如果是null,那么那就是random为null
                copyNode.random = p.random.next;        //这里p和后一个指针是一一映射的
            }
            p = p.next.next;
        }
        //断链!!!
        p = head;
        ComplexLinkList res = p.next; //保存copy链表的第一个节点
        while (p != null) {             //p在不断的遍历主链
            ComplexLinkList copyNode = p.next;      //复制的节点
            p.next = p.next.next;                    //主链断链,注意p.next是复制节点，所以一定是不为null的\
            if (p.next != null) {       //如果p不是最后一个节点
                copyNode.next = copyNode.next.next;      //copy链表断链
            }
            p = p.next;       //到主链第二个节点
        }
        return res;
    }


    /**
     * 题目07(swordOffer 第06题):从尾到头打印链表
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
     * 题目08(swordOffer 第22题): 链表中倒数第k个节点
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
     * 题目09(swordOffer 第25题): 合并两个排序的链表
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
