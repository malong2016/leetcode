package com.kwl.data01.dataStructure;

/**
 * 并查集 - unionFind
 *
 * 处理连通性
 *
 * @author kuang.weilin
 * @date 2021/7/12 18:56
 */
public class UnionFind {

    public int[] root;              //记录每个节点的root节点
    public int[] rank;             //记录权重,如果没有权重，可以不使用这个值
    public int count;            // 记录连接的节点总共的数(如果没有可以省略)


    /**
     * 构造器，初始化节点
     *
     * @param count
     */
    public UnionFind(int count) {
        this.count = count;
        root = new int[count];
        rank = new int[count];
        for (int i = 0; i < count; i++) {
            root[i] = i;
            rank[i] = 0;                       //权重初始化为0
        }
    }

    /**
     * 判断这二个节点是否连接，如果root是相等那么就是相等的
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isConnection(int x, int y) {
        return findRoot(x) == findRoot(y);
    }

    /**
     * 二个节点进行union
     * 将二个节点union,先找到二个节点的root，如果root不同就
     * 把二个root节点进行union
     *
     * @param x
     * @param y
     */
    public void union(int x, int y) {
        int rootX = findRoot(x);
        int rootY = findRoot(y);
        if (rootX != rootY) {            //相等，就不需要union,不相等就union
            if (rank[rootX] > rank[rootY]) {            //谁的权重大，就把宁外一个设置本权重大节点的值
                root[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                root[rootX] = rootY;
            } else {                          //权重相等的情况
                root[rootY] = rootX;
                rank[rootX]++;                //权重+1,因为有二棵树在同一水平上
            }
            count--;           //二个节点合并之后，this.count--
        }
    }


    /**
     * 找到当前节点p的root节点
     * while循环法，和递归法
     *
     * @param x
     * @return
     */
    public int findRoot(int x) {         //while循环
        //如果p就是root[p]，那么root节点就是本身p
        //最后找到root节点，root节点的p = root[p]的
        while (x != root[x]) {
            //这个是路径压缩,会使得树的高度变短，性能优化.可以省略
            //本节点的root --> 等于父节点的父节点，这个可以减少一个递归
            root[x] = root[root[x]];
            x = root[x];
        }
        return x;
    }

    public int findRecursion(int x) {         //递归法
        if (x == root[x]) return x;
//        return findRecursion(root[p]);      //递归找root节点root
//        return root[p] = findRecursion(root[p]);    //直接找到父的节点,这个是b站
        return findRecursion(root[root[x]]);    //直接找root的root节点
    }
}
