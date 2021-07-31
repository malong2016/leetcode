package com.kwl.data01.bean;

import com.kwl.data01.dataStructure.ListNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 默写ListNode的一些常见的方法
 * 假设每个节点都是val
 * @author kuang.weilin
 * @date 2021/6/22 0:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListNodeMy {



    private Integer val;

    private ListNodeMy next;

    public ListNodeMy(Integer val) {
        this.val = val;
    }

    //头插法建立链表
    public ListNodeMy buildListByHeadInsert(Integer[] arr){
        if (arr==null || arr.length == 0) return null;
        ListNodeMy head = new ListNodeMy(arr[0], null);      //设置head节点
        for (int i = 1; i < arr.length; i++) {
            ListNodeMy curNode = new ListNodeMy(arr[i]);
            curNode.next = head.next;
            head.next = curNode;
        }
        return head;
    }

    //尾插法建立链表
    public ListNodeMy buildListByTailInsert(Integer[] arr){
        if (arr == null || arr.length == 0) return null;
        ListNodeMy head = new ListNodeMy(arr[0], null);      //设置head节点
        ListNodeMy tailNode = head; //尾节点指向最后一个节点
        for (int i = 1; i < arr.length; i++) {
            ListNodeMy curNode = new ListNodeMy(arr[i]);
            tailNode.next = curNode;
            tailNode = tailNode.next;
        }
        tailNode.next = null;
        return head;
    }

    //正序打印链表
    public  void  printListByOrder(ListNodeMy head){
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    //逆序打印链表
    public void printListByReverse(ListNodeMy head){
        if (head != null) {
            printListByReverse(head.next);
            System.out.println(head.val);
        }
    }


    public static void main(String[] args) {
        ListNodeMy listNodeMy = new ListNodeMy();
        Integer[] arr = {5,4,7,2,1};
        //头插法测试
//        ListNodeMy buildListByHeadInsert = listNodeMy.buildListByHeadInsert(arr);
//        listNodeMy.printListByOrder(buildListByHeadInsert);

        //尾插法测试
        ListNodeMy buildListByTailInsert = listNodeMy.buildListByTailInsert(arr);
        listNodeMy.printListByOrder(buildListByTailInsert);  //5-4-7-2-1
        System.out.println("=============");
        listNodeMy.printListByReverse(buildListByTailInsert);          //逆序打印测试
    }
}
