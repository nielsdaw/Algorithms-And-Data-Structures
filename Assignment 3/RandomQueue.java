import java.util.Iterator;
public class RandomQueue<Item> implements Iterable<Item> {
 
    private int size;                       // number of elements on q
    private Item[] q;                       // queue elements
    private int last;                   // index of next available slot

    // create an empty random queue
    public RandomQueue() {
            size = 0; last = 0;
            q = (Item[]) new Object[2];
    }
 
        // resize the underlying array
    private void resize(int max) {
        Item tempArray[] = (Item[]) new Object[max];
        for (int i = 0; i < size; i++) {tempArray[i] = q[i % q.length];}
        q = tempArray;
    }
 
    // is it empty?
    public boolean isEmpty() {return (size == 0);}

    // return the number of elements
    public int size() {return size;}

    // add an item
    public void enqueue(Item item) {
            if (last+1 >= q.length) {resize(q.length*2);}
            q[last] = item;
            last++;
            size++;
    }

    // return (but do not remove) a random item
    public Item sample() {return q[StdRandom.uniform(size)];}

    // remove and return a random item
    public Item dequeue() {
            int randomIndex = StdRandom.uniform(size);
            Item randomItem = q[randomIndex];
            q[randomIndex] = q[last-1];
            q[last-1] = null;
            size--;
            last--;
            if (size > 0 && size == q.length/4) {resize(q.length/2);}
            return randomItem;
    }

    // return an iterator over the items in random order
    public Iterator<Item> iterator() {return new RandomQueueIterator();}

    private class RandomQueueIterator implements Iterator<Item> {
            private int i = 0;
            private Item[] randomarray;
    public boolean hasNext()  {return i < size;}

    public Item next() {return randomarray[i++];}

    // Constructor
            public RandomQueueIterator() {
                    randomarray = (Item[]) new Object[size];
                    for (int i = 0; i < size; i++) {randomarray[i] = q[i];}

                    for (int i = 0; i < size; i++) {
                            int randomIndex = StdRandom.uniform(size);
                            Item item1 =  randomarray[i];
                            Item item2 = randomarray[randomIndex];
                            randomarray[i] = item2;
                            randomarray[randomIndex] = item1;
                    }
            }
    }
   
    public static void main(String args[]) {
            // Build a queue containing the Integers 1,2,...,6:
            RandomQueue<Integer> Q= new RandomQueue<Integer>();
                    for (int i = 1; i < 7; ++i) Q.enqueue(i); // autoboxing! cool!

            // Print 30 die rolls to standard output
            StdOut.print("Some die rolls: ");
           
            for (int i = 1; i < 30; ++i) StdOut.print(Q.sample() +" ");
           
            StdOut.println();
           
            // Let’s be more serious: do they really behave like die rolls?
            int[] rolls = new int [10000];
           
            for (int i = 0; i < 10000; ++i)
                    rolls[i] = Q.sample(); // autounboxing! Also cool!
           
            StdOut.printf("Mean (should be around 3.5): %5.4f\n", StdStats.mean(rolls));
            StdOut.printf("Standard deviation (should be around 1.7): %5.4f\n",
            StdStats.stddev(rolls));
           
            // Now remove 3 random values
            StdOut.printf("Removing %d %d %d\n", Q.dequeue(), Q.dequeue(), Q.dequeue());
           
            // Add 7,8,9
            for (int i = 7; i < 10; ++i) Q.enqueue(i);
           
            // Empty the queue in random order
            while (!Q.isEmpty()) StdOut.print(Q.dequeue() +" ");
                    StdOut.println();

            // Let’s look at the iterator. First, we make a queue of colours:
            RandomQueue<String> C = new RandomQueue<String>();
            C.enqueue("red"); C.enqueue("blue"); C.enqueue("green"); C.enqueue("yellow");
            Iterator I= C.iterator();
            Iterator J= C.iterator();
            StdOut.print("Two colours from first shuffle: "+I.next()+" "+I.next()+" ");
            StdOut.print("\nEntire second shuffle: ");
            while (J.hasNext()) StdOut.print(J.next()+" ");
            StdOut.print("\nRemaining two colours from first shuffle: "+I.next()+" "+I.next());
    }
}