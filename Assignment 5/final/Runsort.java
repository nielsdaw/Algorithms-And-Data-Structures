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
                            merge(a, aux, lo, mid, hi); // merge
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
        Runsort.sort(a);
        show(a);
    }
}
