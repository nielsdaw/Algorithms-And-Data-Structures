public class GorillaHash {

	private final static int d = 10000;	// size of the vectors
	private final static int k = 20;	// value for k-grams
	private static Species[] allSpecies = new Species[12];	// array of species
	private static int count = 0;							// counter for array

	public static void main(String[] args) {
		while(StdIn.hasNextLine()){
			String nextSpecies = StdIn.readLine(); 			// readLine
			if (nextSpecies.substring(0,1).equals(">")) {	// new species has been found
				String[] tmpArr = nextSpecies.split(" ",2); // remove the ">"
				String species = tmpArr[0].substring(1);
				Species tmp = new Species(species, d);			// create new species instance with name
				String proteinSequence = "";				// init protein seq

				for (int i = 0; i < 3 ;i++ ) {				// asume always 3 lineseq
					proteinSequence += StdIn.readLine();	// add to protein sequence
				}
				tmp = setKGrams(tmp,proteinSequence);		// add protein species to proteinseq
				allSpecies[count] = tmp;									// add the new species to array
				count++;													// increment count
			}
		}
		StdOut.print("\t");
		for (int i = 0; i < allSpecies.length; i++ ) {		// create table names
			int[] tmp = allSpecies[i].getVector();		
			StdOut.print("\t" + allSpecies[i].getName());
		}
		StdOut.println();
		for (int i = 0; i < allSpecies.length; i++ ) {		// compare all species
			int[] tmp = allSpecies[i].getVector();		// species 1 to compare
			if(i == 11){
				StdOut.print(allSpecies[i].getName()+"\t");
			}
			else{
				StdOut.print(allSpecies[i].getName() + "\t\t");
			}
			for (int x = 0; x < allSpecies.length; x++ ) {
				int[] tmp2 = allSpecies[x].getVector();		// species 2 to compare
				StdOut.printf("%.3f \t",cos_angle(tmp, tmp2));
			}
			StdOut.println("\n");
		}
	}

	public static int hashValue(String s){
		return s.hashCode() % d;	// hash value within d
	}

	public static Species setKGrams(Species spieces, String s){
		for (int i = 0; i < s.length(); i+= k) {
			if(i+k > s.length()){
				spieces.addToVector(hashValue(s.substring(i, s.length()))); // add the last to vector
			}
			else{
				spieces.addToVector(hashValue(s.substring(i, i+k)));		// add to vector
			}
		}
		return spieces;
	}

	public static double cos_angle(int[] p, int[] q){
		return (dotProduct(p,q) / (length(p) * length(q)));
	} 

	public static double dotProduct(int[] p, int[] q){
		double sum = 0.0;
		for(int i= 0; i < p.length; i++){
			sum += p[i]*q[i];
		}
		return sum;
	}

	public static double length(int[] vector){
		double sum = 0.0;
		for(int i : vector){
			sum += Math.pow(i,2);
		}
		return Math.sqrt(sum);
	}
}