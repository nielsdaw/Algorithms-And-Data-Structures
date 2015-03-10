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
public class PlotBar {

    // This class should not be instantiated.
    private PlotBar() { }

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

    // updated sort method
    public static void sort(Comparable[] a){
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        int lo = 0;
        int mid = 0;
        int hi = 0;
        int runs = 0;
        while(!isSorted(a)){ // while not sorted
            int size = 0;
            for (int i = 1; i < N; i++){    // first run
                if(less(a[i], a[i-1])){     
                    if(size == 0){size = 1;} // the size is at minimum 1
                    mid = i -1;
                    lo = i - size;
                    runs += 2;  // at this point there is two runs

                    if(i+1 == N){i = i-1;}  // avoid out of bounds
                    for (int k = i+1; k < N; k++){  // second run // ---> k = i ; check a[k] a[k+1]
                        if(less(a[k], a[k-1])){
                            hi = k -1; 
                            merge(a, aux, lo, mid, hi);
                            size = 0;
                            i = k;
                            break; // break out
                        }
                    }
                }
                else{
                    size++; // increment size of first run
                }       
            }
            if(runs % 2 == 0){  // merge if the total number of runs is equal
                merge(a, aux, lo, mid, (N-1));
            }
            plotArray(a);
        }   
    }

  /***********************************************************************
    *  Helper sorting functions
    ***********************************************************************/
    
    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
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

    public static void plotArray(Comparable[] a){
        double[] tmpArray = new double[a.length];
        for (int i = 0; i < a.length; i++){
            String tmp1 = (String) a[i];
            char tmp2 = tmp1.charAt(0);
            StdOut.println(tmp2 + " " + (double) tmp2 / 100 );
            tmpArray[i] = (double) tmp2 / 100;
        } 
        plotBars(tmpArray);
    }

    /**
     * Plot bars from (0, a[i]) to (i, a[i]) to standard draw.
     */
    public static void plotBars(double[] a) {
        int N = a.length;
        StdDraw.setXscale(0, N-1);
        StdDraw.setPenRadius(0.3 / N);
        try{
            Thread.sleep(250);
        }catch (Exception e){
            StdOut.println("error");   
        }
        StdDraw.clear();
        for (int i = 0; i < N; i++) {
            StdDraw.line(i, 0, i, a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; bottom-up
     * mergesorts them; and prints them to standard output in ascending order. 
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings(); 
        PlotBar.sort(a);
        show(a);
    }
}
