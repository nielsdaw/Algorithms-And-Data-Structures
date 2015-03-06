public class e_1_3_2{
	

	public static void main(String[] args) {
		
		Stack<String> s = new Stack<String>();

		while (!StdIn.isEmpty()){
			String item = StdIn.readString();
			if(!item.equals("-"))
				s.push(item);
			else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
		}

		StdOut.println("(" + s.size() + " left on stack)");

	}
}