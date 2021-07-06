package com.kwl.data01.LeetCode_HOT100.LinkedList题组;

import com.kwl.data01.dataStructure.ListNode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * leetcode热题HOT100_链表_题组01(每个题组是4个题目)
 *
 * @author kuang.weilin
 * @date 2021/2/21
 */
public class LinkList_Hot100_01 {


    /**
     * 题目1(leetcode 148题): 排序链表
     * 描述: 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表
     * 进阶: 你可以在 O(nlog2n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
     * <p>
     * 思路01(my):冒泡法和简单选择排序
     * 思路02(归并排序,leetcode K神思路): 设置快慢指针,每次都找到链表中间节点(如果有二个中心,选择前一个中心)
     * 进行二路归并排序(时间复杂度是o(log2n))
     */
    public static ListNode sortListByGb(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode temp = slow.next;
        slow.next = null;           //断链
        ListNode left = sortListByGb(head);    //指向二个链表的头节点
        ListNode right = sortListByGb(temp);
        ListNode h = new ListNode(-1);
        ListNode res = h;              //保留头节点,h是遍历指针
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left != null ? left : right;     //连接上多余的链表
        return res.next;
    }

    /**
     * 题目2(leetcode 第234题): 回文链表
     * eg:  1 2 2 1是回文链表 1 2 3 1不是回文链表
     * <p>
     * 思路01: 利用快慢指针找到链表的中间节点,后半段链表逆转,在用p1,p2分别扫描二链表进行比较
     * 思路02(一半链表入栈): 利用快慢指针找到链表的中间节点,后半段入栈,在依次出栈和前半段进行比较
     * 思路03:全部链表入栈,在出栈如果能一一对应,就返回true
     */
    public boolean isPalindrome01(ListNode head) {    //找到中点,翻转后半段,在对比?如果是奇数怎么办呢??
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode left = head;
        ListNode right = reverseList(slow);    //后半段,作为判断的条件(注意奇数和偶数都是可以的)
        while (right != null) {
            if (left.val != right.val) return false;
            left = left.next;
            right = right.next;
        }
        return true;
    }

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

    public boolean isPalindrome(ListNode head) {        //全部入栈,在比较
        Stack<Integer> stack = new Stack<>();
        ListNode p = head;       //注意后面也是要用到head,如果用head扫描会断链
        while (p != null) {
            stack.push(p.val);
            p = p.next;
        }
        p = head;
        while (p != null) {
            if (p.val != stack.pop()) return false;
            p = p.next;
        }
        return true;
    }


    /**
     * 题目3(leetcode 160题): 相交链表
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;
        while (p1 != p2) {
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }
        return p1;
    }

    public ListNode fun01(ListNode headA, ListNode headB) { //如果非common节点val和next相同,会报错
        if (headA == null || headB == null) return null;
        HashSet<ListNode> hashSet = new HashSet<>();
        while (headA != null) {
            hashSet.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (!hashSet.add(headB)) return headB;   //已经添加
            headB = headB.next;
        }
        return null;
    }

    /**
     * 题目4(leetcode 19题):删除链表的倒数第 N 个结点
     * 进阶: 一趟完成删除
     * 思路01: 先计算链表的长度length,要删除的是length-n+1节点,所以顺序扫描到要删除节点的前驱第length-n个节点
     * 思路02: 设置快慢指针,块指针先走k+1(前面有k+1个节点,到Null之后,fast到slow前面有k+1个,也就是倒数k+1个)
     * 然后慢指针和快指针一起走,快指针null,慢指针就到删除节点的前驱节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //方法二: 设置快慢指针,快指针先走k步(注意是达到第k+1个节点,前面就是k),最后快指针到null,前面有k个数,慢指针是倒数第k个节点
        if (head == null) return head;
        ListNode fast = head, slow = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null) {      //最后fast == null
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public ListNode removeNthFromEnd02(ListNode head, int n) {  //代码优化
        if (head == null) return head;
        ListNode fast = head, slow = head;
        for (int i = 0; fast != null; i++) {
            if (i >= n) slow = slow.next; //已经走了k步(0,1,2...k-1)
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 题目5(leetcode 21题): 合并两个有序链表
     * 描述: 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的
     * <p>
     * 思路01: 非递归法
     * 思路02: 递归法
     */
    public  ListNode mergeTwoLists(ListNode l1, ListNode l2) {      //思路01: 非递归法
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
        p.next = l1 == null ? l2 : l1;
        return head.next;
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

    /**
     * 题目6(leetcode 23题): 合并K个升序链表
     * 描述: 给你一个链表数组，每个链表都已经按升序排列,请你将所有链表合并到一个升序链表中，返回合并后的链表。
     * eg: lists = [[1,4,5],[1,3,4],[2,6]]  --> [1,1,2,3,4,4,5,6]
     * <p>
     * 思路01(通解): 先二个合并,合并成一个新的,在继续合并
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
     * 题目7(leetcode 第142题): 环形链表 II
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
     * 题目7(leetcode 第141题): 环形链表
     * 描述: 给定一个链表，判断链表中是否有环。
     *
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     *
     * 如果链表中存在环，则返回 true 。 否则，返回 false
     */
    public boolean hasCycle(ListNode head) {  //快慢指针找
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }

    /**
     * 题目8(leetcode  第2题): 两数相加
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
