public class SuperVectorMario {
	public static void main(String[] args) {
		int rows = StdIn.readInt();
		int colums = StdIn.readInt();
		String[] input = StdIn.readAllLines();
		
		char[][] grid = new char[rows][colums];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < colums; j++) {
				grid[i][j] = input[i].charAt(j);
				StdOut.println(grid[i][j]);
			}
		}
	}
}