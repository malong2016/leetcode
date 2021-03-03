package com.kwl.data01.leetCode题目.LinkList题组.swordOffer;

import com.kwl.data01.dataStructure.题目Struct.ComplexLinkList;
import com.kwl.data01.dataStructure.ListNode;

import java.util.*;

/**
 * 剑指offer_链表_题组02(每个题组是4个题目)
 *
 * @author kuang.weilin
 * @date 2021/2/13
 */
public class LinkSword_02 {

    /**
     * 题目1(swordOffer 面试题52): 输入二个链表,找出他们第一个公共节点
     * <p>
     * 思路01(最优解):计算出l1和l2长度,在长链表的指针先移动|l1-l2|步,在同时运动l1,l2,找到公共节点,结束
     * 时间复杂度o(n+m)
     * 思路02:二链表依次入二个栈,在出栈,找到最后一个相同的链表节点,就是所求节点
     * 时间复杂度o(n+m) 空间复杂度o(n+m)需要额外的空间,没有思路01好
     * 思路03:暴力解,遍历l1每个节点,依次和l2的每个节点比较,时间复杂度是o(nm),不推荐!!!
     */

    /**
     * 思路02:计算出l1和l2长度,在长链表的指针先移动|l1-l2|步,在同时运动l1,l2,找到公共节点,结束
     * 时间复杂度o(n+m)
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode findFirstCommonNode_01(ListNode l1, ListNode l2) {
        int l1Length = ListNode.countNodeNum(l1);
        int l2Length = ListNode.countNodeNum(l2);
        int lengthDiff = Math.abs(l1Length - l2Length); //l1和l2长度之差
        ListNode lLong = l1;              //默认l1长,l2短,lLong指向l1
        ListNode lShort = l2;
        if (l1Length < l2Length) {        //如果l2长,就改变一下lLong指向l2
            lLong = l2;
            lShort = l1;
        }
        //lLong先走lengthDiff步,此时lLong和lShort一样长,如果有一个为null,宁外一个会变为null
        for (int i = 0; i < lengthDiff; i++) lLong = lLong.next;
        while (lLong != lShort && lShort != null && lLong != null) {
            lLong = lLong.next;
            lShort = lShort.next;
        }
        return lLong;
    }

    /**
     * 题目2(swordOffer 面试题23):链表环中的入口节点
     * 思路01:利用HashSet不断加入LinkListNode到无序列表之内,如果返回false,说明有重复加入,就是入口节点(加不了循环节点???栈溢出)
     * 思路02: 先设置块满指针,相遇一定是在环中相遇meetNode,不断遍历meetNode,指针回到meetNode,就是环的节点数n
     * fastP先跑环的节点数n,然后fastP和shortP一起跑,相遇就是入口节点??/
     */
    public static boolean meetingNode(ListNode head) {
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
     * 题目3(swordOffer 面试题35):复杂链表的复制
     * 描述: 请实现函数ComplexListNode clone(complexListNode pHead)复制一个复杂链表。
     * 在复杂链表中，每个节点除了有一个m_pNext指针指向下一个节点，还有一个m_pSibling指针指向链表
     * 的任意节点或者null
     * <p>
     * 思路01(空间复杂度是o(n)):利用HashMap建立原node和copyNode的一一映射关系,在遍历copyNode把next和random(按照原node的连接)连接上就可以
     * 思路02(空间复杂度是o(1)):在原链表node后面接上copyNode,扫描原链表,找到random节点,random节点后一个节点也是copyNode!!!
     */
    public static ComplexLinkList complexLinkClone(ComplexLinkList head) {
        if (head == null) return null;
        ComplexLinkList cur = head;       //注意不能直接使用head进行遍历,因为后面要使用到head!!!
        HashMap<ComplexLinkList, ComplexLinkList> map = new HashMap<>();
        while (cur != null) {
            map.put(cur, new ComplexLinkList(cur.data));
            cur = cur.next;
        }
        cur = head;    //从新遍历,构造copyList的next和random指针,1本节点  2对应的next指向的节点 3对应random指向的节点
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    public static ComplexLinkList complexLinkClone02(ComplexLinkList head) {
        if (head == null) return null;
        ComplexLinkList cur = head;
        while (cur != null) {         //在原链表每个节点后面复制一个data一样的节点
            ComplexLinkList copyNode = new ComplexLinkList(cur.data);
            copyNode.next = cur.next;
            cur.next = copyNode;
            cur = copyNode.next;     //扫描器要走
        }
        cur = head;
        while (cur != null) {     //对原链表进行扫描
            if (cur.random!=null) cur.next.random = cur.random.next;     //如果当前node.random为null,就没有next
            cur = cur.next.next;    //cur.next肯定是不是null(因为是下一个copy节点!!!)
        }

        //3 拆分二链表
        cur = head.next;  //当前节点指向新链表
        ComplexLinkList pre = head;
        ComplexLinkList res = head.next;       //保存返回的结果node
        while (cur.next != null) {       //原node和copyNode进行断链处理
            pre.next = pre.next.next;
            cur.next = cur.next.next;
            pre = pre.next;    //倒数第二个节点,控制了cur.next!=null,那么pre.next一定不为null!11
            cur = cur.next;  //最后扫描到最后一个节点
        }
        pre.next = null;
        return res;
    }




    public static void main(String[] args) {
//        LinkListNode head01 = new LinkListNode(1);    //1-->2-->3-->4
//        head01.next = new LinkListNode(2);
//        LinkListNode head02 = new LinkListNode(-1);   //-1-->-2-->3-->4
//        head02.next = new LinkListNode(-2);
//        //共同的节点
//        LinkListNode commonNode01 = new LinkListNode(3);
//        LinkListNode commonNode02 = new LinkListNode(4);
//        commonNode01.next = commonNode02;
//        //连接到共同的节点
////        head01.next.next = commonNode01;
//        head02.next.next = commonNode01;

//        System.out.println("测试寻找到共同的节点:");
//        System.out.println(findFirstCommonNode_01(head01, head02));

//        System.out.println("链表环中的入口节点:");
//        LinkListNode node = new LinkListNode(11);
//        LinkListNode node02 = new LinkListNode(12);
//        LinkListNode node03 = new LinkListNode(13);
//        node.next = node02;
//        node02.next = node03;
//        node03.next = node02;
//                System.out.println(meetingNode(node));
//        LinkListNode.printLinkList(head01);

//        System.out.println("题目3(swordOffer 面试题35):复杂链表的复制:");  //如果节点有环,不能使用toString()!!!!
//        ComplexLinkList head = new ComplexLinkList(1);
//        ComplexLinkList node01 = new ComplexLinkList(2);
//        ComplexLinkList node02 = new ComplexLinkList(3);
//        head.next = node01;
//        node01.next = node02;
//        node02.random = node01;
//        ComplexLinkList complexLinkList = complexLinkClone(head);
//        System.out.println(complexLinkList.next.next.random.data);

        System.out.println("题目4(swordOffer 面试题37):序列化二叉树");

    }


}
