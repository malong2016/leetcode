package com.kwl.data01.dataStructure.题目Struct;

/**
 * swordOffer 面试题35 复杂链表的数据结构
 *
 * @author kuang.weilin
 * @date 2021/2/19
 */
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class ComplexLinkList {

    public int data;            //数据域

    public ComplexLinkList next;      //指向下一个节点

    public ComplexLinkList random;    //指向任意节点

    public ComplexLinkList(int data) {         //构造器
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public ComplexLinkList getNext() {
        return next;
    }

    public void setNext(ComplexLinkList next) {
        this.next = next;
    }

    public ComplexLinkList getRandom() {
        return random;
    }

    public void setRandom(ComplexLinkList random) {
        this.random = random;
    }
}
