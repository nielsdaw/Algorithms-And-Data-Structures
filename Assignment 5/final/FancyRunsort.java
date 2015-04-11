public class FancyRunsort{   

    /**
    *   Convert array values to double, and invoke plotBars
    **/
    public static void plotArray(Comparable[] a){
        double[] tmpArray = new double[a.length];
            for (int i = 0; i < a.length; i++){
                String tmp1 = (String) a[i];
                tmp1 = tmp1.toUpperCase();
                char tmp2 = tmp1.charAt(0);   //  since uppercase has values within 0 - 90
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
}