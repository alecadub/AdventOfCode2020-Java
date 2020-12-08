package days.Day07;

import java.util.ArrayList;

public class NodeFactory {
    public Node getNode(int value, String color, Node[] children) {
        return new Node(value, color, children);
    }
}
