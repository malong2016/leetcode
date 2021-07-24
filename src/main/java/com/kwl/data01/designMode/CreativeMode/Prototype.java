package com.kwl.data01.designMode.CreativeMode;

/**
 * 23种设计模式- 第05种   --通过复制现有的实例来创建新的实例
 *
 * @author kuang.weilin
 * @date 2021/7/15 15:56
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 定义: 原型模式虽然是创建型的模式，但是与工程模式没有关系，从名字即可看出，
 * 该模式的思想就是将一个对象作为原型，对其进行复制、克隆，产生一个和原对象类似的新对象。
 * 本小结会通过对象的复制，进行讲解。在Java中，复制对象是通过clone()实现的，先创建一个原型类：
 *
 * 输入理解: 深复制和浅复制
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prototype implements Cloneable {

    private String proId;

    public Prototype cloneA() throws CloneNotSupportedException {      //深辅助
        Prototype cloneObject = (Prototype) super.clone();
        return cloneObject;
    }


    public static void main(String[] args) throws CloneNotSupportedException {
    }




}
