public class LinkedListDeque<T> {
    /* The first item (if it exists) is at sentinel.next. */
    private Node sentinel;
    private int size;

    private class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    /** Creates an empty LinkedListDeque. */
    public LinkedListDeque() {
        sentinel = new Node( null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    public LinkedListDeque(T x){
        sentinel = new Node(null, null, null);
        Node p = sentinel;
        p.prev = sentinel;
        p.next = sentinel;
        sentinel.next = new Node(sentinel, x, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }


    /** Adds x to the front of the list. */
    public void addFirst(T x) {
        sentinel.next = new Node(sentinel, x, sentinel.next);
        sentinel.next.next.prev = sentinel.next ; /**注意这里*/
        size = size + 1;
    }

    /** Adds x to the end of the list. */
    public void addLast(T x) {
        sentinel.prev = new Node(sentinel.prev, x, sentinel);
        sentinel.prev.prev.next = sentinel.prev ;
        size = size + 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        if (size == 0){
            return true;
        }
        else{
            return false;
        }
    }

    /** Returns the size of the list. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line. */
    public void printDeque() {
        Node p = sentinel;

        /* Advance p to the end of the list. */
        while (p.next != sentinel) {
            System.out.println(p.next + " ");
            p = p.next;
        }
        System.out.println("\n");
    }

    /** Removes x to the front of the list. */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T value = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel; ;
        size = size - 1;

        return value;
    }

    /** Removes x to the end of the list. */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T value = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel ;
        size = size - 1;

        return value;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null.  */
    public T get(int index) {
        if (index > size -1) {
            return null;
        }
        int i = 0;
        Node p = sentinel.next;
        while (i < index){
            p = p.next;
            i++;
        }
        return p.item;

    }

    public static void main(String[] args) {

    }
}