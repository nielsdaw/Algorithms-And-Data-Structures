import java.util.HashMap;

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

	public static String sortString(String s){
		String[] arr = s.split("");
		Quick.sort(arr);
		String sorted = "";
		for (String x : arr){
			sorted += x;
		}			
		return sorted;
	}

	public static void main(String[] args) {
		//HashMap<Integer, Vertex> vertices = new HashMap<Integer, Vertex>();
		String[] inputWords = StdIn.readAllStrings();
		int v = inputWords.length;
		Digraph graph = new Digraph(v);
		Vertex[] vertices = new Vertex[v];

		for(int i = 0; i < inputWords.length; i++){
			String s1 = inputWords[i];
			Vertex vertex1 = new Vertex(s1, i);
			vertices[i] = vertex1;
			for(int j = i+1; j < inputWords.length; j++){
				String s2 = inputWords[j];
				
				if(containLetters(s1, s2) && !s1.equals(s2)){
					//StdOut.println(s1 + " " + s2);
					graph.addEdge(vertex1.getIndex(), j);
				}
				if(containLetters(s2, s1) && !s2.equals(s1)){
					//StdOut.println(s1 + " " + s2);
					graph.addEdge(j, vertex1.getIndex());
				}
			}
		}
		
		//StdOut.println(graph.toString());

		BreadthFirstDirectedPaths p = new BreadthFirstDirectedPaths(graph, 6);
		Iterable<Integer> path = p.pathTo(9);
		
		for(Integer ints: path) {
			StdOut.println(ints);
		}
	}


}

