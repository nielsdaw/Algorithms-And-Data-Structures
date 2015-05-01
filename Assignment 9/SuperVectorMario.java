import java.util.ArrayList;
import java.lang.Iterable;

public class SuperVectorMario {

	public static void main(String[] args) {
		int rows = StdIn.readInt();
		int colums = StdIn.readInt();
		StdIn.readChar(); 		// remove linespace
		// StdIn.readChar();		// remove some annoying input crap

		String[] input = StdIn.readAllLines();
		char[][] grid = new char[colums][rows];
		Queue move = new Queue();
		ArrayList start = new ArrayList<Vertex>();
		ArrayList finish = new ArrayList<Vertex>();
		int[] test = new int[1];
		int[] test2 = new int[1];

		ST visited = new ST(); // visited 

		Digraph graph = new Digraph(rows*colums*10);	// Graph 

		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < colums; j++) {
				// StdOut.println("grid: " + j + ", " + i);
				grid[j][i] = input[i].charAt(j);
				if (Character.toString(input[i].charAt(j)).equals("S")) {move.enqueue(new Vertex(j, i, 0, 0)); start.add(new Vertex(j, i, 0, 0));}
				else if (input[i].equals("F")) {finish.add(new Vertex(i, j, 0, 0));}
			}
		}

		// while(!start.isEmpty()) {
			// Vertex v = (Vertex) start.dequeue();
			// Queue move = new Queue();
			// move.enqueue(v);
			// StdOut.println(v.getX() + ", " + v.getY() + " velocity: " +  v.getDX() + ", " + v.getDY());
			// StdOut.println("Fest?");
			int currentVertex = 0;
			int nextVertex = 0;
			boolean startSeen = false;
			boolean finishSeen = false;

			while(!move.isEmpty()) {
				Vertex v1 = (Vertex) move.dequeue();
				StdOut.println(v1.getX() + ", " + v1.getY() + " velocity: " +  v1.getDX() + ", " + v1.getDY());
				//StdOut.println("Fest!");
				for (int startX = v1.getX()+v1.getDX()-1; startX <= v1.getX()+v1.getDX()+1; startX++) {

					for (int startY = v1.getY()+v1.getDY()-1; startY <= v1.getY()+v1.getDY()+1; startY++) {

						StdOut.println(startX + ", " + startY + " " + (startX-v1.getX()) + "," + (startY-v1.getY()));

						if (isLegalXY(startX, startY, rows-1, colums-1) && Character.toString(grid[startX][startY]).matches("S|\\s|F") && !isVisited(startX, startY, startX-v1.getX(), startY-v1.getY(), colums-1, visited)) { // JEG MANGLER AT TJEKKE ALLEREDE BESØGTE V'S

							// StdOut.println("speed: " + (startX-v1.getX()) + " " + (startY-v1.getY()) + " " + v1.getDX() + "," + v1.getDY());

							move.enqueue(new Vertex(startX, startY, startX-v1.getX(), startY-v1.getY()));
							// StdOut.println("just added: " + startX + "," + startY + " " + (startX-v1.getX()) + "," + (startY-v1.getY()));
							nextVertex++;
							graph.addEdge(currentVertex,nextVertex);

							if(!startSeen && Character.toString(grid[startX][startY]).matches("S")){
								test[0] = nextVertex;
								startSeen = true;
							}
							else if(!finishSeen && Character.toString(grid[startX][startY]).matches("F")){
								test2[0] = nextVertex;
								finishSeen = true;
							}
							// StdOut.println("move.size(): " + move.size());
						}
					}
					currentVertex++;
				}
				StdOut.println("Done!");
			}
			StdOut.println("inner while is empty");
		// }
		// StdOut.println("Outer while is empty");


			// Iterable list1 = visited.keys();

			// for (Object look: list1){
			// 	Integer lookup = (Integer) look;
			// 	Bag list = (Bag) visited.get(lookup.intValue());

			// 	for (Object o: list) {
			// 		Vertex v = (Vertex) o;
			// 		StdOut.println("visited vertex: " + v.getX() + "," + v.getY() + "  velocity: " + v.getDX() + "," + v.getDY());
			// 		}
			// }

			// StdOut.println(visited.size());

			StdOut.println(graph.toString());
			StdOut.println("arrss: " + test[0] + " " + test2[0]);
			BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(graph,test[0]);
			StdOut.println(bfs.distTo(test2[0]));


	}

	public static boolean isLegalXY(int x, int y, int rows, int colums) {
		if (x < 0 || y < 0 || x > colums || y > rows) {return false;}
		else {return true;}
	}

	public static boolean isVisited(int x, int y, int dx, int dy, int colums, ST visited){
		int lookup = (colums * y) + (x +1);
		Vertex tmpV = new Vertex(x, y, dx, dy);
		if (visited.contains(lookup)){
			Bag list = (Bag) visited.get(lookup);
			for (Object o: list) {
				if(tmpV.compareTo( (Vertex) o) == 0){
					StdOut.println("allready visited: " + tmpV.getX() + "," + tmpV.getY() + " " + tmpV.getDX() + "," + tmpV.getDY());
					return true;
				}
			}
			list.add(tmpV);
		}
		else{ 
			Bag list = new Bag(); 
			list.add(tmpV); 
			visited.put(lookup,list);
		}
		return false;
	}














}