public class Vertex{
	
	private int x;
	private int y;
	private int dx;
	private int dy;

	private boolean visited = false;

	public Vertex(int x, int y, int dx, int dy){
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
}