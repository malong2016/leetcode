package com.kwl.data01.myTest;

import java.io.*;

/**
 * // tode amd模式
 * @author kuang.weilin
 * @date 2021/7/28 15:11
 */
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static ListNode creatListNode(int len, String[] ss) {
        ListNode head = new ListNode(0);
        ListNode p = head;
        for (int i = 0; i < len; i++) {
            ListNode node = new ListNode(Integer.parseInt(ss[i]));
            p.next = node;
            p = node;
        }
        return head.next;
    }

    public static ListNode deleteNode(ListNode head, int index) {
        if (head == null) return head;
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode p = pre;
        while (p.next != null) {
            index--;
            if (index == 0) {
                p.next = p.next.next;
                break;
            }
            p = p.next;
        }
        return pre.next;
    }

    public static void main(String[] args) throws IOException {
        String[] s1 = br.readLine().trim().split(" ");
        int len = Integer.parseInt(s1[0]);
        int index = Integer.parseInt(s1[1]);
        String[] ss = br.readLine().trim().split(" ");
        ListNode head = creatListNode(len, ss);
        head = deleteNode(head, index);
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val).append(" ");
            head = head.next;
        }
        System.out.print(sb.toString());
    }
}

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
        this.next = next;
    }
}
