package com.kwl.data01.Java面试题.Java_208题.JavaCore.JavaBasic;


import java.io.*;

/**
 * javaCore_java基础_02(一共19道题目,本类中一共9道)
 */
public class JavaBasic_02 {

    /**
     * 题11: 抽象类必须要有抽象方法吗？
     * 答案:
     * 不需要,抽象方法一定要在抽象类中,但是抽象类可以没有抽象方法
     */

    /**
     * 题12: 普通类和抽象类有哪些区别？
     * 答案:
     *  1) 抽象类中可以包括抽象方法,普通类不可以
     *  2) 抽象类不能直接实例化,普通类可以
     */

    /**
     * 题13: 抽象类能使用 final 修饰吗？
     * 答案:
     *   不能,抽象类定义就是让其他类继承的,而final类不能被其他类继承,二者产生了矛盾
     */

    /**
     * 题14: 接口和抽象类的区别?
     * 答案:
     *   1) 抽象类子类是使用extends来继承,而必须使用implement来实现接口
     *   2) 抽象类可以用构造函数,接口不可以有
     *   3) main方法,抽象类可以用main方法,接口不可以
     *   4) 实现数量,类可以实现多个接口,但是只能继承一个抽象类
     *   5) 访问修饰符: 接口中的方法默认是使用public,但是抽象类中可以使用任意修饰符
     */

    /**
     * 题15: java 中 IO 流分为几种？
     * 答案:
     *  1) 按照功能来分: 输入流(input)、输出流(output)
     *  2) 按照类型来分: 字节流(inputStream/outputStream)和字符流 (Reader/Writer)
     *  3) 字节流和字符流的区别是:字节流按8位传输以字节为单位输入输出数据，字符流按16位(遇到中文),8位(遇到英文)传输以字符为单位输入输出数据。
     *
     *补充:
     *  1)utf-8 一个中文是三个字节,gdk 一个中文是二个字节
     *  2)字节流都是实现抽象类inputStream/outputStream
     *    字符流都是实现抽象类Reader/Writer
     */
    public static void fun01() throws IOException {

        //基本的写入数据
        File file = new File("C:\\Users\\view\\Desktop\\测试输入和输出.txt");
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        String str = "我是要写入的字符串";
//        fileOutputStream.write(str.getBytes());
        //读取数据
        FileInputStream fileInputStream = new FileInputStream(file);
//        byte[] buf = new byte[1024]; //数据中转站 临时缓冲区
        byte[] buf = new byte[fileInputStream.available()]; //数据中转站 临时缓冲区
        int length = 0;
        while ((length = fileInputStream.read(buf))!=-1){      //将读取到的数据赋值为byte[] buf，读第二次的时候length位-1
            System.out.println(new String(buf,0,length));
        }
    }

    /**
     * 题16: BIO、NIO、AIO 有什么区别？(要深入理解)
     * 答案:
     *   1) BIO：Block IO 同步阻塞式 IO，就是我们平常使用的传统 IO，它的特点是模式简单使用方便，并发处理能力低
     *   2) NIO：New IO 同步非阻塞 IO，是传统 IO 的升级，客户端和服务器端通过 Channel（通道）通讯，实现了多路复用。
     *   3) AIO：Asynchronous IO 是 NIO 的升级，也叫 NIO2，实现了异步非堵塞 IO ，异步 IO 的操作基于事件和回调机制。
     */

    /**
     * 17: Files的常用方法都有哪些？(要深入理解)
     * Files.exists()：检测文件路径是否存在。
     * Files.createFile()：创建文件。
     * Files.createDirectory()：创建文件夹。
     * Files.delete()：删除一个文件或目录。
     * Files.copy()：复制文件。
     * Files.move()：移动文件。
     * Files.size()：查看文件个数。
     * Files.read()：读取文件。
     * Files.write()：写入文件。
     *
     * //这是my自己complement的
     * Files.mkdir()  如果路径不存在,就创建路径,只能创建最后一层路径
     * Files.mkdirs() 如果路径不存在,就创建路径,可以创建多层路径
     */
    public static void fun02() throws IOException {
        //文件类
//        File file = new File("C:\\Users\\view\\Desktop\\测试输入和输出.txt");
    }



    /**
     * 主测试类
     * @param args
     */
    public static void main(String[] args) throws Exception {
        System.out.println("题14: java 中 IO 流分为几种？");
        fun01();
    }
}
