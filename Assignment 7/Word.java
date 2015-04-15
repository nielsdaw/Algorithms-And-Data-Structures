import java.util.Iterator;

public class Word{
	private Bag<Vertex> bag;	

	public Word(){
		bag = new Bag();
	}

	public void addVertex(Vertex v){
			bag.add(v);
		}

	public Iterator getWordsFromBag(){
		return bag.iterator();
	}
}