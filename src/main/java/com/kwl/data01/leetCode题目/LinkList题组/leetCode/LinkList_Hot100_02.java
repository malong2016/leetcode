package com.kwl.data01.leetCode题目.LinkList题组.leetCode;

import com.kwl.data01.dataStructure.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * leetcode热题HOT100_链表_题组02(每个题组是4个题目)
 *
 * @author kuang.weilin
 * @date 2021/2/22
 */
public class LinkList_Hot100_02 {


    /**
     * 题目1(leetcode 21题): 合并二个有序列表
     * 描述: 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的
     * <p>
     * 思路01: 非递归法
     * 思路02: 递归法
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {      //思路01: 非递归法
        ListNode head = new ListNode(-1);
        ListNode p = head;
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
        p.next = l1 == null ? l2 : l1;    //注意p此时不为null!!!
        return head.next;
    }

    public static ListNode mergeTwoLists01(ListNode l1, ListNode l2) {  //思路01: 递归法
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {          //注意,此时l1和l2都是不为null
            l1.next = mergeTwoLists01(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists01(l1, l2.next);
            return l2;
        }
    }

    /**
     * 题目2(leetcode 23题): 合并K个升序链表
     * 描述: 给你一个链表数组，每个链表都已经按升序排列,请你将所有链表合并到一个升序链表中，返回合并后的链表。
     * eg: lists = [[1,4,5],[1,3,4],[2,6]]  --> [1,1,2,3,4,4,5,6]
     * <p>
     * 思路01(my): 先二个合并,合并成一个新的,在继续合并
     * 思路02:分治算法
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;  //传入null或者{},返回null
        ListNode res = null;
        for (int i = 0; i < lists.length; i++) {
            res = mergeTwoLists01(res, lists[i]);          //和返回的链表res不断的合并
        }
        return res;
    }

    /**
     * 题目3(leetcode 142题): 环形链表 II
     * 描述: 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * 注意: 不允许修改给定的链表
     * 进阶: 是否可以用空间复杂度o(1)处理这个问题
     * <p>
     * 思路01(时间复杂度是o(n)): 利用HashSet不断添加结点到里面,如果有重复就返回false
     * 思路02: 快慢指针???  todo 理解
     */
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) return head;
            head = head.next;
        }
        return null;    //如果没有环或者传入null,就返回null
    }

    /**
     * 题目4(leetcode  2题): 两数相加
     * 描述: 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序(反过来就是顺序)的方式存储的，并且每个节点只能存储 一位 数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。你可以假设除了数字 0 之外，这两个数都不会以 0 开头
     * eg: 2->4->3 和 5->6->4  343+465=807 返回7->0->8
     * <p>
     * 思路01(my): 设置进位为carry,链表处的位置(n1+n2+carry)%10,进位是(n1+n2+carry)/10
     * 核心: 如果链表长度不一样,可以认为短链表后面有若干个0,遍历结束之后,如果carry>0,就要在链表后面附加一个节点!!!
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return null;
        ListNode res = new ListNode(-1);      //返回的链表,设置一个头节点,方便插入
        ListNode p = res;
        int carry = 0; //设置进位默认是为0
        while (l1 != null || l2 != null) {       //有一个不为null,全部为null,结束循环
            int l1Val = 0, l2Val = 0;     //先设置一个默认值
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
        if (carry!=0) p.next = new ListNode(carry);    //有多余的进位,就加一个节点
        else p.next = null;            //没有就讲尾指针.next设置为null
        return res.next;
    }

    public static void main(String[] args) {
        System.out.println("题目4(leetcode  2题): 两数相加");
        ListNode list01 = new ListNode(2);
        list01.next = new ListNode(4);
        list01.next.next = new ListNode(3);

        ListNode list02 = new ListNode(5);
        list02.next = new ListNode(6);
        list02.next.next = new ListNode(4);

        ListNode.printLinkList(addTwoNumbers(list01, list02));
    }

}
