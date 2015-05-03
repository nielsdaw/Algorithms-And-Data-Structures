import java.lang.Iterable;
import java.util.Iterator;

public class SuperVectorMario {
	public static void main(String[] args) {
		int rows = StdIn.readInt();
		int colums = StdIn.readInt();
		StdIn.readChar(); 						// remove linespace
		String[] input = StdIn.readAllLines();	
		char[][] grid = new char[colums][rows];	// stores all coordinates in a grid
		Queue move = new Queue();				// Queue for moves
		Bag start = new Bag();					// stores all start index'
		Bag finish = new Bag();					// stores all finish index'
		ST visited = new ST(); 					// stores all visited verticies
		Digraph graph = new Digraph(rows*colums*colums);	// graph must be big enough to store all possibilities of coordinates with different velocity	

		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < colums; j++) {
				grid[j][i] = input[i].charAt(j);
				if (Character.toString(input[i].charAt(j)).equals("S")) {move.enqueue(new Vertex(j, i, 0, 0));}	// save start
			}
		}

		int currentVertex, vertexCount = 0;	// variables for vertex index'
		while(!move.isEmpty()) {
			Vertex v1 = (Vertex) move.dequeue();
			currentVertex = v1.getIndex();
			if(Character.toString(grid[v1.getX()][v1.getY()]).equals("S")){start.add(currentVertex);} 	// add start index'
			for (int startX = v1.getX()+v1.getDX()-1; startX <= v1.getX()+v1.getDX()+1; startX++) {		// loop through possible moves
				for (int startY = v1.getY()+v1.getDY()-1; startY <= v1.getY()+v1.getDY()+1; startY++) {
					if (isLegalXY(startX, startY, rows-1, colums-1) && Character.toString(grid[startX][startY]).matches("S|\\s|F") && !isVisited(startX, startY, startX-v1.getX(), startY-v1.getY(),colums-1, visited)) { // check for visited verticies
						vertexCount++;
						move.enqueue(new Vertex(startX, startY, startX-v1.getX(), startY-v1.getY(), vertexCount));	// add new coordinates to queue
						graph.addEdge(currentVertex,vertexCount);
						if(Character.toString(grid[startX][startY]).matches("F")){	// add Finish index'
							finish.add(vertexCount);
						}
					}
				}
			}
		}

		int shortestMoves = 1000000;
		for (Object o1: start) {
			Integer startIndex = (Integer) o1;
			BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(graph,startIndex.intValue());
			for (Object o2 : finish) {
				Integer finishIndex = (Integer) o2;
				int tmpMoves = bfs.distTo(finishIndex.intValue());
				if(shortestMoves > tmpMoves){shortestMoves = tmpMoves;}
			}
		}
		StdOut.println("Moves: " + (shortestMoves +1));	// add one, since start counts as 1 move
	}

	public static boolean isLegalXY(int x, int y, int rows, int colums) {
		if (x < 0 || y < 0 || x > colums || y > rows) {return false;}
		else {return true;}
	}

	public static boolean isVisited(int x, int y, int dx, int dy, int colums, ST visited){
		int lookup = (colums * y) + (x +1);
		Vertex tmpV = new Vertex(x, y, dx, dy);
		if (visited.contains(lookup)){				// has this been visited?
			Bag list = (Bag) visited.get(lookup);	
			for (Object o: list) {
				if(tmpV.compareTo( (Vertex) o) == 0){
					return true;
				}
			}
			list.add(tmpV);		// add to the inner list if not visited before
		}
		else{ 
			Bag list = new Bag(); 
			list.add(tmpV); 			// add to the list if not visited before
			visited.put(lookup,list); 	// add to visited 
		}
		return false;
	}














}