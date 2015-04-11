import java.util.HashMap;

public class WordLadder{
	private final int vertices = 5757;
	private Map<Vertex> vertices = new HashMap<Vertex>();

	
	private class Vertex{
		private int index;
		private String word;

		public Vertex(String word, int index){
			this.word = word;
			this.index = index;
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
		Digraph graph = new Digraph(vertices);

		while(StdIn.hasNextLine()){
			String s1 = StdIn.readString();
			while(StdIn.hasNextLine()){
				String s2 = StdIn.readString();
				if(containLetters(s1,s2)){

				}
			}
		}
	}

}