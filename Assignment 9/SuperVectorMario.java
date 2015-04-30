import java.util.ArrayList;

public class SuperVectorMario {

	public static void main(String[] args) {
		int rows = StdIn.readInt();
		int colums = StdIn.readInt();
		StdIn.readChar(); 		// remove linespace
		StdIn.readChar();		// remove some annoying input crap

		String[] input = StdIn.readAllLines();
		char[][] grid = new char[colums][rows];
		Queue start = new Queue();
		ArrayList finish = new ArrayList<Vertex>();

		Graph graph = new Graph(rows*colums);	// Graph

		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < colums; j++) {
				StdOut.println(j + ", " + i);
				grid[j][i] = input[i].charAt(j);
				if (Character.toString(input[i].charAt(j)).equals("S")) {start.enqueue(new Vertex(j, i, 0, 0)); StdOut.println("Added to start " + j + ", " + i + " " + Character.toString(input[i].charAt(j)));}
				else if (input[i].equals("F")) {finish.add(new Vertex(i, j, 0, 0));}
			}
		}

		while(!start.isEmpty()) {
			Vertex v = (Vertex) start.dequeue();
			Queue move = new Queue();
			move.enqueue(v);
			StdOut.println(v.getX() + ", " + v.getY() + " and " +  v.getDX() + ", " + v.getDY());
			StdOut.println("Fest?");
			while(!move.isEmpty()) {
				Vertex v1 = (Vertex) move.dequeue();
				//StdOut.println("Fest!");
				for (int startX = v1.getX()+v1.getDX()-1; startX <= v1.getX()+v1.getDX()+1; startX++) {
					for (int startY = v1.getY()+v1.getDY()-1; startY <= v1.getY()+v1.getDY()+1; startY++) {
						StdOut.println(startX + ", " + startY);
						if (isLegalXY(startX, startY, rows-1, colums-1) && Character.toString(grid[startX][startY]).equals(" ")) { // JEG MANGLER AT TJEKKE ALLEREDE BESØGTE V'S
							move.enqueue(new Vertex(startX, startY, startX-v1.getX(), startY-v1.getY()));
							StdOut.println("move.size(): " + move.size());
						}
					}
				}
				StdOut.println("Done!");
			}
			StdOut.println("inner while is empty");
		}
		StdOut.println("Outer while is empty");
	}

	public static boolean isLegalXY(int x, int y, int rows, int colums) {
		if (x < 0 || y < 0 || x > colums || y > rows) {return false;}
		else {return true;}
	}
}