package Java面试题.Java_208题.JavaCore.Container;

import java.util.*;

/**
 * javaCore_容器_01(一共17道题目,本类一共17题)
 */
public class Container_01 {

    /**
     * 题18  java 容器都有哪些？
     * 答案:
     * Collection(接口):List(ArrayList, LinkedList,vector)、Queue(LinkedList)、Set(HashSet, TreeSet)
     * Map(接口): HashMap、TreeMap
     */

    /**
     * 题19 Collection 和 Collections 有什么区别？
     * 答案:
     *   1) java.util.Collection是一个接口集合。提供了为集合对象最通用的方法.Collection在java类库
     *   中有很多具体的实现.意义是为各种具体的集合提供最大化统一操作方式,其直接继承接口有List和Set
     *   2)java.util.Collections: 则是集合类的一个工具类/帮助类，其中提供了一系列静态方法，用于对集合中元素进行排序、搜索以及线程安全等各种操作。
     */


    /**
     * 题20 List、Set、Map 之间的区别是什么？具体看表,下面列举一些
     * 答案:
     *   1) List是可以重复的,set和Map是不可以重复的
     *   2) List是有序的的,Set是无序的
     *   3) List,set是集合,Map是键值对
     */

    /**
     * 题21  HashMap 和 Hashtable 有什么区别？
     * 答案:
     * 1) hashMap去掉了HashTable 的contains方法，但是加上了containsValue（）和containsKey（）方法。(好像hashTable方法也有)
     * 2) hashTable同步的，而HashMap是非同步的，效率上比hashTable要高。
     * 3) hashMap允许空键值，而hashTable不允许。(注意: hashTable中key和value都是不允许null的)
     */
    public static void fun01() {
        HashMap<String, String> map = new HashMap<>();
        map.put("01", "张三");
//        System.out.println(map.put("02", "李四"));  //返回key对应的value值
//        map.put(null, null);
        System.out.println(map.containsKey("01"));
        System.out.println(map.containsValue("张三"));
        System.out.println(map.keySet());
        System.out.println(map.values());
    }

    /**
     * 题22: 如何决定使用 HashMap 还是 TreeMap？(一般插入,删除,查找使用hashMap,如果便利key有序集合,就使用TreeMap)
     * 答案:
     * 对于在Map中插入、删除和定位元素这类操作，HashMap是最好的选择。然而，假如你需要对一个有序的key集合进行遍历，TreeMap是更好的选择。基于你的
     * collection的大小，也许向HashMap中添加元素会更快，将map换为TreeMap进行有序key的遍历。
     */
    public static void fun02() {
        Map<String, String> map = new HashMap<>();
        map.put("张三", "北京");
        map.put("张三01", "北京01");
        map.put("张三02", "北京02");
        System.out.println(map.get("122"));     //找不到就等于null
    }
    /**
     * 题23: 说一下 HashMap 的实现原理？ 哈希表和Map接口实现
     * 答案:
     * HashMap概述： HashMap是基于哈希表的Map接口的非同步实现。此实现提供所有可选的映射操作，并允许使用null值和null键。此类不保证映射的顺序，特别
     * 是它不保证该顺序恒久不变。
     * HashMap的数据结构： 在java编程语言中，最基本的结构就是两种，一个是数组，另外一个是模拟指针（引用），所有的数据结构都可以用这两个基本结构来构造
     * 的，HashMap也不例外。HashMap实际上是一个“链表散列”的数据结构，即数组和链表的结合体。
     * 当我们往Hashmap中put元素时,首先根据key的hashcode重新计算hash值,根绝hash值得到这个元素在数组中的位置(下标),如果该数组在该位置上已经存放了其他
     * 元素,那么在这个位置上的元素将以链表的形式存放,新加入的放在链头,最先加入的放入链尾.如果数组中该位置没有元素,就直接将该元素放到数组的该位置上。
     * 需要注意Jdk 1.8中对HashMap的实现做了优化,当链表中的节点数据超过八个之后,该链表会转为红黑树来提高查询效率,从原来的O(n)到O(logn)
     */
    /**
     * 题24: 说一下 HashSet 的实现原理？
     * 答案:
     * HashSet底层由HashMap实现
     * HashSet的值存放于HashMap的key上
     * HashMap的value统一为PRESENT
     */
    /**
     * 题25: ArrayList 和 LinkedList 的区别是什么？
     * 答案:
     *   1) ArrayList底层数据结构是数组,支持随机访问
     *   2)LinkedList底层数据结构是双向循环列表,不支持随机访问
     *   3)访问一个元素 ArrayList时间复杂度是o(1),LinkedList时间复杂度是o(n)
     */

