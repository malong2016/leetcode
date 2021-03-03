package com.kwl.data01.dataStructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 输入理解链表 创建链表(头插法,尾插法)、遍历链表
 * 注意:本类中头节点都是带data域!!!
 *
 * @author kuang.weilin
 * @date 2021/2/11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListNode {

    public int val;            //数据域

    public ListNode next;      //指向下一个节点


    public ListNode(int val) {
        this.val = val;
    }

    /**
     * 1-1 创建链表之[头插法],return 头节点
     *
     * @param arr 传入待插入的整型数组
     */
    public static ListNode createLinkListByHead(int[] arr) {
        if (arr == null || arr.length == 0) return null;     //传入数组为null就返回
        ListNode head = new ListNode(arr[0], null);    //将插入arr的第一个元素作为head,head.next指向null
        for (int i = 1; i < arr.length; i++) {        //遍历数组,逐步进行头插
            ListNode linkNode = new ListNode(arr[i]);
            linkNode.next = head.next;
            head.next = linkNode;
        }
        return head;           //返回头节点
    }

    /**
     * 1 2)创建链表之[尾插法],return 头节点
     */
    public static ListNode createLinkListByTail(int[] arr) {
        if (arr == null || arr.length == 0) return null;     //传入数组为null或者{}就返回null
        ListNode head = new ListNode(arr[0], null);    //将插入arr的第一个元素作为head,head.next指向null
        ListNode pTail = head;                       //定义一个尾指针,始终指向链表的最后一个Node
        for (int i = 1; i < arr.length; i++) {
            ListNode linkNode = new ListNode(arr[i]);
            pTail.next = linkNode;               //尾节点指向新插入的节点
            pTail = pTail.next;                   //尾指针指向新的尾节点(新插入的元素)
        }
        pTail.next = null;               //插入完毕之后,尾节点指向null(可以省略,初始化节点时候,p.next=null)
        return head;           //返回头节点
    }

    /**
     * 2-1 正序遍历打印链表
     */
    public static void printLinkList(ListNode headNode) {
        ListNode p = headNode;          //p指向头节点
        System.out.print("遍历打印的链表: ");
        while (p != null) {
            System.out.print(p.val + " ");         //不换行
            p = p.next;
        }
        System.out.println(); //最后换行
    }

    /**
     * 2-2 逆序打印链表(递归或者利用栈都可以)
     * 思路:不断递归,头部节点后打印
     */
    public static void printListByReverse(ListNode p) {
        if (p != null) {              //判断一次,if是判断一次就好,while和for是循环需要多次
            printListByReverse(p.next);
            System.out.print(p.val + " ");
        }
        System.out.println(); //最后换行
    }

    public static int countNodeNum(ListNode headNode) {
        int nodeNum = 0;
        while (headNode != null) {         //本方法headNode只改变方向,没有改变地址里面东西,所以可以直接使用head
            nodeNum++;
            headNode = headNode.next;
        }
        return nodeNum;
    }

    /**
     * 2-3 快(fast)慢(slow)指针寻找链表中间的节点
     * 思路: 设置fast和slow指针,扫描链表,fast指针一次走二步,slow指针一次走一步
     * 当fast指针到达尾节点后者尾节点的next,slow指针到达链表中间节点
     */
    public static ListNode getMidNodeByFastAndSlow(ListNode head) {
        ListNode fast = head, slow = head;    //如果有二个中点,slow指向后一个中点!!!如果是一个中点,指向中点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;

//        if (head==null) return null;
//        ListNode slow = head,fast = head.next;     //如果有二个中点,slow指向前一个中点!!!!如果是一个中点,指向中点
//        while (fast != null && fast.next != null){
//            fast = fast.next.next;
//            slow = slow.next;
//        }
//        return slow;
    }


    public static void main(String[] args) {
//        System.out.println("头插法创建链表");
//        LinkListNode headNode = createLinkListByHead(new int[]{1, 2, 3, 7, 8});

//        System.out.println("尾插法创建链表");
//        ListNode headNode = createLinkListByTail(new int[]{1, 2, 3, 7, 8});

//        System.out.println("正序打印");
//        printLinkList(headNode);

//        System.out.println("逆序打印");
//        printListByReverse(headNode);

//        System.out.println("计算链表的节点数:");
//        System.out.println(countNodeNum(headNode));

        System.out.println("2-3 快(fast)慢(slow)指针寻找链表中间的节点:");
        ListNode head = createLinkListByTail(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println(getMidNodeByFastAndSlow(head).val);
    }


}
