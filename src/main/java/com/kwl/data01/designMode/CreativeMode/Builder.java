package com.kwl.data01.designMode.CreativeMode;

import java.util.List;

/**
 * 23种设计模式- 第04种    --封装一个复杂对象的构建过程，并可以按步骤构造。
 *
 * @author kuang.weilin
 * @date 2021/7/15 15:47
 */

/**
 * 思路: 从这点看出，建造者模式将很多功能集成到一个类里，这个类可以创造出比较复杂的东西。
 * 所以与工程模式的区别就是：工厂模式关注的是创建单个产品，而建造者模式则关注创建符合对象，
 * 多个部分。因此，是选择工厂模式还是建造者模式，依实际情况而定。
 */
public class Builder {

    private List<Sender> list;

    public void produceEmailSends(int count) {    //创建出比较复杂的东西.
        for (int i = 0; i < count; i++) {          //创建十个EmailSend对象
            list.add(new EmailSend());
        }
    }

    public void produceSmsSends(int count) {
        for (int i = 0; i < count; i++) {
            list.add(new EmailSend());
        }
    }
}
