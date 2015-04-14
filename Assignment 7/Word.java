import java.util.Iterator;

public class Word{
	private Bag<String> bag;	

	public Word(){
		bag = new Bag();
	}

	public void addWord(String word){
			bag.add(word);
		}

	public Iterator getWordsFromBag(){
		return bag.iterator();
	}
}