public class Species{
	private String name;
	private int[] vector;
	private int counter = 0;

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

	public void addToVector(int val){
		vector[counter] = val;
		counter++;
	}

	public void setEuclidianLength(double val){
		euclidianLength = val;
	}

	public double getEuclidianLength(){
		return euclidianLength;
	}

}
