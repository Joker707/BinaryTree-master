import java.util.Objects;

public class BinaryTree {

    private Node head;

    public BinaryTree(int value) {
        head = new Node(value, null);
    }

    public BinaryTree() {
    }

    public Node getHead() {
        return head;
    }

    public void add(int value) {
        if (head == null) {
            head = new Node(value, null);
            return;
        }

        if (search(value) != null) {
            return;
        }

        Node currentNode = head;

        while (currentNode != null) {

            if (currentNode.getValue() > value) {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(new Node(value, currentNode));
                    break;
                }
                currentNode = currentNode.getLeft();

            } else {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(new Node(value, currentNode));
                    break;
                }
                currentNode = currentNode.getRight();
            }
        }
    }


    public Node search(int value) {

        Node currentNode = head;
        while (currentNode != null && currentNode.getValue() != value) {
            if (currentNode.getValue() > value)
                currentNode = currentNode.getLeft();
            else
                currentNode = currentNode.getRight();

        }
        return currentNode;
    }

    public void delete(int value) {
        Node currentNode = search(value);
        Node parent = currentNode.getParent();
        if (currentNode.getLeft() == null && currentNode.getRight() == null) {

            if (parent == null) {
                head = null;
            } else {
                if (currentNode.getValue() < parent.getValue()) {

                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
            }
        } else if (currentNode.getLeft() != null && currentNode.getRight() == null) {

            if (parent != null) {
                currentNode.getLeft().setParent(parent);
                if (currentNode.getValue() < parent.getValue()) {
                    parent.setLeft(currentNode.getLeft());
                } else {
                    parent.setRight(currentNode.getLeft());
                }
            } else {
                head.setOtherNode(currentNode.getLeft());
                head.setParent(null);
                if (head.getRight() != null) {
                    head.getRight().setParent(head);
                }
                if (head.getLeft() != null) {
                    head.getLeft().setParent(head);
                }
            }
        } else if (currentNode.getLeft() == null && currentNode.getRight() != null) {

            if (parent != null) {
                currentNode.getRight().setParent(parent);
                if (currentNode.getValue() < parent.getValue()) {
                    parent.setLeft(currentNode.getRight());
                } else {
                    parent.setRight(currentNode.getRight());
                }
            } else {
                head.setOtherNode(currentNode.getRight());
                head.setParent(null);
                if (head.getRight() != null) {
                    head.getRight().setParent(head);
                }
                if (head.getLeft() != null) {
                    head.getLeft().setParent(head);
                }
            }
        } else {
            if (parent != null && currentNode.getValue() < parent.getValue()) {

                if (currentNode.getRight().getLeft() == null) {
                    currentNode.getLeft().setParent(currentNode.getRight());
                    currentNode.getRight().setParent(parent);
                    currentNode.getRight().setLeft(currentNode.getLeft());
                    parent.setLeft(currentNode.getRight());
                } else {
                    Node minNode = minNode(currentNode.getRight());
                    minNode.getParent().setLeft(minNode.getRight());
                    if (minNode.getRight() != null) {
                        minNode.getRight().setParent(minNode.getParent());
                    }
                    minNode.setParent(parent);
                    minNode.setLeft(currentNode.getLeft());
                    minNode.setRight(currentNode.getRight());
                    parent.setLeft(minNode);
                    currentNode.getRight().setParent(minNode);
                    currentNode.getLeft().setParent(minNode);
                }
            } else if (parent != null && currentNode.getValue() > parent.getValue()) {

                if (currentNode.getRight().getLeft() == null) {
                    currentNode.getLeft().setParent(currentNode.getRight());
                    currentNode.getRight().setParent(parent);
                    currentNode.getRight().setLeft(currentNode.getLeft());
                    parent.setRight(currentNode.getRight());
                } else {
                    Node minNode = minNode(currentNode.getRight());
                    minNode.getParent().setLeft(minNode.getRight());
                    if (minNode.getRight() != null) {
                        minNode.getRight().setParent(minNode.getParent());
                    }
                    minNode.setParent(parent);
                    minNode.setLeft(currentNode.getLeft());
                    minNode.setRight(currentNode.getRight());
                    parent.setRight(minNode);
                    currentNode.getRight().setParent(minNode);
                    currentNode.getLeft().setParent(minNode);
                }
            } else if (parent == null) {
                if (currentNode.getRight().getLeft() == null) {

//                    currentNode.getLeft().setParent(currentNode.getRight());
//                    currentNode.getRight().setLeft(currentNode.getLeft());
//                    currentNode.getRight().setParent(null);
//                    head.setOtherNode(currentNode.getRight());
//                    head.getLeft().setParent(head);
//                    head.getRight().setParent(head);
                    head.setValue(currentNode.getRight().getValue());
                    head.getLeft().setParent(head);
                    if (currentNode.getRight().getRight() != null) {
                        head.setRight(currentNode.getRight().getRight());
                        head.getRight().setParent(head);
                    } else {
                        head.setRight(currentNode.getRight().getRight());
                    }

                }
                else if (currentNode.getRight().getLeft() != null) {

                    Node minNode = minNode(currentNode.getRight());
                    minNode.getParent().setLeft(minNode.getRight());
                    if (minNode.getRight() != null) {
                        minNode.getRight().setParent(minNode.getParent());
                    }
                    head.setValue(minNode.getValue());
                    head.setParent(null);
                    head.setLeft(currentNode.getLeft());
                    head.setRight(currentNode.getRight());
                    head.getRight().setParent(head);
                }
            }
        }
    }


    public Node[] getNeighbours(int value) {
        Node currentNode = search(value);
        return new Node[]{currentNode.getLeft(),
                currentNode.getRight(), currentNode.getParent()};
    }

    public Node minNode(Node currentNode) {
        while (currentNode.getLeft() != null) {
            currentNode = currentNode.getLeft();
        }
        return currentNode;
    }

    public static void main(String[] args) {
        int array[];
        array = new int[10];
        for (int i = 0; i < array.length; i++)
            array[i] = (int) (Math.random() * 1000);
        for (int i : array)
            System.out.print(i + " ");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryTree that = (BinaryTree) o;
        return equalsNodes(head, that.head);
    }

    private boolean equalsNodes(Node a, Node b) {
        if (b != null && a != null) {
            return (b.getValue() == a.getValue()) &&
                    (equalsNodes(a.getLeft(), b.getLeft())) &&
                    (equalsNodes(a.getRight(), b.getRight()));
        } else if ((b == null && a != null) ||
                (a == null && b != null)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int hashCode() {
        return 7 * head.getValue();
    }
}
