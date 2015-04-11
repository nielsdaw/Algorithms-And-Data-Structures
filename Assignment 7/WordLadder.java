public class WordLadder{

	public static void main(String[] args) {
		
		// String tmpWord1 = StdIn.readString();
		// String tmpWOrd2 = StdIn.readString();

		String s1 = "other";
		String s2 = "there";

		containLetters(s1,s2);

	}


	public static void containLetters(String s1, String s2){

		String tmp = sortString(s1.substring(1, s1.length()));
		StdOut.println(tmp);

		for (int x = 0; x < 5 ; x++ ) {

			if(x == 0){
				String tmp2 = s2.substring(x, s2.length()-1);
				StdOut.println(tmp2);
			}
			else if(x == s2.length()-1){
				String tmp2 = s2.substring(s2.length() - x, s2.length()-1);
				StdOut.println(tmp2);	
			}
			else{
				String tmp2 = s2.substring(0, x) + s2.substring(x+1);
				StdOut.println(tmp2);
			}


		}





	}


	public static String sortString(String s) {
		char[] chars = s.toCharArray();
		Arrays.sort(chars);
		String sorted = new String(chars);
		return sorted;
	}



}