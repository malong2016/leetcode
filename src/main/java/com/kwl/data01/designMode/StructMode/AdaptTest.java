package com.kwl.data01.designMode.StructMode;

/**
 * 23种设计模式- 第07种    --适配器
 * @author kuang.weilin
 * @date 2021/7/15 16:18
 */

/**
 * 类的适配器模式
 * 对象的适配器模式
 * 接口的适配器模式
 *
 * 使用场景:
 * 类的适配器模式：当希望将一个类转换成满足另一个新接口的类时，可以使用类的适配器模式，创建一个新类，继承原有的类，实现新的接口即可。
 *
 * 对象的适配器模式：当希望将一个对象转换成满足另一个新接口的对象时，可以创建一个Wrapper类，持有原类的一个实例，在Wrapper类的方法中，调用实例的方法就行。
 *
 * 接口的适配器模式：当不希望实现一个接口中所有的方法时，可以创建一个抽象类Wrapper，实现所有方法，我们写别的类的时候，继承抽象类即可。
 */
public class AdaptTest {

    public static void main(String[] args) {
        Targetable targetable = new Adapt();
        targetable.newMethod();
        targetable.sourceMethod();
    }
}


//1 类的适配器
class Source{
    public void sourceMethod(){
        System.out.println("我是sourceMethod....");
    }
}

interface Targetable{                 //要同时实现这二个方法,需要一个适配器才能实现
    public void sourceMethod();

    public void newMethod();
}

class Adapt extends Source implements Targetable{

    @Override
    public void newMethod() {
        System.out.println("我是newMethod");
    }
}


//2 对象适配器
class Wrapper implements Targetable{

    Source source;

    public Wrapper(Source source) {
        this.source = source;
    }

    @Override
    public void sourceMethod() {

    }

    @Override
    public void newMethod() {        //引用对象适配
        source.sourceMethod();
    }
}


//3 接口的适配器
//解释: 第三种适配器模式是接口的适配器模式，接口的适配器是这样的：有时我们写的一个接口中有多个抽象方法，当我们写该接口的实现类时，必
// 须实现该接口的所有方法，这明显有时比较浪费，因为并不是所有的方法都是我们需要的，有时只需要某一些，此
// 处为了解决这个问题，我们引入了接口的适配器模式，借助于一个抽象类，该抽象类实现了该接口，实现了所有的方法，而我们不和原始的接口打交道，
// 只和该抽象类取得联系，所以我们写一个类，继承该抽象类，重写我们需要的方法就行。
interface Sourceable {

    public void method1();
    public void method2();
}
//abstract class Wapper02 implements Sourceable05 {      //定义一个抽象类实现接口
//    @Override
//    public void method1() {
//
//    }
//
//    @Override
//    public void method2() {
//
//    }
//}
//
//class A extends Wapper02{        //只实现了部分接口就可以
//    @Override
//    public void method1() {
//        super.method1();
//        System.out.println("我是method1.....");
//    }
//}
//class B extends Wapper02{
//    @Override
//    public void method2() {
//        super.method2();
//        System.out.println("我是method1.....");
//    }
//}