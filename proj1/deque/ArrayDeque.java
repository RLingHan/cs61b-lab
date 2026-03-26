package deque;

import java.util.Iterator;

import static java.lang.System.arraycopy;

public class ArrayDeque<T> implements  Deque<T>, Iterable<T>{

    private T[] array;
    private int size;
    private int front;
    private int rear;

    private class ArrayDequeIterator implements Iterator<T>{
        private int index;
        public ArrayDequeIterator(){
            index = 0;
        }
        public boolean hasNext(){
            return index < size;
        }
        public T next(){
            return (T)get(index++);
        }
    }
    @Override
    public Iterator<T> iterator(){
        return new ArrayDequeIterator();
    }


    public ArrayDeque(T[] array, int size) {
        this.array = array;
        this.size = size;
    }
    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        front = 0;
        rear = 0;
    }

    private void resize(int newSize) {
//        T[] temp = array;
//        array = (T[]) new Object[newSize];
//        arraycopy(temp, 0, array, 0, size);
        T[] temp = (T[]) new Object[newSize];
        if(front > rear) {
            arraycopy(array, front, temp, 0, array.length-front);
            arraycopy(array, 0, temp, array.length-front, array.length-rear);
        }else {
            arraycopy(array, front, temp, 0, size);
        }
        front = 0;
        rear = size;
        array = temp;
    }

    @Override
    public void addFirst(T item) {
        if(size == array.length) resize(array.length*2);
//        T[] temp =(T[]) new Object[array.length];
//        temp[0] = item;
//        System.arraycopy(array, 0, temp, 1, size);
//        array = temp;
//        array[size] = item;
        front = (front - 1 + array.length) % array.length;
        array[front] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        if(size == array.length) resize(array.length*2);
//        array[size++] = item;
        array[rear] = item;
        rear = (rear + 1 ) % array.length;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int num = size;
        int i = front;
        while(num>0){
            System.out.print(array[i]+" ");
            i = (i+1) % array.length;
            num--;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if(isEmpty()) return null;
//        T value = array[0];
//        T[] temp = (T []) new Object[array.length];
//        size--;
//        arraycopy(array, 1, temp, 0, size);
//        array = temp;
//        return value;
        T item = array[front];
        array[front] = null;
        front = (front + 1 ) % array.length;
        size--;
        if(size < array.length/4 && size >=4) resize(array.length/4);
        return item;
    }

    @Override
    public T removeLast() {
        if(isEmpty()) return null;
//        T value = array[size-1];
//        array[size-1] = null;
//        size--;
//        if(size < array.length/4 && size >=4) resize(array.length/4);
//        return value;
        rear = (rear - 1 + array.length) % array.length;
        T item = array[rear];
        array[rear] = null;
        size--;
        if(size < array.length/4 && size >=4) resize(array.length/4);
        return item;
    }

    @Override
    public T get(int index) {
        if (isEmpty() || index < 0 || index >= size) return null;
        return array[(front + index) % array.length];
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) { return false; }
        if (this == o) { return true; } // optimization
        if (this.getClass() != o.getClass()) { return false; }
        ArrayDeque<T> other = (ArrayDeque<T>) o;
        if (this.size() != other.size()) { return false; }
        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }

}
