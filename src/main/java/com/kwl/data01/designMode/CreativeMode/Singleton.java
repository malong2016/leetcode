package com.kwl.data01.designMode.CreativeMode;

/**
 * 23种设计模式- 第01种
 * @author kuang.weilin
 * @date 2021/7/15 13:07
 */

/**
 * 实现单例模式  -- 本题在swordOffer上没有
 * 定义: 它的定义就是确保某一个类只有一个实例，并且提供一个全局访问点。
 * 三个特点: 1、只有一个实例。 2、自我实例化。 3、提供全局访问点。
 * <p>
 * 思路01: 懒汉模式，把构造器设置为private,别人创建不来,然后设置静态方法去创建实例
 * 3.1 只适用于单线程环境的单例模式                        --不使用(线程不安全)
 * 3.2 可以在多线程环境中工作，但效率不高的单例模式          --不使用(效率低)
 * 3.3 双重锁定的单例模式                                 --使用
 * 3.4 使用静态内部类实现的单例模式                        --使用
 * 思路02: 饿汉模式   线程安全                            --使用
 *   private static final Singleton1 single = new Singleton1();
 *   //静态工厂方法
 *  public static Singleton1 getInstance() {
 *        return single;
 * }
 *
 * 比较01:
 * 饿汉就是类一旦加载，就把单例初始化完成，保证getInstance的时候，单例是已经存在的了，
 *
 * 懒汉比较懒，只有当调用getInstance的时候，才回去初始化这个单例。
 *
 * 线程安全:
 * 饿汉式天生就是线程安全的，可以直接用于多线程而不会出现问题，
 *
 * 懒汉式本身是非线程安全的，为了实现线程安全有几种写法，分别是上面的1、2、3，这三种实现在资源加载和性能方面有些区别。
 *
 * 资源加载和性能：
 * 饿汉式在类创建的同时就实例化一个静态对象出来，不管之后会不会使用这个单例，都会占据一定的内存，但是相应的，在第一次调用时速度也会更快，因为其资源已经初始化完成，
 * 懒汉式顾名思义，会延迟加载，在第一次使用该单例的时候才会实例化对象出来，第一次调用时要做初始化，如果要做的工作比较多，性能上会有些延迟，之后就和饿汉式一样了。
 */
public class Singleton {         //懒汉模式

    public static Singleton instance;

    private Singleton() {         //私有的空构造器
    }

    /**
     * 解法01:  只适用于单线程环境的单例模式
     * 唯一的全局访问点,而且只能创建一个实例对象
     * @return
     */
    public static Singleton getInstance() {
        if (instance == null){          //避免重复创建
            instance = new Singleton();
        }
        return instance;
    }

    /**
     * 解法02:  可以在多线程环境中工作，但效率不高的单例模式
     * 上面的情况，在多线程就不满足情况，如果二个线程第一次同时访问install == null,那么就会创建二个实例对象。
     * 加上同步关键字synchronized
     */
    public synchronized static Singleton getInstance01() {    //可以在多线程环境中工作，但效率不高的单例模式
        if (instance == null){          //避免重复创建
            instance = new Singleton();
        }
        return instance;
    }

    /**
     * 解法03: 双重锁定的单例模式
     * 为了在多线程环境下，不影响程序的性能，不让线程每次调用getInstance()方法时都加锁，而只是在实例未被创建时再加锁，
     * 在加锁处理里面还需要判断一次实例是否已存在，这种做法被称为“双重锁定”。
     */
    public  static Singleton getInstance03() {
        if (instance == null){                      //先判断实例是否存在，如果实例不存在就加锁
            synchronized (Singleton.class){
                instance = new Singleton();
            }
        }
        return instance;
    }

    /**
     * 解法04: 静态内部类模式
     * 既实现了线程安全，又避免了同步带来的性能影响，任何时候都不会发生阻塞。
     */
    public static class LazyMan{
        private final static Singleton INSTANCE = new Singleton();
    }

    public  static Singleton getInstance04() {
        return LazyMan.INSTANCE;
    }


    /**
     * 饿汉模式: 天生的线程安全
     * 这个饿汉模式，就初始化一完成，就会创建这个实例对象
     */
    private final static Singleton INSTACE = new Singleton();
    public  static Singleton getInstance05() {
        return INSTACE;
    }

    public static void main(String[] args) {
    }
}
