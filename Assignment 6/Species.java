public class Species{
	private String name;
	private int[] vector;
	private int numberOfElements = 0;

	public Species(String name, int d){
		this.name = name;
		vector = new int[d];
	}

	public String getName(){
		return name;
	}

	public int[] getVector(){
		return vector;
	}

	public int getNumberOfElements(){
		return numberOfElements;
	}

	public void addToVector(int val){
		vector[numberOfElements] = val;
		numberOfElements++;
	}

}
