package org.jetbrains.test;

import java.util.LinkedList;
import java.util.List;

public class TreeNode<T> {
    private T data;
    private List<TreeNode<T>> children = new LinkedList<>();

    public List<TreeNode<T>> getChildren(){return children;}

    public T getData() {
        return data;
    }

    public TreeNode(T data) {
        this.data = data;
    }
}