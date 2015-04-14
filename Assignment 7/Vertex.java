public class Vertex{
	private int index;
	private String word;

	public Vertex(String w, int i){
		word = w;
		index = i;
	}

	public int getIndex(){
		return index;
	}

	public String getWord(){
		return word;
	}

	public void setIndex(int index){
		this.index = index;
	}

}