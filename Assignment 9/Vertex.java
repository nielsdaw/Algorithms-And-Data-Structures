public class Vertex implements Comparable<Vertex>{
	
	private int x;
	private int y;
	private int dx;
	private int dy;
	private int index;

	public Vertex(int x, int y, int dx, int dy){
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}

	public Vertex(int x, int y, int dx, int dy, int index){
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.index = index;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public int getDX(){
		return dx;
	}

	public int getDY(){
		return dy;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	@Override
	public int compareTo(Vertex v) {
		if (this.x == v.getX() && this.y == v.getY() && this.dx == v.getDX() && this.dy == v.getDY()) {return 0;}
		else if(this.x < v.getX()){return -1;}
		else {return 1;}
	}
}