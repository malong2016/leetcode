package com.kwl.data01.leetCode题目.LinkList题组.swordOffer;

import com.kwl.data01.dataStructure.ListNode;

import java.util.Stack;

/**
 * 剑指offer_链表_题组01(每个题组是4个题目)
 *
 * @author kuang.weilin
 * @date 2021/2/13
 */
public class LinkSword_01 {


    /**
     * 题目1(swordOffer 面试题18):在O(1)的时间内删除链表的节点
     * 描述:给定单向链表的头指针和一个节点指针,定义一个函数在O(1)的时间内删除该节点(假设一定存在,my add)
     * <p>
     * 思路_01:如果存在后继节点,将后继节点的值赋值给deleteNode,然后删除后继节点
     * 不存在后继节点
     * 1)deleteNode是headNode,直接将headNode置空
     * 2)不是头节点,扫描链表找到该节点的前继节点,在删除
     * 注意: 如果直接从头节点扫描找到deleteNode的前继节点进行删除操作,时间复杂度是O(n),不满足题目,(这种扫描最好设置一个头节点(无val域),好查找和删除)
     * 所有一定替代删除的节点,直接从后删除!!!!
     */
    public static void deleteNode(ListNode headNode, ListNode deleteNode) {
        if (headNode == null || deleteNode == null) return;
        if (deleteNode.next != null) {
            deleteNode.val = deleteNode.next.val;
            deleteNode.next = deleteNode.next.next;
        } else if (deleteNode == headNode) {       //是头节点,而且只有一个节点
            headNode = null;
            deleteNode = null;
        } else {
            ListNode p = headNode;   //定义一个扫描节点,指向headNode,p最后指向deleteNode的前继节点
            while (p.next != deleteNode) {          //不断向下扫描
                p = p.next;      //向下扫描
            }
            p.next = null;         //找到(注意:这是一定存在的)
        }
    }

    /**
     * 题目2(swordOffer 面试题24):定义一个函数,输入一个链表头节点,反转该链表
     * <p>
     * 思路:  1)头插法构造链表,因为面试链表一般是没有headNode(置null)这种,要构造一个新的头节点,麻烦,一般不使用
     * 2)原地逆转,保留逆转的后继节点,扫描节点可以继续扫描!!!(注意传入节点为null或者单个节点已经考虑了)
     */
    public static ListNode reverseList(ListNode headNode) {
        ListNode pre = null;           //p要逆转的node,pre前继节点,pNodeNext是p节点的后继节点
        ListNode p = headNode;
        while (p != null) {        //先逆转,在移动
            ListNode pNodeNext = p.next;
            p.next = pre;             //逆转
            pre = p;                   //pre,p2右移动
            p = pNodeNext;
        }
        return pre;              //pre节点最后指向原链表的表尾
    }

    /**
     * 题目3(swordOffer 面试题22):输入一个链表,输出该链表中倒数第K个节点(注意,尾节点是倒数第一个节点)
     * &&假设链表(如果不为null)长度>=k
     * 思路: 1)先计算出链表的长度n,在从head扫描到(n-k+1)个节点,就是倒数第k个节点.(如果只能扫描一次,这个方法不适合)
     * 2)定义双指针,pFront,pBack,先让pFront走[k-1]步,第k步开始,pFront,pBack同时走,p1扫描到链表最后一个节点
     * return pBack(就是倒数第一个节点)
     */
    public static ListNode findKthToTail(ListNode headNode, int k) {
        if (headNode == null || k <= 0) return null;         //传入空链表或者k为非正整数,此时节点不存在,返回null
        ListNode pFront = headNode;
        ListNode pBack = headNode;
        for (int i = 0; i < k - 1; i++) {
            pFront = pFront.next;
        }
        while (pFront.next != null) {
            pFront = pFront.next;
            pBack = pBack.next;
        }
        return pBack;
    }

    /**
     * 题目4(swordOffer 面试题25):输入二个递增的链表,合并这二个链表并使得新链表中的节点依旧是排序的
     * <p>
     * 思路01:p指针指向待合成的新链表,二个指针p1 p2依次对应扫描pHead1 pHead2链表
     * 比较p1.data和p2.data大小,p指向较小节点,较小节点对应的指针右移动,当p1或者p2为null，扫描结束.
     * 扫描结束之后,如果p1或者p2不为null,这是多余的链表,将p指向不为null的p1或者p2
     * 思路02: 递归 todo
     */
    public static ListNode mergeLinkList(ListNode l1, ListNode l2) {
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
        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;     //如果传入有一个为null也不怕,这里可以处理
        return prehead.next;
    }




    public static void main(String[] args) {
        ListNode headNode = new ListNode(1);
        ListNode node01 = new ListNode(2);
        ListNode node02 = new ListNode(3);
        ListNode node03 = new ListNode(4);
        headNode.next = node01;
        node01.next = node02;
        node02.next = node03;         //链表是1-->2-->3-->4
        node03.next = null;

//        ListNode head02 = new ListNode(1,null);
//
//        System.out.println("原节点是:");
//        ListNode.printLinkList(headNode);
//        System.out.println("题目1:删除之后的节点");
//        deleteNode(headNode, node03);
//        LinkListNode.printLinkList(headNode);

        //题目2: 链表逆转
//        System.out.println("逆转之后的节点打印是:");
//        LinkListNode.printLinkList(reverseList(headNode));

        //题目3 返回倒数第k个节点
//        System.out.println("返回倒数第k个节点");
//        System.out.println(findKthToTail(headNode, 3).data);

        //题目4 合并二个排序的链表
//        System.out.println("合并二个排序的链表:");
//        ListNode.printLinkList(mergeLinkList(headNode,head02));


    }

}
