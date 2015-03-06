public class GiantQuickFind{


	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);      // number of vertices/sites
        int T = Integer.parseInt(args[1]);      // number of trials
        int [] edgesArray = new int[T];
        int [] nonIsolated  = new int [T];		// nonIsolated
        int [] giantComponent = new int [T];	// giantcomponent
        int [] connected = new int [T];			// connected
        
        long startTime = System.currentTimeMillis();
        // repeat the experiment T times
        for (int t = 0; t < T; t++) {
			int edges = 0;
	        boolean hasGiantComponent = false;
	        boolean hasNonIsolated = false;
	        boolean [] isVisited = new boolean [N];// boolean, if the objects are isolated
			int numberOfNonIsolated = 0;			// number of nonIsolated objects
	        QuickFindUF uf = new QuickFindUF(N);
	        while (uf.count() > 1) {
	            int i = StdRandom.uniform(N);
	            int j = StdRandom.uniform(N);
	            
	            // check i & j, if isolated
	            if(isVisited[i] != true){			
	            	isVisited[i] = true; 
	            	numberOfNonIsolated++;
	            } 
	            if(isVisited[j] != true){			
	            	isVisited[j] = true;
	            	numberOfNonIsolated++;
	            }

	            uf.union(i, j);
	            edges++;

	            //connected
	            if (uf.count() == 1) {		
	            	connected[t] = edges;
	            } 

	            // giantComponent
	            if (!hasGiantComponent && uf.maxComponentSize() >= (N/2)){ 
	            	giantComponent[t] = edges;
	            	hasGiantComponent = true;
	            }

	            // nonIsolated
	            if(!hasNonIsolated && numberOfNonIsolated == N){
	            	nonIsolated[t] = edges;
	            	hasNonIsolated = true;
	            }
	        }
        }

        long endTime = System.currentTimeMillis();


        // report statistics
        StdOut.println("Time: " + ((endTime - startTime) / 1000.0) + " sec");
        StdOut.printf("connected - mean		= %.2e %n", StdStats.mean(connected));
        StdOut.printf("connected - stddev		= %.2e %n", StdStats.stddev(connected));
    }


}
