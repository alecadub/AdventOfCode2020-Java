package days.Day07;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Node {
    private int value;
    private String color;
    private Node[] children;
    public ArrayList<Node> parents = new ArrayList<Node>();
    static Set<String> goodColors = new HashSet<String>();

    public Node(int value, String color, Node[] children) {
        this.value = value;
        this.color = color;
        this.children = children;
    }

    public Node[] getChildren() {
        return children;
    }

    public void setChildren(Node[] children) {
        this.children = children;
    }

    public String getColor() {
        return color;
    }

    static void traverseBoth(Node node, Node newNode) {
        if (newNode.children != null) {
            for (int i = 0; i < newNode.children.length; i++) {
                setChildrenAndParents(node, newNode);
                traverseBoth(node, newNode.children[i]);
            }
        } else {
            setChildrenAndParents(node, newNode);
        }
    }

    static void setChildrenAndParents(Node node, Node newNode) {
        if (node.getColor().equals(newNode.getColor())) {
            newNode.setChildren(node.getChildren());
            for (Node x : newNode.parents) {
                if (!node.parents.contains(x))
                    node.parents.add(x);
            }
        }
    }

    static void traverseSingle(Node node) {
        if (node.children != null) {
            for (int i = 0; i < node.children.length; i++) {
                if (node.getColor().equals("shiny gold")) {
                    Node.calculateParents(node);
                }
                traverseSingle(node.children[i]);
            }
        } else {
            if (node.getColor().equals("shiny gold")) {
                Node.calculateParents(node);
            }
        }
    }

    static void calculateParents(Node node) {
        if (!node.parents.isEmpty()) {
            for (int i = 0; i < node.parents.size(); i++) {
                goodColors.add(node.parents.get(i).getColor());
                calculateParents(node.parents.get(i));
            }
        } else {
            goodColors.add(node.getColor());
        }
    }
}
