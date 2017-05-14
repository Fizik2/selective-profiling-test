package org.jetbrains.test;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by fizik on 14.05.17.
 */
public class DummyApplication {
    private final List<String> args;
    private Random random = new Random(System.nanoTime());

    private TreeNode<String> root;
    private TreeNode<String> lastParent;

    public DummyApplication(List<String> args) {
        this.args = args;
    }

    private boolean nextBoolean() {
        return random.nextBoolean();
    }

    private boolean stop() {
        return random.nextDouble() < 0.05;
    }

    private String nextArg() {
        int idx = random.nextInt(args.size());
        return args.get(idx);
    }

    private void sleep() {
        try {
            Thread.sleep(random.nextInt(20));
        } catch (InterruptedException ignored) {

        }
    }

    private void checkRoot(TreeNode<String> node) {
        if (root == null) {
            root = node;
        } else {

            lastParent.getChildren().add(node);
        }
    }

    private boolean isNeedToPrint(TreeNode<String> node) {
        return node == root;
    }

    private StringBuilder getTree() {
        return getSubTree(root, 1, new StringBuilder());
    }

    private StringBuilder getSubTree(TreeNode<String> node, int level, StringBuilder result) {
        String offset = "";
        for (int i = 0; i < level; i++) {
            offset += "-";
        }
        result.append(offset + node.getData() + "\n");

        for (TreeNode<String> childrenNode : node.getChildren()) {
            getSubTree(childrenNode, level + 1, result);
        }
        return result;
    }

    private void abc(String s) {
        //your code here
        TreeNode<String> node = new TreeNode<>("abc(" + s + ")");
        checkRoot(node);

        sleep();
        if (stop()) {
            //do nothing
        } else if (nextBoolean()) {
            lastParent = node;
            def(nextArg());
        } else {
            lastParent = node;
            xyz(nextArg());
        }

        if (isNeedToPrint(node)) {
            doOutput();
        }
    }

    private void doOutput() {
        StringBuilder tree = getTree();
        System.out.println(tree);

        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("output.txt", true)))) {
            out.println(tree);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void def(String s) {
        //your code here
        TreeNode<String> node = new TreeNode<>("def(" + s + ")");
        checkRoot(node);

        sleep();
        if (stop()) {
            //do nothing
        } else if (nextBoolean()) {
            lastParent = node;
            abc(nextArg());
        } else {
            lastParent = node;
            xyz(nextArg());
        }
    }

    private void xyz(String s) {
        //your code here
        TreeNode<String> node = new TreeNode<>("xyz(" + s + ")");
        checkRoot(node);

        sleep();
        if (stop()) {
            //do nothing
        } else if (nextBoolean()) {
            lastParent = node;
            abc(nextArg());
        } else {
            lastParent = node;
            def(nextArg());
        }
    }

    public void start() {
        abc(nextArg());
    }


}