    /**
     * 题26: 如何实现数组和 List 之间的转换？
     * 答案:
     * 1) List转换成为数组：调用ArrayList的toArray方法。
     * 2) 数组转换成为List：调用Arrays的asList方法。
     */
    public static void fun03() {
        List<String> list = new ArrayList<>();
        String[] strings = new String[]{
                "张三", "李四", "王五"
        };
        //arr转化为list
        System.out.println(Arrays.asList(strings));
        //list转化为arr
        System.out.println(list.toArray());
    }
    /**
     * 题27: ArrayList 和 Vector 的区别是什么？  List下面(ArrayList,vector,LinkedList)
     * 答案:
     * 1) Vector是同步的，而ArrayList不是。然而，如果你寻求在迭代的时候对列表进行改变，你应该使用CopyOnWriteArrayList。
     * 2) ArrayList比Vector快，它因为有同步，不会过载。
     * 3) ArrayList更加通用，因为我们可以使用Collections工具类轻易地获取同步列表和只读列表. ???
     */
    public static void fun04() {
        List<String> list = new Vector<>();
        list.add("张娜");
        list.add("张娜01");
        System.out.println(list);
    }

    /**
     * 题28: Array 和 ArrayList 有何区别？
     * 答案:
     * 1) Array可以容纳基本类型和对象，而ArrayList只能容纳对象。
     * 2) Array是指定大小后不可变的，而ArrayList大小是可变的。
     * 3) Array没有提供ArrayList那么多功能，比如addAll、removeAll和iterator等。
     */
    public static void fun05() {

    }


    /**
     * 题29: 在 Queue 中 poll()和 remove()有什么区别？
     * 答案:
     * poll() 和 remove() 都是从队列中取出一个元素，但是 poll() 在获取元素失败的时候会返回空，但是 remove() 失败的时候会抛出异常。
     */

    /**
     * 题30: 哪些集合类是线程安全的？
     * 答案:
     * 1) vector：就比arraylist多了个同步化机制（线程安全），因为效率较低，现在已经不太建议使用。在web应用中，特别是前台页面，往往效率（页面响应速度）
     * 是优先考虑的。
     * 2) statck：堆栈类，先进后出
     * 3) hashtable：就比hashmap多了个线程安全。
     * 4) enumeration：枚举，相当于迭代器。
     */

    /**
     * 题31: 迭代器 Iterator 是什么？
     * 答案:
     * 迭代器是一种设计模式，它是一个对象，它可以遍历并选择序列中的对象，而开发人员不需要了解该序列的底层结构。迭代器通常被称为“轻量级”对象，因为创建
     * 它的代价小。
     */
    public static void fun06() {
        //测试一下容器里面的迭代器
        List<String> list = new ArrayList(){{
            add("张三");
            add("李四");
            add("王二");
        }};
//        System.out.println(list);
        Iterator<String> iterator = list.iterator();  //拿到list的迭代器
        while (iterator.hasNext()) {  //如果有下一个就继续便利
            System.out.println(iterator.next());
        }
    }
    /**
     * 题32: Iterator 怎么使用？有什么特点？
     * 答案:
     * Java中的Iterator功能比较简单，并且只能单向移动：
     * 1) 使用方法iterator()要求容器返回一个Iterator。第一次调用Iterator的next()方法时，它返回序列的第一个元素。注意：iterator()方法是java.lang.Iterable接口,
     * 被Collection继承。
     * 2)  使用next()获得序列中的下一个元素。
     * 3) 使用hasNext()检查序列中是否还有元素。
     * 4) 使用remove()将迭代器新返回的元素删除。
     * Iterator是Java迭代器最简单的实现，为List设计的ListIterator具有更多的功能，它可以从两个方向遍历List，也可以从List中插入和删除元素
     */

    /**
     * 题33: Iterator 和 ListIterator 有什么区别？
     * 答案:
     * 1) Iterator可用来遍历Set和List集合，但是ListIterator只能用来遍历List。
     * 2) Iterator对集合只能是前向遍历，ListIterator既可以前向也可以后向。
     * 3) ListIterator实现了Iterator接口，并包含其他的功能，比如：增加元素，替换元素，获取前一个和后一个元素的索引，等等
     */
    public static void fun07() {
        //测试一下容器里面的迭代器
        List<String> list = new ArrayList(){{
            add("张三");
            add("李四");
            add("王二");
        }};
        ListIterator<String> stringListIterator = list.listIterator(); //深入理解ListIterator
//        stringListIterator.hasPrevious()     //判断是否有前一个
    }







    public static void main(String[] args) {
//        System.out.println("题21  HashMap 和 Hashtable 有什么区别？");
//        fun01();
//        System.out.println("题21  如何决定使用 HashMap 还是 TreeMap？");
//        fun02();
//        System.out.println(" 题24: 如何实现数组和 List 之间的转换？");
//        fun03();
//        System.out.println("ArrayList 和 Vector 的区别是什么？");
//        fun04();
//        System.out.println("题26: Array 和 ArrayList 有何区别？");
//        System.out.println("题31: 迭代器 Iterator 是什么？");
//        fun06();
        System.out.println("题33: Iterator 和 ListIterator 有什么区别？");
    }
}
