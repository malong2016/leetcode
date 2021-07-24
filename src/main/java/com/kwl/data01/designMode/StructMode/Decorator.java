package com.kwl.data01.designMode.StructMode;

/**
 * 23种设计模式- 第08种  --装饰器
 * @author kuang.weilin
 * @date 2021/7/15 16:49
 */

/**
 * 装饰模式就是给一个对象增加一些新的功能，
 * 而且是动态的，要求装饰对象和被装饰对象实现同一个接口，装饰对象持有被装饰对象的实例，关系图如下：
 *
 *
 * Source类是被装饰类，Decorator类是一个装饰类，可以为Source类动态的添加一些功能
 */
public class Decorator implements Source01able {
    Source01able source01able;

    public Decorator(Source01able source01able) {
        this.source01able = source01able;
    }

    @Override
    public void method() {       //在source01的基础上添加了一些方法
        source01able.method();
        System.out.println("我是Decerator的里面的method....");
    }

    public static void main(String[] args) {
        Source01able source01able = new Source01();
        source01able.method();
        System.out.println("================");
        Decorator decorator = new Decorator(source01able);//通过装饰器，添加了新的method,这个是形成了一个新类
        decorator.method();
    }
}

interface Source01able {
    void method();
}
class Source01 implements Source01able {

    @Override
    public void method() {
        System.out.println("我是source01....的method...");
    }
}
