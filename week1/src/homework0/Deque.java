package homework0;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        private Item element;
        private Node next;
        private Node pre;

        public Node() {

        }

        public Node(Item element, Node next, Node pre) {
            this.element = element;
            this.next = next;
            this.pre = pre;
        }
    }

    private Node FirstNode;
    private Node EndNode;
    private int size;

    public Deque() {
        FirstNode = EndNode = null;
        size = 0;
    }

    public boolean isEmpty() {
        return FirstNode == null;
    }

    public int size() {
        return this.size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("The item should not be null.");
        }
        if (FirstNode == null) {
            FirstNode = EndNode = new Node(item, null, null);
        } else {
            Node oldNode = FirstNode;
            FirstNode = new Node(item, oldNode, null);
            oldNode.pre = FirstNode;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("The item should not be null.");
        }
        if (EndNode == null) {
            FirstNode = EndNode = new Node(item, null, null);
        } else {
            EndNode.next = new Node(item, null, EndNode);
            EndNode = EndNode.next;
        }
        size++;
    }

    public Item removeFirst() {
        if (FirstNode == null) {
            throw new NoSuchElementException("The deque is empty.");
        }
        Item item = FirstNode.element;
        FirstNode = FirstNode.next;
        FirstNode.pre = null;
        size--;
        return item;
    }

    public Item removeLast() {
        if (EndNode == null) {
            throw new NoSuchElementException("The deque is empty.");
        }
        Item item = FirstNode.element;
        FirstNode = FirstNode.next;
        FirstNode.pre = null;
        size--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node i = FirstNode;

            @Override
            public boolean hasNext() {
                return !(i == null);
            }

            @Override
            public Item next() {
                return i.element;
            }
        };
    }

    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<>();
        for (int i = 0; i < 50; i++) {
            dq.addFirst(i);
        }
        for (int i = 50; i < 100; i++) {
            dq.addLast(i);
        }
        for (int i = 0; i < 25; i++) {
            System.out.println(dq.removeFirst());
            System.out.println(dq.removeLast());
        }
        System.out.println(dq.size());
        System.out.println(dq.isEmpty());
    }
}
