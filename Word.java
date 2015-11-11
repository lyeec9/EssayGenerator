import java.util.*;

class Word{
	private String word;
	private Map<Word, Integer> nextWord;
	private int totalNextWords;

	/**
	 * Word constructor. This constructor should only be used to indicate the start of the essay.
	 */
	public Word(){
		word = "";
		nextWord = new HashMap<>();
		totalNextWords = 0;
	}
	
	/**
	 * Word constructor. This constructor should be used in all cases except for indicating the
	 * very start of the essay.
	 */
	public Word(String w){
		word = w;
		nextWord = new HashMap<>();
		totalNextWords = 0;
	}

	public String getWord(){
		return word;
	}

	//Implement machine learning here soon
	public Word getRandomNextWord(){
		int index = (int)(Math.random()*totalNextWords);
		int counter = 0;
		for(Map.Entry<Word, Integer> entry : nextWord.entrySet()){
			counter+= entry.getValue();
			if(counter > index){
				return entry.getKey();
			}
		}
		return null;
	}

	public void setNextWord(Word w){
		if(nextWord.keySet().contains(w)){
			nextWord.put(w, nextWord.get(w)+1);
		}
		else{
			nextWord.put(w, 1);
		}
		totalNextWords++;
	}

	public int getTotalNextWords(){
		return totalNextWords;
	}

	@Override
	public int hashCode(){
		return word.hashCode();
	}

	@Override
	public boolean equals(Object obj){
		return this.word.equals(((Word)obj).word);
	}

}