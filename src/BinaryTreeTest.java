
import org.testng.annotations.Test;
import sun.nio.cs.ext.MacArabic;

import java.util.ArrayList;
import java.util.HashSet;

import static org.testng.Assert.assertEquals;


public class BinaryTreeTest {
    @Test
    public void add() {
        BinaryTree bt = new BinaryTree();
        BinaryTree actualBt = new BinaryTree();

        assertEquals(bt, actualBt);

        BinaryTree bst = new BinaryTree(10);
        BinaryTree actualBst = new BinaryTree(10);

        assertEquals(bst, actualBst);

        bst.add(8);
        Node head = actualBst.getHead();
        Node second = new Node(8, head);
        head.setLeft(second);

        assertEquals(bst, actualBst);

        bst.add(8);

        assertEquals(bst, actualBst);

        bst.add(15);
        Node third = new Node(15, head);
        head.setRight(third);

        assertEquals(bst, actualBst);
    }

    @Test
    public void delete() {
        BinaryTree bst = new BinaryTree();
        int[] a = {83, 95, 82};
        for (int i : a) bst.add(i);
        bst.delete(83);
        BinaryTree actualbst = new BinaryTree();
        int[] b = {95, 82};
        for (int i : b) actualbst.add(i);
        assertEquals(bst, actualbst);

        BinaryTree bst1 = new BinaryTree(8);
        int[] c = {5, 10, 2, 7, 6};
        for (int i : c) bst1.add(i);
        bst1.delete(5);
        BinaryTree actualbst1 = new BinaryTree(8);
        int[] d = {6, 10, 2, 7};
        for (int i : d) actualbst1.add(i);
        assertEquals(bst1, actualbst1);

        BinaryTree bst2 = new BinaryTree(8);
        int[] e = {5, 10, 2, 6, 7};
        for (int i : e) bst2.add(i);
        bst2.delete(5);
        BinaryTree actualbst2 = new BinaryTree(8);
        int[] f = {6, 10, 2, 7};
        for (int i : f) actualbst2.add(i);
        assertEquals(bst2, actualbst2);

        BinaryTree bst3 = new BinaryTree(8);
        bst3.delete(8);
        BinaryTree actualbst3 = new BinaryTree();
        assertEquals(bst3, actualbst3);

        BinaryTree bst4 = new BinaryTree();
        int[] a1 = {21, 8, 16, 49, 88, 36, 328, 396};
        for (int i : a1) bst4.add(i);
        bst4.delete(21);
        BinaryTree actualbst4 = new BinaryTree();
        int[] b1 = {36, 8, 16, 49, 88, 328, 396};
        for (int i : b1) actualbst4.add(i);
        assertEquals(bst4, actualbst4);

        BinaryTree binaryTree = new BinaryTree();
        ArrayList<Integer> list = new ArrayList<>();
        int value;
        int sum1, sum2;
        int[] array;
        array = new int[100];

        while( list.size() != 100) {
            value = (int) (Math.random() * 1000);
            if (!list.contains(value)) {
                list.add(value);
                array[list.size() - 1] = value;
            }
        }

//        int[] array = {464, 726, 926, 752, 810, 210, 924, 629, 1, 510, 616, 341, 933, 261, 38};
//        for (int i = 0; i < array.length; i++) {
//            list.add(array[i]);
//        }



        System.out.println(list);

        for (int i = 0; i < 100; i++) {
            binaryTree.add(array[i]);
        }
        sum1 = ((list.size() + 1) * (list.size()) / 2);
        sum2 = 0;
        for (int i = 0; i < list.size(); i++) {
            binaryTree.delete(array[i]);
            if (binaryTree.search(array[i]) == null) {
                sum2++;
            } else {
                break;
            }
            for (int j = i + 1; j < list.size() ; j++) {
                if (binaryTree.search(array[j]) != null) {
                    sum2++;
                } else {
                    break;
                }
            }
        }
        assertEquals(sum1, sum2);
    }

    @Test
    public void search() {
        BinaryTree bst = new BinaryTree(10);
        int[] a = {5, 14, 6, 7, 23, 12};
        for (int i : a) bst.add(i);
        Node node = bst.search(7);
        assertEquals(7, node.getValue());
        node = bst.search(23);
        assertEquals(23, node.getValue());
    }

    private void equalsNeighbours(int value, Integer[] neighbours) {
        BinaryTree bst = new BinaryTree(10);
        int[] a = {6, 14, 5, 7, 23, 12};
        for (int i : a) bst.add(i);

        Integer nodeValue = null;

        Node[] bstNodes = bst.getNeighbours(value);

        for (int i = 0; i < 3; i++) {
            if (bstNodes[i] == null) {
                nodeValue = null;
            }
            if (bstNodes[i] != null) {
                nodeValue = bstNodes[i].getValue();
            }
            assertEquals(nodeValue, neighbours[i]);
        }
    }

    @Test
    public void neighbours() {
        Integer[] actual = {5, 7, 10};
        equalsNeighbours(6, actual);
        Integer[] actual1 = {12, 23, 10};
        equalsNeighbours(14, actual1);
        Integer[] actual2 = {null, null, 6};
        equalsNeighbours(5, actual2);
        Integer[] actual3 = {6, 14, null};
        equalsNeighbours(10, actual3);
    }

}

