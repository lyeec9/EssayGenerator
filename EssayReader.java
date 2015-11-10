import java.util.*;


 /**
  * Refactor this code to use an API call from MAS in the future, as soon as the key is acquired
  *
  */
class EssayReader{
	Map<Word, Integer> startingWords;
	String query;
	String folder;


	public EssayReader(String q, String folderDest){
		query = q;
		folder = folderDest;
	}

	public Map<Word, Integer> readFiles() {
	    Scanner sc;
	    Map<Word, Integer> startingWords = new HashMap<>();
	    for (File file : folder.listFiles()) {
	        sc = new Scanner(file);
	        Word currWord = null;
		    while(sc.hasNext()){
		        while(sc.hasNextLine()){
					String line = sc.nextLine();
		        	//change later to include punctuation
		        	tokenizedline.split("\\w+([.,]\\w+)*|\\S+");
		        	if(currWord==null){
		        		currWord = new Word(tokenizedline[0]);
		        		if(startingWords.contains(currWord)){
		        			startingWords.put(currWord, startingWords.get(currWord)+1));
		        		}
		        		else{
		        			startingWords.put(currWord, 1));	
		        		}
		        	}
		        	else{
		        		for(int i = 1; i < tokenizedline.length(); i++){
		        			Word nextWord = new Word(tokenizedline[i]);
		        			currWord.setNextWord(nextWord);
		        			currWord = nextWord;
		        		}
		        	}
		        }
		        //add paragraph line here to currword, since there is nothing on the line but still words later
		        if(currWord != null){ //Skip over all blank lines at the start of the essay
		        	Word nextWord = new Word(" ");
        			currWord.setNextWord(nextWord);
        			currWord = nextWord;
		        }
	        	sc.nextLine();
		    }
    	}
    	return startingWords;
	}

}