package com.kwl.data01;


import com.kwl.data01.dataStructure.Array;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.*;


@SpringBootTest
class Data01ApplicationTests {


    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charStr = str.toCharArray();
            Arrays.sort(charStr);
            String strSort = String.valueOf(charStr);     //拿到排好序的str
            if (!map.containsKey(strSort)) map.put(strSort, new LinkedList<String>());   //先添加/最优解
            map.get(strSort).add(str);        //后拿到加入
        }
        return new LinkedList<>(map.values());
    }


    @Test
    void test() {
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
}
