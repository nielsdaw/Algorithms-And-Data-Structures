import java.util.Arrays;

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
		LinearProbingHashST<String, Integer> vertices = new LinearProbingHashST<String, Integer>();
		LinearProbingHashST<String, String> fourLetterWords = new LinearProbingHashST<String, String>();
		
		String[] inputWords = StdIn.readAllStrings();

		Arrays.sort(inputWords);
		int v = inputWords.length;
		Digraph graph = new Digraph(v);
		for(int i = 0; i < inputWords.length; i++){
			String s1 = inputWords[i];
			vertices.put(s1, i);
			for(int j = i+1; j < inputWords.length; j++){
				String s2 = inputWords[j];
				if(containLetters(s1, s2) && !s1.equals(s2)){graph.addEdge(i, j);}
				if(containLetters(s2, s1) && !s2.equals(s1)){graph.addEdge(j, i);}
			}
		}
		shortestPath("about","there", graph, vertices);
		//StdOut.println(graph.toString());
	}

}

