package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements  Deque<T>{

    private class TNode {
        T item;
        TNode next;
        TNode prev;
        public TNode(T item, TNode next, TNode prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
    private int size;
    private TNode sentinel;

    public LinkedListDeque(){
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        TNode newNode = new TNode(item, sentinel.next, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    @Override
    public void addLast(T item) {
        TNode newNode = new TNode(item, sentinel, sentinel.prev);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        if(isEmpty()) return;
        TNode current = sentinel;
        for(int i = 0; i < size(); i++){
            current = current.next;
            System.out.print(current.item + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if(isEmpty()) return null;
        TNode first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        size--;
        return first.item;
    }

    @Override
    public T removeLast() {
        if(isEmpty()) return null;
        TNode last = sentinel.prev;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;
        size--;
        return last.item;
    }

    @Override
    public T get(int index) {
        if (isEmpty() || index < 0 || index >= size) return null;
        TNode current = sentinel.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }


    public T getRecursive(int index) {
        if (isEmpty() || index < 0 || index >= size) return null;
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index,TNode current) {
        if(index == 0) return current.item;
        return getRecursiveHelper(index-1, current.next);
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

}
