/**
 *  <p>
 *   The following is based on MergeButtomUp code from: <a href="http://algs4.cs.princeton.edu/21elementary">Section 2.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Jonas Tonny Nielsen
 *  @author Niels Dawartz
 */
public class Runsort {

    // This class should not be instantiated.
    private Runsort(){}

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

    // Our version of the sort method
    public static void sort(Comparable[] a){
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        int mid = 0;
        int hi = 0;
        while(!isSorted(a)){ // while not sorted
            int lo = 0; 
            int runs = 0;
            for (int i = 1; i < N; i++){    // first run
                if(less(a[i], a[i-1])){
                    runs+=2;  // one run 
                    mid = i -1; // mid is the previous
                    lo = mid - lo; // low is the lowest from mid
                    for (int k = i +1; k < N; k++){  // second run
                        if(less(a[k], a[k-1])){
                            hi = k-1; 
                            merge(a, aux, lo, mid, hi); // merge
                            i = k ;
                            lo = 0;
                            break; // break out
                        }
                        i = k;
                    }
                }
                else{lo++;} // increment lo
            }
            if(runs % 2 == 0){  // merge if the total number of runs is equal
                merge(a, aux, lo, mid, (N-1));
            }
            FancyRunsort.plotArray(a);
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

    /**
     * Reads in a sequence of strings from standard input; bottom-up
     * mergesorts them; and prints them to standard output in ascending order. 
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        FancyRunsort.plotArray(a);
        Runsort.sort(a);
        show(a);
    }
}
