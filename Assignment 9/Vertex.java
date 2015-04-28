public class Vertex{
	
	private int index;
	private String xy;
	private int x;
	private int y;
	private int dx;
	private int dy;

	public Vertex(int x, int y){
		this.index = 0;
		this.xy = String.valueOf(x) + "," + String.valueOf(y);
		this.x = x;
		this.y = y;
		this.dx = 0;
		this.dy = 0;
	}

	public Vertex(int index, int x, int y, int dx, int dy){
		this.index = index;
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}

	public String getXY(){
		return xy;
	}

	public int getIndex(){
		return index;
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

	public void setVelocity(int dx, int dy){
		this.dx = dx;
		this.dy = dy;
	}

}