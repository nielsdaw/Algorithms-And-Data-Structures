import java.util.Arrays;

public class WordLadder{

	// public static boolean containLetters(String s1, String s2){
	// 	String tmp1 = sortString(s1.substring(1, s1.length()));
	// 	String tmp2 = sortString(s2);

	// 	for (int x = 0; x < 5 ; x++ ) {
	// 		if(x == 0){
	// 			String tmp3 = tmp2.substring(x, tmp2.length()-1);
	// 			if(tmp1.equals(tmp3)){return true;}
	// 		}
	// 		else if(x == tmp2.length()-1){
	// 			String tmp3 = tmp2.substring(tmp2.length() - x, tmp2.length());	
	// 			if(tmp1.equals(tmp3)){return true;}
	// 		}
	// 		else{
	// 			String tmp3 = tmp2.substring(0, x) + tmp2.substring(x+1);
	// 			if(tmp1.equals(tmp3)){return true;}
	// 		}
	// 	}
	// 	return false;
	// }


	public static void mapFourLetters(String s, ST st){
		//String tmp1 = sortString(s1.substring(1, s1.length()));
		String inputString = sortString(s);

		for (int x = 0; x < 5 ; x++ ) {
			LinkedBag lb = new LinkedBag();
			if(x == 0){
				String inputStringShort = inputString.substring(x, inputString.length()-1);
				if(st.contains(inputStringShort)) {
					lb = (LinkedBag) st.get(inputStringShort);
					lb.add(s);
					st.put(inputStringShort, lb);
					StdOut.println(inputStringShort + " already exists");
				} 
				else {
					lb.add(inputString);
					st.put(inputStringShort, lb);
					StdOut.println("added: " + inputStringShort);
				}	
			}
			else if(x == inputString.length()-1){
				String inputStringShort = inputString.substring(inputString.length() - x, inputString.length());
				if(st.contains(inputStringShort)) {
					lb = (LinkedBag) st.get(inputStringShort);
					lb.add(s);
					st.put(inputStringShort, lb);
					StdOut.println(inputStringShort + " already exists");
				} 
				else {
					lb.add(inputString);
					st.put(inputStringShort, lb);
					StdOut.println("added: " + inputStringShort);
				}
			}
			else{
				String inputStringShort = inputString.substring(0, x) + inputString.substring(x+1);
				if(st.contains(inputStringShort)) {
					lb = (LinkedBag) st.get(inputStringShort);
					lb.add(s);
					st.put(inputStringShort, lb);
					StdOut.println(inputStringShort + " already exists");
				} 
				else {
					lb.add(inputString);
					st.put(inputStringShort, lb);
					StdOut.println("added: " + inputStringShort);
				}
			}
		}
	}

	public static String sortString(String s){
		String[] arr = s.split("");
		Quick.sort(arr);
		String sorted = "";
		for (String x : arr){
			sorted += x;
		}			
		return sorted;
	}

	public static void shortestPath(String scource, String vertex, Digraph graph, LinearProbingHashST<String, Integer> vertices){
		int scourceIndex = vertices.get(scource);
		int vertexIndex = vertices.get(vertex);
		BreadthFirstDirectedPaths p = new BreadthFirstDirectedPaths(graph, scourceIndex);
		if(p.hasPathTo(vertexIndex)){
			StdOut.println(p.distTo(vertexIndex));
		}
		else{
			StdOut.println(-1);
		}
		
	}

	public static void main(String[] args) {
		//ST<String, LinkedBag> vertices = new ST<String, LinkedBag>();
		ST<String, LinkedBag> fourLetterWords = new ST<String, LinkedBag>();
		
		String[] inputWords = StdIn.readAllStrings();

		String[] inputWordsFour = new String[inputWords.length];

		//mapFourLetters(inputWords[w], fourLetterWords);

		// Put all words in a sorted structure. Values are bags containing the original words
		// from which the four-letter word is derived
		for (int w = 0; w < inputWords.length; w++) {
			mapFourLetters(inputWords[w], fourLetterWords);
			// if (fourLetterWords.contains(sortString(inputWords[w].substring(1)))) {
			// 	// LinkedBag tempLb = fourLetterWords.get(sortString(inputWords[w].substring(1)));
			// 	// tempLb.add(inputWords[w]);
			// 	// fourLetterWords.put(sortString(inputWords[w].substring(1)), lb);
			// 	StdOut.println(sortString(inputWords[w].substring(1)) + " already exists");
				
			// }
			// else {
			// 	//LinkedBag lb = new LinkedBag();
			// 	mapFourLetters(inputWords[w], fourLetterWords);
			// 	//lb.add(inputWords[w]);
			// 	//fourLetterWords.put(sortString(inputWords[w].substring(1)), lb);
			// }
		}

		// Create an array with all "the last four" of all words in the file
		// and sort it
		for (int q = 0; q < inputWords.length; q++) {
			inputWordsFour[q] = sortString(inputWords[q].substring(1));
			//StdOut.println(inputWordsFour[q]);
		}
		Arrays.sort(inputWordsFour);


		
		// int v = inputWords.length;
		// Digraph graph = new Digraph(v);
		// for(int i = 0; i < inputWords.length; i++){
		// 	String s1 = inputWords[i];
		// 	vertices.put(s1, i);
		// 	for(int j = i+1; j < inputWords.length; j++){
		// 		String s2 = inputWords[j];
		// 		if(containLetters(s1, s2) && !s1.equals(s2)){graph.addEdge(i, j);}
		// 		if(containLetters(s2, s1) && !s2.equals(s1)){graph.addEdge(j, i);}
		// 	}
		// }
		// shortestPath("about","there", graph, vertices);
		//StdOut.println(graph.toString());
	}

}

