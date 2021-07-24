package com.kwl.data01.designMode.CreativeMode;

/**
 * 23中设计模式- 第03种
 * @author kuang.weilin
 * @date 2021/7/15 15:23
 */

/**
 * 设计模式03: 抽象工厂 (把工厂抽取为接口)  --多个工厂类
 *
 * 思路: 工厂方法模式有一个问题就是，类的创建依赖工厂类，也就是说，如果想要拓展程序，必须对工厂类进行修改，这违背了闭包原则
 * 就用到抽象工厂模式，创建多个工厂类
 */
public class AbstractFactory {

    public static void main(String[] args) {
        Produce produce = new SendMailFactory();
        produce.produce().send();
    }
}

//这二个工厂类继承Produce
class SendMailFactory implements Produce{

    @Override
    public Sender produce() {
        return new EmailSend();
    }
}

class SendSmsFactory implements Produce{

    @Override
    public Sender produce() {
        return new SmsSend();
    }
}


interface Produce{              //利用抽象工厂模式，创建多个工厂类，这是是工厂类的接口
    Sender produce();       //这是返回的实例化对象Send()函数
}