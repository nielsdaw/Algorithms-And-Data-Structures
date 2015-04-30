import java.util.ArrayList;

public class SuperVectorMario {

	public static void main(String[] args) {
		int rows = StdIn.readInt();
		int colums = StdIn.readInt();
		StdIn.readChar(); 		// remove linespace
		StdIn.readChar();		// remove some annoying input crap

		String[] input = StdIn.readAllLines();
		char[][] grid = new char[rows][colums];
		ST board = new ST();
		Queue start = new Queue();
		ST finish = new ST();

		Graph graph = new Graph(rows*colums);	// Graph

		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < colums; j++) {
				grid[i][j] = input[i].charAt(j);
				if 		(input[i].equals(" ")) {board.put((i*colums)+j+1, new Vertex(i, j, 0, 0));}
				else if (input[i].equals("S")) {start.enqueue(new Vertex(i, j));}
				else if (input[i].equals("F")) {finish.put((i*colums)+j+1, new Vertex(i, j, 0, 0));}

				StdOut.println((i*colums)+j+1);
				//StdOut.print(grid[i][j]);
			}
			StdOut.print("\n");
		}

		while(!start.isEmpty()) {
			Vertex v = (Vertex) start.dequeue();
			int velx = 0;
			int vely = 0;
			int vx = v.getX();
			int vy = v.getY();
			Queue move = createQ(vx, vy, velx, vely); // what is the velocity?
			while(!move.isEmpty()) {
				Vertex v1 = (Vertex) move.dequeue();
			}
		}
	}

	public static Queue createQ(int sx, int sy, int dx, int dy) {
		int x = sx+dx;
		int y = sy+dy;
		Queue q = new Queue();
		q.enqueue(new Vertex(x+1, y+1, dx+1, dy+1));
		q.enqueue(new Vertex(x, y+1, dx, dy+1));
		q.enqueue(new Vertex(x+1, y, dx+1, dy));
		q.enqueue(new Vertex(x-1, y-1, dx-1, dy-1));
		q.enqueue(new Vertex(x-1, y, dx-1, dy));
		q.enqueue(new Vertex(x, y-1, dx, dy-1));
		q.enqueue(new Vertex(x-1, y+1, dx-1, dy+1));
		q.enqueue(new Vertex(x+1, y-1, dx+1, dy-1));
		q.enqueue(new Vertex(x, y, dx, dy));
		return q;
	}
}