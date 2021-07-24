package com.kwl.data01.designMode.CreativeMode;


/**
 * 23种设计模式- 第02/03种      简单工厂和工厂方法模式
 * @author kuang.weilin
 * @date 2021/7/15 15:02
 */
/**
 *  简单工厂模式: 是一个工厂类！！多个(一个)工厂(静态和非静态)方法
 * 1 简单工厂设计模式: 就是建立一个工厂类，对实现了同一接口的一些类进行实例的创建。首先看下关系图：
 * 2 多个工厂方法模式，是对普通工厂方法模式的改进，在普通工厂方法模式中，
 * 3 静态工厂模式: 将上面的多个工厂方法模式里的方法置为静态的，不需要创建实例，直接调用即可
 * 如果传递的字符串出错，则不能正确创建对象，而多个工厂方法模式是提供多个工厂方法，分别创建对象。关系图：
 * 总结: 总体来说，工厂模式适合：凡是出现了大量的产品需要创建，并且具有共同的接口时，
 * 可以通过工厂方法模式进行创建。在以上的三种模式中，第一种如果传入的字符串有误，
 * 不能正确创建对象，第三种相对于第二种，不需要实例化工厂类，所以，大多数情况下，我们会选用第三种——静态工厂方法模式。
 */
public class SimpleFactoryMode {
    /**
     * 1 简单工厂模式: 就是建立一个工厂类，对实现了同一接口的一些类进行实例的创建
     * 工厂模式定义了创建对象的接口,子类决定要实例化的类是哪一个,实例化推迟到子类
     *
     * @param type
     * @return
     */
    Sender produce(String type) {       //根据类型创建不同的实例
        if (type.equals("Email")) {
            return new EmailSend();
        } else if (type.equals("Sms")) {
            return new SmsSend();
        } else {
            System.out.println("请输入正确的类型");
            return null;
        }
    }

    /**
     * 2 多个工厂模式,提供多个工厂方法，分类创建不同的实例化对象
     *
     * @return
     */
    Sender produceEmail() {
        return new EmailSend();
    }

    Sender produceSms() {
        return new SmsSend();
    }

    /**
     * 3 静态工厂模式: 将上面的多个工厂方法模式里的方法置为静态的，不需要创建实例，直接调用即可
     */
    static Sender produceEmail01() {
        return new EmailSend();
    }

    static Sender produceSms01() {
        return new SmsSend();
    }
}

interface Sender {
    void send();
}

class EmailSend implements Sender {
    @Override
    public void send() {
        System.out.println("I am emailSend...");
    }
}

class SmsSend implements Sender {       //Sms是simple message service
    @Override
    public void send() {
        System.out.println("I ma SmsSend...");
    }
}
