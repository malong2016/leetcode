package com.kwl.data01.designMode.StructMode;

/**
 * 23种设计模式- 第10种    --代理模式
 * @author kuang.weilin
 * @date 2021/7/15 16:58
 */

/**
 * 其实每个模式名称就表明了该模式的作用，代理模式就是多一个代理类出来，替原对象进行一些操作，
 * 比如我们在租房子的时候回去找中介，为什么呢？因为你对该地区房屋的信息掌握的不够全面，希望找一个更熟悉的人去帮你做，
 * 此处的代理就是这个意思。再如我们有的时候打官司，我们需要请律师，因为律师在法律方面有专长，可以替我们进行操作，表达我们的想法
 */
public class Proxy implements Socure02Able{       //代理模式实现了socur02....也添加了新的方法

    Socure02 socure02;

    public Proxy(Socure02 socure02) {
        this.socure02 = socure02;
    }

    @Override
    public void method() {
        socure02.method();
        after();
    }

    void after(){
        System.out.println("我是after.....");
    }
}

interface Socure02Able{
    void method();
}

class Socure02 implements Socure02Able{

    @Override
    public void method() {
        System.out.println("我是socure02......");
    }
}
