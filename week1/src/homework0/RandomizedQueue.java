package homework0;


import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] arr;
    private int capacity;
    private int size;

    public RandomizedQueue() {
        arr = (Item[]) new Object[1];
        capacity = 1;
        size = 0;
    }

    private void resize(int newCapacity) {
        Item[] temp = (Item[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
        capacity = newCapacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("The item should not be null.");
        }
        arr[size] = item;
        size++;
        if (size == capacity) {
            resize(capacity * 2);
        }
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("The queue is empty.");
        }
        int index = StdRandom.uniformInt(0, size);
        Item temp = arr[index];
        arr[index] = arr[size - 1];
        size--;
        return temp;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("The queue is empty.");
        }
        return arr[StdRandom.uniformInt(0, size)];
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int count = 0;
            private int[] tmpArr = new int[size];

            {
                for (int i = 0; i < size; i++) {
                    tmpArr[i] = i;
                }
                for (int i = 0; i < size; i++) {
                    int index1 = StdRandom.uniformInt(0, size);
                    int index2 = StdRandom.uniformInt(0, size);
                    int tmp = tmpArr[index1];
                    tmpArr[index1] = tmpArr[index2];
                    tmpArr[index2] = tmp;
                }
            }

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public Item next() {
                if (count == size) {
                    throw new NoSuchElementException("There is no more item to return.");
                }
                return arr[tmpArr[count++]];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("The method is not safe.");
            }
        };
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        for (int i = 0; i < 50; i++) {
            rq.enqueue(StdRandom.uniformInt(200));
        }
        System.out.println(rq.isEmpty());
        for (int i = 0; i < 25; i++) {
            System.out.println(rq.dequeue());
        }
        for (int i = 0; i < 25; i++) {
            System.out.println(rq.sample());
        }
        Iterator<Integer> it = rq.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println(rq.size());
    }
}
