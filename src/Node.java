import java.util.Objects;

public class Node {

    private int value;

    private Node left;

    private Node right;

    private Node parent;

    public Node(int value, Node parent) {
        this.value = value;
        this.parent = parent;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    private boolean equalsNodes(Node node) {
            return this.value == node.value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return value == node.value &&
                left.equalsNodes(node.left) &&
                right.equalsNodes(node.right) &&
                parent.equalsNodes(node.parent);
    }

    @Override
    public int hashCode() {
        int result = 7 * value;
        if (parent != null) {
            result += 7 * parent.value;
        }
        if (left != null) {
            result += 7 * left.value;
        }
        if (right != null) {
            result += 7 * right.value;
        }
        return result;
    }

    public void setOtherNode(Node node) {
        this.left = node.left;
        this.right = node.right;
        this.value = node.value;
    }
}
