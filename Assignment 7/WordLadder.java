import java.util.Arrays;
import java.lang.Iterable;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WordLadder{

	public static void mapFourLetters(String s, ST st, int index){
		//String tmp1 = sortString(s1.substring(1, s1.length()));
		String inputString = sortString(s);
		Vertex v = new Vertex(s, index);
		boolean seen = false;

		for (int x = 0; x < 5 ; x++ ) {
			LinkedBag lb = new LinkedBag();
			if(x == 0){
				String inputStringShort = inputString.substring(x, inputString.length()-1);
				if(st.contains(inputStringShort)) {
					lb = (LinkedBag) st.get(inputStringShort);
					for (Object o: lb) {
						Vertex vert = (Vertex) o;
						if (vert.getWord().equals(s)) {seen = true;}
					}
					if(!seen) {
						lb.add(v);
						st.put(inputStringShort, lb);
					}
				} 
				else {
					lb.add(v);
					st.put(inputStringShort, lb);
				}	
			}
			else if(x == inputString.length()-1){
				String inputStringShort = inputString.substring(inputString.length() - x, inputString.length());
				if(st.contains(inputStringShort)) {
					lb = (LinkedBag) st.get(inputStringShort);
					for (Object o: lb) {
						Vertex vert = (Vertex) o;
						if (vert.getWord().equals(s)) {seen = true;}
					}
					if(!seen) {
						lb.add(v);
						st.put(inputStringShort, lb);
					}
				}
				else {
					lb.add(v);
					st.put(inputStringShort, lb);
				}
			}
			else{
				String inputStringShort = inputString.substring(0, x) + inputString.substring(x+1);
				if(st.contains(inputStringShort)) {
					lb = (LinkedBag) st.get(inputStringShort);
					for (Object o: lb) {
						Vertex vert = (Vertex) o;
						if (vert.getWord().equals(s)) {seen = true;}
					}
					if(!seen) {
						lb.add(v);
						st.put(inputStringShort, lb);
					}
				}
				else {
					lb.add(v);
					st.put(inputStringShort, lb);
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

	public static void shortestPath(String scource, String vertex, Digraph graph, ST<String, Integer> vertices, String[] inputWords){
		int scourceIndex = vertices.get(scource);
		int vertexIndex = vertices.get(vertex);
		BreadthFirstDirectedPaths p = new BreadthFirstDirectedPaths(graph, scourceIndex);
		if(p.hasPathTo(vertexIndex)){
			StdOut.println(p.distTo(vertexIndex) + " (dist) with the path: ");
			for (Integer i : p.pathTo(vertexIndex)) {
				StdOut.print(inputWords[i] + " ");
			}
			StdOut.println();
		}
		else{
			StdOut.println(-1);
		}
		
	}

	public static void main(String[] args) {
		ST<String, LinkedBag> fourLetterWords = new ST<String, LinkedBag>();
		ST<String, Integer> indexFix = new ST<String, Integer>();
		
		String[] inputWords = StdIn.readAllStrings();
		Arrays.sort(inputWords);

		String[] inputWordsFour = new String[inputWords.length];

		// Put all words in a sorted structure. Values are bags containing the original words
		// from which the four-letter word is derived
		for (int w = 0; w < inputWords.length; w++) {
			mapFourLetters(inputWords[w], fourLetterWords, w);
			indexFix.put(inputWords[w], w);
		}

		Digraph graph = new Digraph(inputWords.length);
		
		for (int i = 0; i < inputWords.length; i++) {
			if(fourLetterWords.contains(sortString(inputWords[i].substring(1)))) {
				LinkedBag lb = (LinkedBag) fourLetterWords.get(sortString(inputWords[i].substring(1)));
				for (Object o: lb) {
					Vertex v = (Vertex) o;
					if (!v.getWord().equals(inputWords[i])) {
						graph.addEdge(i, v.getIndex());
					}
				}
			}
		}

		try{
			BufferedReader in = new BufferedReader(new FileReader(args[0]));
			String line;
			while((line = in.readLine()) != null) {
				String w1 = line.substring(0, 5);
				String w2 = line.substring(6);
				StdOut.println("\n" + w1 + " to " + w2);
				shortestPath(w1, w2, graph, indexFix, inputWords);
			}
			in.close();
		}
		catch (Exception e1) {
			StdOut.println("No in-file given as argument\nPlease use this program like so:\njava WordLadder words-5757-in.txt < words-5757.txt");
		}
	}
}

