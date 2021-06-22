package com.kwl.data01.dataStructure;



/**
 * 字符串(String) 输入理解
 *
 * @author kuang.weilin
 * @date 2021/2/8
 */
public class StringTest {

    /**
     * toCharArray():将字符串转化为字符数组
     * StringBuilder(StringBuffer:低版本):大量拼接字符串时产生很多中间对象问题而提供的一个类(String也是Immutable类的典型实现，被声明为final class)
     *   &&下面的方法都是可以修改字符串本身,不需要产生中间对象,但是对于String,进行方法操作不会改变本身
     * stringBuilder.append():向字符串后面添加,return 插入之后的新字符串
     * stringBuilder.insert(offset, str):从字符串索引offset初开始插入str, return 插入之后的新字符串
     * stringBuilder.delete(1, 3):删除string索引[1,3)位置的字符串,后面向前移动, return 删除之后的新字符串
     * stringBuilder.deleteCharAt(index):删除指定位置的index
     * stringBuilder.replace(1,3,str):将[1,3)替换为str
     * stringBuilder.reverse():逆序字符串
     *
     * 和string同名方法差不多
     *     length():  return字符串长度
     *     charAt(index): return指定索引的char
     *     indexOf("我",fromindex): return该元素的第一个索引,fromindex从哪个索引开始找
     *     lastIndexOf('我'):  return该元素的最后一个索引
     */
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder("我是中国人");
        //        stringBuilder.deleteCharAt(0);
//        System.out.println(stringBuilder);
    }
}
