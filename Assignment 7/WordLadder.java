import java.util.Iterator;

public class WordLadder{

	public static boolean containLetters(String s1, String s2){
		String tmp1 = sortString(s1.substring(1, s1.length()));
		String tmp2 = sortString(s2);

		for (int x = 0; x < 5 ; x++ ) {
			if(x == 0){
				String tmp3 = tmp2.substring(x, tmp2.length()-1);
				if(tmp1.equals(tmp3)){return true;}
			}
			else if(x == tmp2.length()-1){
				String tmp3 = tmp2.substring(tmp2.length() - x, tmp2.length());	
				if(tmp1.equals(tmp3)){return true;}
			}
			else{
				String tmp3 = tmp2.substring(0, x) + tmp2.substring(x+1);
				if(tmp1.equals(tmp3)){return true;}
			}
		}
		return false;
	}


	public static void mapFourLetterWords(String s1, RedBlackBST<String, String> tree){
		String tmp1 = sortString(s1);

		for (int x = 0; x < tmp1.length() ; x++ ) {
			if(x == 0){
				String tmp2 = tmp1.substring(x, tmp1.length()-1);
				tree.put(tmp2, s1);
			}
			else if(x == tmp1.length()-1){
				String tmp2 = tmp1.substring(tmp1.length() - x, tmp1.length());	
				tree.put(tmp2, s1);
			}	
			else{
				String tmp2 = tmp1.substring(0, x) + tmp1.substring(x+1);
				tree.put(tmp2, s1);
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
		// 	Iterable<Integer> path = p.pathTo(vertixIndex);
		// 	for(Integer ints: path) {
		// 		StdOut.println(ints);
			StdOut.println(p.distTo(vertexIndex));
		}
		else{
			StdOut.println(-1);
		}
		
	}

	public static void main(String[] args) {
		LinearProbingHashST<String, Integer> vertices = new LinearProbingHashST<String, Integer>();
		String[] inputWords = StdIn.readAllStrings();
		RedBlackBST<String, Vertex> tree = new RedBlackBST<String, Vertex>();
		RedBlackBST<String, String> fourLetterTree = new RedBlackBST<String, String>();
		int v = inputWords.length;
		int counter = 0;
		int counter2 = 1;
		Digraph graph = new Digraph(v);

		for (String s: inputWords){
			tree.put(s, new Vertex(s,0));
			mapFourLetterWords(s, fourLetterTree);
		}
		Iterator iterator = fourLetterTree.keys().iterator();
		// iterator.next();

		boolean marked = false;
		String tmpFourLetterWord2 = "";



		for(String s: fourLetterTree.keys()){
			StdOut.println(s);
		}

		
		// for (String v1: tree.keys()) {
		// 	tree.get(v1).setIndex(counter);
		// 	String tmpWord = tree.get(v1).getWord();
		// 	String tmpFourLetterWord1 = tmpWord.substring(1);
			
		// 	while(iterator.hasNext() ){
		// 		if(!marked){
		// 			tmpFourLetterWord2 = (String) iterator.next();
		// 		}

		// 		StdOut.println(tmpFourLetterWord1+ " " + tmpFourLetterWord2);

		// 		if(tmpFourLetterWord1.compareTo(tmpFourLetterWord2) < 1 && tmpFourLetterWord1.equals(tmpFourLetterWord2)){
		// 			graph.addEdge(counter, counter2);
		// 			counter2++;
		// 		}
		// 		else if((tmpFourLetterWord1.compareTo(tmpFourLetterWord2) > 0)){
		// 			marked = true;
		// 			break;
		// 		}
		// 		marked = false;
		// 	}
		// 	counter++;
		// 	StdOut.println(counter + " " + counter2);
		// }

		// for(int i = 0; i < inputWords.length; i++){
		//  	String s1 = inputWords[i];
		//  	vertices.put(s1, i);
		// 	for(int j = i+1; j < inputWords.length; j++){
		// 		String s2 = inputWords[j];
		// 		if(containLetters(s1, s2) && !s1.equals(s2)){graph.addEdge(i, j);}
		// 		if(containLetters(s2, s1) && !s2.equals(s1)){graph.addEdge(j, i);}
		// 	}
		// }
		//shortestPath("about","there", graph, vertices);
		StdOut.println(graph.toString());
	}




}

