public class SuperVectorMario {
	
	public static void main(String[] args) {

		int rows = StdIn.readInt();
		int colums = StdIn.readInt();
		StdIn.readChar(); 		// remove linespace

		String[] input = StdIn.readAllLines();	// 
		char[][] grid = new char[rows][colums];

		Digraph graph = new Digraph(rows*colums);	// digraph

		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < colums; j++) {
				grid[i][j] = input[i].charAt(j);

				StdOut.print(grid[i][j]);
			}
			StdOut.print("\n");
		}
	}


	public void addToBoard(String s){

		if(!s.equals("O")){
			if(s.equals("S")){

			}
			else if(s.equals("F")){

			}
			else{
			}
		}
	}

}