import java.util.ArrayList;

public class SuperVectorMario {
	private static ArrayList<Vertex> start = new ArrayList<>();

	// private static ResizingArrayStack start = new ResizingArrayStack();
	private static ST finish = new ST();	// keys ="x,y"
	private static ST board = new ST();		// keys = "x,y"
	private static Queue moves = new Queue();
	
	

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
				addToBoard(String.valueOf(input[i].charAt(j)),j, i);

				StdOut.print(grid[i][j]);
			}
			StdOut.print("\n");
		}

		// for (Object s: board.keys()) {
		// 	String tmp = (String) s;
		// 	Vertex v =  (Vertex) board.get(tmp);
		// 	StdOut.println("x: " + v.getX() + " y: " + v.getY());
		// }

		for (Vertex v: start) {
			move(v);
		}





	}


	public static void addToBoard(String s, int x, int y){
		String key = String.valueOf(x) + "," + String.valueOf(y);
		Vertex tmp = new Vertex(x,y);

		if(!s.equals("O")){
			if(s.equals("S")) {start.add(tmp);}	// add to start

			else if(s.equals("F")) {finish.put(key, tmp);}	// add to finish
			board.put(key, tmp);	// add to board
		}
	}


	public static void move(Vertex v){
		Queue moves = new Queue();
		int x1 = v.getX();
		int y1 =  v.getY();

		StdOut.println("start: " + x1 + ","+  y1);



		for (int i = -1; i < 2; i++) {
			moves.enqueue(new Vertex(x1 + i, y1));
			moves.enqueue(new Vertex(x1, y1 +i ));
			moves.enqueue(new Vertex(x1 + i, y1 + i)); 

			StdOut.println("q" +i + " v: " + (x1 + i) + ","+  y1);
			StdOut.println("q" +i + " v: " + x1 + ","+ (y1+i));
			StdOut.println("q" +i + " v: " + (x1 + i) + ","+  (i+ y1));
		}




		
	}



}