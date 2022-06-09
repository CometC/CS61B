public class ArrayDeque<Item> {
    private Item[] items;
    int size;
    private int nextFirst;//addfirst 第一个
    private int nextLast;//addlast 第一个
    private static int RFACTOR = 2;
    private static int capacity = 8;
    private static int RMIN = 4;

    /** Creates an empty list. */
    public ArrayDeque() {
        items = (Item[]) new Object[capacity];
        size = 0;
        nextFirst = 0 ;
        nextLast = 1 ;
    }


    /**
     *  minusOne, plusOne, floorMod用来干什么？ >>>>> 用于重新定位nextFirst/nextLast
     **/
    public int minusOne(int index) {
        return Math.floorMod(index-1, items.length); //same as (index - 1 + items.length) % items.length
    }


    public int plusOne(int index) {
        return Math.floorMod(index+1, items.length);
    }

    public int plusOne(int index, int length) {
        return Math.floorMod(index+1, length); //这个length是干什么的？和上一个plusOne的区别？
    }

    /**
     *  invariants:
     *      设计resize()方法，将在增加ArrayDeaue实例内存的方法中调用
     *      内部判断内存满则调用expand()增加内存
     *        如果内存过小则调用reduce()减小内存
     **/
    private void resize() {
        if (size == items.length) {
            expand();
        }
        if (size < items.length / 4 && items.length > 8) {
            reduce();
        }
    }

    private void expand() {
        resizeHelper(items.length * 2);
    }

    private void reduce() {
        resizeHelper(items.length / 2);
    }

    private void resizeHelper(int capacity) {
        Item[] temp = items;
        int begin = plusOne(nextFirst);
        int end = minusOne(nextLast);
        items = (Item[]) new Object[capacity];
        nextFirst = 0;
        nextLast = 1;
        for (int i=begin; i != end; i = plusOne(i, temp.length)) {
            items[nextLast] = temp[i];
            nextLast = plusOne(nextLast);
        }
        items[nextLast] = temp[end];
        nextLast = plusOne(nextLast);
    }


    /**
     *  invariants:
     *      通过minusOne()方法确定nextFirst，(nextFirst-1)%items.length
     *      即nextFirst的下一个位置
     *      eg. (0 - 1) % 8 = 7
     * */
    public void addFirst(Item x) {
        resize();
        items[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    public void addLast(Item x) {
        resize();
        items[nextLast] = x ;
        nextFirst = plusOne(nextLast);
        size++ ;
    }

    public Item removeFirst() {
        Item i = items[plusOne(nextFirst)];
        nextFirst = plusOne(nextFirst);
        items[nextFirst] = null;
        size--;
        resize();

        return i;
    }

    public Item removeLast(Item x) {
        Item i = items[minusOne(nextLast)];
        nextLast = minusOne(nextFirst);
        items[nextLast] = null;
        size--;
        resize();

        return i;
    }

    /**
     *  Other functions
     * */

    public Item get(int i) {
        return items[Math.floorMod(plusOne(nextFirst) + i, items.length)];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }

    public void printDeque() {
        for (int index = plusOne(nextFirst); index != nextLast; index = plusOne(index)) {
            System.out.print(items[index]);
            System.out.print(" ");
        }
        System.out.println();
    }


/*
    public static void main(String[] args) {
        ArrayDeque<Integer> aq = new ArrayDeque<Integer>();
        for (int i = 0; i < 100; i++) {
            aq.addLast(i);
        }
        aq.printDeque();
        for (int i = 0; i < 98; i++) {
            aq.removeFirst();
        }
        aq.printDeque();
        System.out.println(aq.get(0));
    }
*/

}
