package com.kwl.data01.Java面试题.Java_208题.JavaCore.JavaBasic;

import java.util.Arrays;

/**
 * javaCore_java基础_01(一共19道题目,本类中10道)
 */
public class JavaBasic_01 {

    /**
     * 题01: JDK 和 JRE 有什么区别？
     * 答案:
     * JDK: java development kit,包括JRE,也包括javac(把java源代码编译成为class字节码文件)
     * JRE: java runtime environment
     * 如果只要运行java文件,那么安装JRE就可以
     * 但是如果要编写和运行java文件,那么要安装JDK
     */

    /**
     * 题02: == 和 equals 的区别是什么？
     * 答案:
     * "==": 对应基本数据类型(int,double,boolean,char),是比较值是否相等
     *       对应引用类型,是比较引用(就是内存地址)是否相等
     * "equals": 一般情况下和"=="的引用类型一样,都是比较引用(内存地址)是否相等
     *          特殊情况下String和Integer重写了equals方法,是比较值相等
     *
     * 注意: 我们平常使用的Lombok @Data包括@EqualsAndHashCode,可以重写equals/hashcode(字段相等,就相等)
     *       或者手动重写equals,hashCode方法(字段相等,就相等)
     */

    /**
     * 题03: 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？
     * 答案:
     * 不对,hashCode()相等,就是两个键值对的哈希值相等,然而哈希值相等,并不能得出键值对相等
     * eg: 4+5 = 1+8 ,但是45组合和18组合是二个不同的String
     * 或者下面的例子
     */
    public static void fun01() {
        String str01 = "通话";
        String str02 = "重地";
        System.out.println(str01.hashCode());
        System.out.println(str02.hashCode());
        System.out.println(str01.equals(str02));
    }
    /**
     * 题04: final在java中有什么作用
     * 答案:
     *   1) final修饰的类,是终止类,不能被继承
     *   2) final修饰的方法,是终止方法,不能被重写
     *   3) final修饰的常量,常量必须被初始化,初始化之后值就不能改变
     * java变量的补充:
     *   1)成员变量,定义在类中定义的变量,有默认初始值,与类共存亡
     *   2)静态变量,定义在类中方法外的变量,有默认初始值,与类共存亡
     *   3)局部变量,定义在方法中的变量,没有默认初始值,必须赋值后才能使用,与方法共存亡
     */

    /**
     * 题05: java 中的 Math.round(-1.5) 等于多少？
     * 答案:
     * 四舍五入的原理是在参数上加0.5然后进行下取整。-1.5+0.5=-1
     * Math.round(-1.6) 结果是-1.6+0.5 = -1.1 向下取整是-2
     */

    /**
     * 题06: String 属于基础的数据类型吗？
     * 答案:
     * 不属于,java的8中基本数据类型是byte,short,int,long,float,double,boolean,char
     */

    /**
     * 题07: java 中操作字符串都有哪些类？它们之间有什么区别？
     * 答案:
     * String,StringBuffer,StringBuilder
     *  1)String声明是不可以改变的对象,每次操作都会生成新的String对象
     *  StringBuffer和StringBuilder是在原有对象进行操作
     *  如果要频繁改变字符串内容,不建议使用String,一般是使用StringBuffer,StringBuilder
     *  2)StringBuffer是线程安全,StringBuilder线程不安全
     *   StringBuilder性能高于StringBuffer
     *   所以: 如果是单线程一般是使用StringBuilder,如果是多线程那么一般使用StringBuffer
     */

    /**
     * 题08: String str="i"与 String str=new String(“i”)一样吗？
     * 答案:
     * 不一样,String str="i"会分配到常量池(栈中)中,String str=new String(“i”)是分配到堆(对象中)内存中
     *
     * 内存知识补充:
     *  栈内存: 存放局部变量(方法中的变量)
     *  堆内存: 存储的是数组和对象（其实数组就是对象），凡是new建立的都是在堆中，堆中存放的都是实体（对象）
     */

    /**
     * 题09: 如何将字符串反转？
     * 答案:
     * 使用StringBuffer和StringBuilder中的reverse()方法进行反转
     */
    public static void fun02() {
        System.out.println("StringBuffer反转字符串");
        StringBuffer stringBuffer = new StringBuffer("abcd");
        System.out.println(stringBuffer.reverse().toString());
        System.out.println("StringBuilder反转字符串");
        System.out.println(new StringBuilder("qwer").reverse());
    }

    /**
     * 题10: String 类的常用方法都有那些？
     * 答案:
     * ● indexOf（）∶返回指定字符的索引。
     * ● charAt（）∶ 返回指定索引处的字符。
     * ● replace（）∶ 字符串替换(所有)。
     * ● trim（）∶ 去除字符串两端空白。
     *  ● split（）∶ 分割字符串，返回一个分割后的字符串数组·
     * ● getBytes（）∶返回字符串的 byte 类型数组。（就是每个位置字符对应的ascill码值,最大127）
     * ● length（）∶ 返回字符串长度。
     *  ● toLowerCase（）∶将字符串转成小写字母。
     * ● toUpperCase（）∶将字符串转成大写字符。
     * ● substring（）∶截取字符串。  index从1开始,包括开始,不包括结尾,省略param2那么就是截取剩余所有
     * ● equals（）∶字符串比较。
     */
    public static void fun03() {
        String str = "I am a Student";
        System.out.println(str.indexOf("t"));  //发挥指定字符的索引
        System.out.println(str.charAt(9));   //返回指定索引处的字符
        System.out.println(str.replace("a", "A"));  //替换指定字符串(所有)
        System.out.println(str.trim());    //去除字符串两端空白
        System.out.println(Arrays.asList(str.split("")));  //分割字符串，返回一个分割后的字符串数组·
//        byte[] bytes = str.getBytes();
//        for (int i = 0; i < bytes.length; i++) {
//            byte aByte = bytes[i];
//            System.out.println("aByte = " + (char)aByte);
//        }
        System.out.println(str.toUpperCase());  //转换为大写
        System.out.println(str.substring(0));  //index
    }


    /**
     * 主测试类
     *
     * @param args
     */
    public static void main(String[] args) {
//        System.out.println("题03");
//        fun01();
//        System.out.println("题09:如何将字符串反转？");
//        fun02();
        System.out.println("题10: String 类的常用方法都有那些？");
        fun03();
    }

}
