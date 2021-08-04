package com.kwl.data01.dataStructure.题目Struct;

/**
 * leetcode 面试题208 (前缀数的数据结构)
 *
 * @author kuang.weilin
 * @date 2021/7/6 14:28
 */

/**
 * 前缀树描述:
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 *
 * 请你实现 Trie 类：
 *
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 */
public class Trie {


   private Trie[] children;        //前缀树的孩子
   private boolean isEnd;        //是否是最后的节点


    public Trie() {
        this.children = new Trie[26];     //对应存储26个字母(char转化为int)
        this.isEnd = false;                //默认是false
    }

    public void insert(String word) {         //是一个一个套下去
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (node.children[index] == null) node.children[index] = new Trie();   //如果是null，就创建一个新的节点
            node = node.children[index];      //指向新的节点
        }
        node.isEnd = true;       //插入结束,最后这个树的子节点isEnd是指向true
    }

    public boolean search(String word) {      //以...结束
        Trie node = searchPrefix(word);
        return  node != null && node.isEnd;     //搜索前缀不等于null,而且最后isEnd为true(是最后一个字母)
    }

    public boolean startsWith(String prefix) {     //包含
        return searchPrefix(prefix) != null;      //等于null说明没有
    }

    private Trie searchPrefix(String prefix) {      //随便搜索
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (node.children[index] == null) return null;    //不存在就返回null
            node = node.children[index];       //指向该孩子的,向下搜索
        }
        return node;
    }
}
