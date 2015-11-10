

class Word{
	private String word;
	private Map<Word, Integer> nextWord;
	int totalNextWords;
	public Word(String w){
		word = w;
		nextWord = new HashMap<>();
	}

	@Override
	public int hashCode(){
		return word.hashCode();
	}

	@Override
	public boolean equals(Object obj){
		return this.word.equals(obj.word);
	}

	public Map<Word, Integer> getNextWord(){
		return nextWord;
	}
	public void setNextWord(Word w){
		if(nextWord.contains(w)){
			nextWord.put(w, nextWord.get(w)+1);
		}
		else{
			nextWord.put(w, 1);
		}
		totalNextWords++;
	}
}