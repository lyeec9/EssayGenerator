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

	/**
	 * Returns the string needed to be written to the ml file
	 * Currently only changes the ML for exact cached searches. Weights are not scaled properly. 
	 */
	public String setMachineLearning(Word next, boolean positive){
		StringBuffer output = new StringBuffer(word);
		for(Word possibleNext : nextWord.keySet()){
			if(possibleNext.equals(next)){
				if(positive){
					nextWord.put(possibleNext, nextWord.get(possibleNext)+1);
				}
				else{
					if(nextWord.get(possibleNext) > 1){
						nextWord.put(possibleNext, nextWord.get(possibleNext)-1);
					}
				}
			}
			output.append(" " + possibleNext + " " + nextWord.get(possibleNext));
		}
		output.append("\n");
		return output.toString();
	}

	/**
	 * Sets the weights of the following word based on previously cached ML
	 */
	public void setMachineLearning(String cachedOutput){
		
	}

	@Override
	public int hashCode(){
		return word.hashCode();
	}

	@Override
	public boolean equals(Object obj){
		return this.word.equals(((Word)obj).word);
	}

	@Override
	public String toString(){
		return word;
	}

}