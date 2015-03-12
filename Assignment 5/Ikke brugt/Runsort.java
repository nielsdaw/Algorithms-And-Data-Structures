/*************************************************************************
 *  Compilation:  javac MergeBU.java
 *  Execution:    java MergeBU < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   http://algs4.cs.princeton.edu/22mergesort/tiny.txt
 *                http://algs4.cs.princeton.edu/22mergesort/words3.txt
 *   
 *  Sorts a sequence of strings from standard input using
 *  bottom-up mergesort.
 *   
 *  % more tiny.txt
 *  S O R T E X A M P L E
 *
 *  % java MergeBU < tiny.txt
 *  A E E L M O P R S T X                 [ one string per line ]
 *    
 *  % more words3.txt
 *  bed bug dad yes zoo ... all bad yet
 *  
 *  % java MergeBU < words3.txt
 *  all bad bed bug dad ... yes yet zoo    [ one string per line ]
 *
 *************************************************************************/

/**
 *  The <tt>MergeBU</tt> class provides static methods for sorting an
 *  array using bottom-up mergesort.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/21elementary">Section 2.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class Runsort {

    // This class should not be instantiated.
    private Runsort() { }

    // stably merge a[lo..mid] with a[mid+1..hi] using aux[lo..hi]
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; 
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];  // this copying is unneccessary
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }
    }

    public static void sort(Comparable[] a){
        Comparable[] aux = new Comparable[a.length];

        int left = 0;
        int right = a.length-1;
        int l = 0;
        int r = right;
        boolean sorted = false;

        // Round 1
        while (!sorted) {
            StdOut.println(sorted);
            sorted = true;
            left = 0;
            while (left < right) {
                l = left;
                StdOut.println("Start - L: " + l);
                //StdOut.println("First: " + (less(a[l], a[l+1]) || a[l] == a[l+1]));
                while (l < right && (less(a[l], a[l+1]) || a[l] == a[l+1])) {
                    l++;
                    StdOut.println("1 - L: " + l);
                }
                r = l+1;
                //StdOut.println("Second: " + (less(a[r], a[r+1]) || a[r] == a[r+1]));
                while (r == right-1 || (r < right && (less(a[r], a[r+1]) || a[r] == a[r+1]))) {
                    r++;
                    StdOut.println("2 - L: " + l);
                }
                if (r <= right) {
                    merge(a, aux, left, l, r);
                    StdOut.println("MERGED! " + left + a[left] + " " + l + a[l] + " " + r + a[r] + " right: " + right + " r: " + r);
                    show(a);
                    sorted = false;
                }
                left = r+1;
            }
        }
    }


  /***********************************************************************
    *  Helper sorting functions
    ***********************************************************************/
    
    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

   // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


   /***********************************************************************
    *  Check if array is sorted - useful for debugging
    ***********************************************************************/
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    /**
     * Reads in a sequence of strings from standard input; bottom-up
     * mergesorts them; and prints them to standard output in ascending order. 
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Runsort.sort(a);
        show(a);
    }
}