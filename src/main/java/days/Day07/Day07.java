package days.Day07;

import days.Day;
import days.ExecuteChallengeSpecifics;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day07 extends Day {
    @Override
    public void executeChallenge(ExecuteChallengeSpecifics function) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/day07.txt"), StandardCharsets.UTF_8);
            ArrayList<Node> roots = new ArrayList<Node>();
            NodeFactory nodeFactory = new NodeFactory();

            for (String line : lines) {
                String nodeColor = line.substring(0, line.indexOf("contain"));
                String nodeChildrenString = line.substring(line.indexOf("contain") + 8, line.indexOf("."));
                String[] nodeChildrenStringArray = nodeChildrenString.split("\\s*,\\s*");
                Node[] nodeChildren = new Node[nodeChildrenStringArray.length];
                Node newNode = nodeFactory.getNode(1, nodeColor.substring(0, nodeColor.indexOf("bag")).trim(), null);

                if (!nodeChildrenStringArray[0].contains("no other bags")) {
                    for (int i = 0; i < nodeChildrenStringArray.length; i++) {
                        int nodeChildValue = Integer.parseInt(nodeChildrenStringArray[i].substring(0, 1));
                        String nodeChildColor = nodeChildrenStringArray[i].substring(2);
                        nodeChildren[i] = nodeFactory.getNode(nodeChildValue, nodeChildColor.substring(0, nodeChildColor.indexOf("bag")).trim(), null);
                        nodeChildren[i].parents.add(newNode);
                    }
                    newNode.setChildren(nodeChildren);
                }

                roots.add(newNode);
            }

            for (int i = 0; i < roots.size(); i++) {
                for (int j = 0; j < roots.size(); j++) {
                    if (i != j) {
                        Node.traverseBoth(roots.get(i), roots.get(j));
                    }
                }
            }

            for (Node node : roots) {
                Node.traverseSingle(node);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void challenge01() {
        this.executeChallenge(() -> {
        });
        setFirstResult(String.valueOf(Node.goodColors.size()));
    }

    @Override
    public void challenge02() {
    }
}
