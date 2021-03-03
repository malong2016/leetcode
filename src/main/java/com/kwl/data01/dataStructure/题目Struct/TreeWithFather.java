package com.kwl.data01.dataStructure.题目Struct;

import lombok.Data;

/**
 * @author kuang.weilin
 * @date 2021/2/18
 */
@Data
public class TreeWithFather {

    public Integer data;

    public TreeWithFather right;

    public TreeWithFather left;

    public TreeWithFather father;
}
