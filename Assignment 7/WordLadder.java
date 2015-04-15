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


	public static void mapFourLetterWords(String s1, int index, LinearProbingHashST<String, Word> st){
		String tmp1 = sortString(s1);
		boolean exists = false;
		Word tmpWord;
		Vertex tmpVertex;
		
		for (int x = 0; x < tmp1.length() ; x++ ) {
			if(x == 0){
				String tmp2 = tmp1.substring(x, tmp1.length()-1);
				if(st.get(tmp2) != null){
					tmpWord = st.get(tmp2);
					tmpWord.addVertex(tmpVertex);
				}
				else{
					tmpVertex = new Vertex(s1, index);
					tmpWord = new Word(tmpVertex);
					st.put(tmp2, tmpWord.add(tmpWord));
				}
				
			}
			else if(x == tmp1.length()-1){
				String tmp2 = tmp1.substring(tmp1.length() - x, tmp1.length());
				if(st.get(tmp2) != null){
					tmpWord  = st.get(tmp2);
					tmpWord.addVertex(tmpVertex);
				}
				else{
					tmpVertex = new Vertex(s1, index);
					tmpWord = new Word(tmpVertex);
					tmpWord.addVertex(tmpVertex);
					st.put(tmp2, tmpWord.add(tmpWord));
				}
			}	
			else{
				String tmp2 = tmp1.substring(0, x) + tmp1.substring(x+1);
				if(st.get(tmp2) != null){
					tmpWord = st.get(tmp2);
					tmpWord.addVertex(tmpVertex);
				}
				else{
					tmpVertex = new Vertex(s1, index);
					tmpWord = new Word(tmpVertex);
					tmpWord.addVertex(tmpVertex);
					st.put(tmp2, tmpWord.add(tmpWord));
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
		// LinearProbingHashST<String, Integer> vertices = new LinearProbingHashST<String, Integer>();
		String[] inputWords = StdIn.readAllStrings();
		RedBlackBST<String, Vertex> tree = new RedBlackBST<String, Vertex>();
		LinearProbingHashST<String, Word> fourLetterTree = new LinearProbingHashST<String, Word>();

		int v = inputWords.length;
		int counter = 0;
		int counter2 = 1;
		Digraph graph = new Digraph(v);

		for (String s: inputWords){
			tree.put(s, new Vertex(s,0));
		}

		int count = 0;
		for(Vertex vertex: tree){
			vertex.setIndex(count);
			mapFourLetterWords(vertex.getWord(), vertex.getIndex(), tree);
			count++;
		}

		Iterator iterator = fourLetterTree.keys().iterator();
		boolean marked = false;


		
		// for(int i = 0; i < inputWords.length; i++){
		//  	String s1 = inputWords[i];
		//  	vertices.put(s1, i);
		// 	for(int j = i+1; j < inputWords.length; j++){
		// 		String s2 = inputWords[j];
		// 		if(containLetters(s1, s2) && !s1.equals(s2)){graph.addEdge(i, j);}
		// 		if(containLetters(s2, s1) && !s2.equals(s1)){graph.addEdge(j, i);}
		// 	}
		// }
		// //shortestPath("about","there", graph, vertices);
		// StdOut.println(graph.toString());

		for(String s: tree.keys()){
			StdOut.println(tree.get(s).getWord() + " has index " + tree.get(s).getIndex());
		}




	}




}

