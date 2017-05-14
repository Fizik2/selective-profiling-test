package org.jetbrains.test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fizik on 14.05.17.
 */
public class AnotherApplication {

    private static List<TreeNode<String>> currentSubTree;
    private static List<TreeNode<String>> rootTrees = new ArrayList<>();


    public static void main(String... args) {
        readFileIntoTree();
    }

    private static void readFileIntoTree() {
        try {
            Files.lines(Paths.get("output.txt"), StandardCharsets.UTF_8).forEach(AnotherApplication::makeTrees);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void makeTrees(String line) {
        if (line.equals("")) {
            return;
        }

        int level = countLevel(line);
        TreeNode<String> node = new TreeNode<>(line.replace("-", ""));

        if (level == 1) {
            rootTrees.add(node);
            currentSubTree = new ArrayList<>();
        }


        if (currentSubTree.size() > level) {
            currentSubTree.set(level - 1, node);
        } else {
            currentSubTree.add(level - 1, node);
        }
        if (level > 1) {
            currentSubTree.get(level - 2).getChildren().add(node);
        }
    }

    private static int countLevel(String line) {
        int counter = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '-') {
                counter++;
            } else {
                break;
            }
        }
        return counter;
    }
}
