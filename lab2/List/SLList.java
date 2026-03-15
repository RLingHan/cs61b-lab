package List;

public class SLList {
    private static class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }
    private int size;
    private IntNode sentinel;
    public SLList(int x) {
        sentinel = new IntNode(0, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }
    public SLList() {
        sentinel = new IntNode(0, null);
        size = 0;
    }

    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size++;
    }
    public void addLast(int x) {
        IntNode n = sentinel;
        while (n.next != null) {
            n = n.next;
        }
        n.next = new IntNode(x,null);
        size++;
    }
    public void deleteFirst() {
        if (sentinel.next == null) return;
        sentinel.next = sentinel.next.next;
        size = size<1 ? 0 : size-1;
    }

    public int getFirst() {
        return sentinel.next.item;
    }
    public int getLast() {
        IntNode n = sentinel;
        while(n.next != null) {
            n = n.next;
        }
        return n.item;
    }

    public int size(){
        return size;
    }

    public String toString() {
        String s = "";
        IntNode n = sentinel;
        while (n.next != null) {
            s += n.next.item + " ";
            n = n.next;
        }
        return s;
    }


    public static void main(String[] args) {
        SLList L = new SLList(15);
        L.addFirst(10);
        L.addFirst(5);
        L.addLast(20);
        System.out.println(L);
        L.deleteFirst();
        System.out.println(L);

        SLList L2 = new SLList();
        L2.addFirst(10);
        System.out.println(L2);
        L2.deleteFirst();
        System.out.println(L2);
    }
}

