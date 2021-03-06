package com.kwl.data01.dataStructure.题目Struct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * swordOffer 面试题35 复杂链表的数据结构
 *
 * @author kuang.weilin
 * @date 2021/2/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplexLinkList {

    public int val;            //数据域

    public ComplexLinkList next;      //指向下一个节点

    public ComplexLinkList random;    //指向任意节点

    public ComplexLinkList(int val) {         //构造器
        this.val = val;
    }
}
