package com.kwl.data01.leetCode题目.LinkList题组.leetCode;

import com.kwl.data01.dataStructure.ListNode;

import java.util.HashSet;
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

    public static ListNode sortList(ListNode head) {       //冒泡法 超时了!!
        ListNode p = head;    //扫描指针,单纯的计数
        ListNode p1, pre;
        while (p != null) {
            pre = head;
            p1 = head.next;     //冒泡的扫描指针 pre在后,p1在前
            while (p1 != null) {
                if (pre.val > p1.val) {      //pre的数据大于p1的数据,就交换!!!
                    int temp = pre.val;
                    pre.val = p1.val;
                    p1.val = temp;
                }
                pre = p1;    //继续扫描
                p1 = p1.next;
            }
            p = p.next;       //p移动多少次就冒泡多少次
        }
        return head;
    }

    public static ListNode sortList01(ListNode head) {  //选择排序 超时!!!!
        ListNode p = head;
        ListNode p1, p1Min;   //p1是无序表的扫描指针,p1Max是记录最大的节点
        while (p != null) {
            p1 = p;
            p1Min = p;
            while (p1 != null) {
                if (p1.val < p1Min.val) p1Min = p1;    //指向最小的节点
                p1 = p1.next;       //扫描
            }
            int temp = p.val;     //交换数据
            p.val = p1Min.val;
            p1Min.val = temp;
            p = p.next;
        }
        return head;
    }


    /**
     * 题目2(leetcode 234题): 判断一个链表是否是回文链表
     * eg:  1 2 2 1是回文链表 1 2 3 1不是回文链表
     * <p>
     * 思路01: 利用快慢指针找到链表的中间节点,后半段链表逆转,在用p1,p2分别扫描二链表进行比较
     * 思路02(一半链表入栈): 利用快慢指针找到链表的中间节点,前半段入栈,在依次出栈和后半段进行比较
     * 思路03:全部链表入栈,在出栈如果能一一对应,就返回true
     */
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

    public boolean isPalindrome01(ListNode head) {    //找到中点,翻转后半段,在对比?如果是奇数怎么办呢??
//        if (head==null||head.next==null) return true;   //可以不用
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


    /**
     * 题目3(leetcode 160题): 编写一个程序,找出两单链表相交的节点
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //思路: 先到达末尾的p连接上宁外一条链表,因为移动的节点是一样的
        if (headA == null || headB == null) return null;
        ListNode p1 = headA, p2 = headB;
        while (p1 != p2) {
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }
        return p1;
    }

    public static ListNode fun01(ListNode headA, ListNode headB) { //如果非common节点val和next相同,会报错
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
     * 题目4(leetcode 19题):删除链表倒数第n个节点
     * 进阶: 一趟完成删除
     * 思路01: 先计算链表的长度length,要删除的是length-n+1节点,所以顺序扫描到要删除节点的前驱第length-n个节点
     * 思路02: 设置快慢指针,块指针先走k+1(前面有k+1个节点,到Null之后,fast到slow前面有k+1个,也就是倒数k+1个)
     * 然后慢指针和快指针一起走,快指针null,慢指针就到删除节点的前驱节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //方法二: 设置快慢指针,快指针先走k步(注意是达到第k+1个节点,前面就是k),最后快指针到null,前面有k个数,慢指针是倒数第k个节点
        if (head == null) return head;
        ListNode fast = head,slow = head;
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
        ListNode fast = head,slow = head;
        for(int i = 0;fast != null;i++){
            if (i >= n) slow = slow.next; //已经走了k步(0,1,2...k-1)
            fast = fast.next;
        }
        return slow;
    }

    public static void main(String[] args) {
//        ListNode head = new ListNode(3);
//        head.next = new ListNode(2);
//        head.next.next = new ListNode(1);
//        head.next.next.next = new ListNode(4);
//        System.out.println("题目1(leetcode 148题): 排序链表:");
////        sortList(head);
//        sortList01(head);
//        ListNode.printLinkList(head);
        System.out.println("题目3(leetcode 160题): 编写一个程序,找出两单链表相交的节点:");
        ListNode common01 = new ListNode(2);
        ListNode headA = new ListNode(1);
        headA.next = common01;
        ListNode headB = new ListNode(1);
        headB.next = common01;
        System.out.println(getIntersectionNode(headA, headB).val);
        System.out.println("HashSet求解");
        System.out.println(fun01(headA, headB).val);
    }

}
