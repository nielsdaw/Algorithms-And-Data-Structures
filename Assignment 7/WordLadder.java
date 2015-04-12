import java.util.HashMap;

public class WordLadder{
	


	private class Vertex{
		private int index;
		private String word;

		public Vertex(String w, int i){
			word = w;
			index = i;
		}

		public int getIndex(){
			return index;
		}

		public String getWord(){
			return word;
		}
	}

	public static boolean containLetters(String s1, String s2){
		String tmp1 = sortString(s1.substring(1, s1.length()));
		String tmp2 = sortString(s2);

		for (int x = 0; x < 5 ; x++ ) {
			if(x == 0){
				String tmp3 = tmp2.substring(x, tmp2.length()-1);
				StdOut.println(tmp3);
				if(tmp1.equals(tmp3)){return true;}
			}
			else if(x == tmp2.length()-1){
				String tmp3 = tmp2.substring(tmp2.length() - x, tmp2.length());
				StdOut.println(tmp3);	
				if(tmp1.equals(tmp3)){return true;}
			}
			else{
				String tmp3 = tmp2.substring(0, x) + tmp2.substring(x+1);
				StdOut.println(tmp3);
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
		int v = 5757;
		HashMap<Integer, Vertex> vertices = new HashMap<Integer, Vertex>();
		Digraph graph = new Digraph(v);
		int counter = 0; 

		while(StdIn.hasNextLine()){
			int counter2 = 1;
			String s1 = StdIn.readString();
			Vertex vertex1 = new Vertex(s1, counter);
			vertices.add(vertex1);
			while(StdIn.hasNextLine()){
				String s2 = StdIn.readString();
				Vertex vertex2 = new Vertex(s2, counter2);
				if(containLetters(s1, s2)){
					graph.addEdge(vertex1.getIndex(), vertex2.getIndex());
				}
			}
		}
	}

}