public class WordLadder{

	public static void main(String[] args) {
		
		

		while(StdIn.hasNextLine()){
			String s1 = StdIn.readString();
			String s2 = StdIn.readString();
			if(containLetters(s1,s2)){
				// do stuff
			}
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

}